package top.jilijili.system.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import top.jilijili.common.entity.Result;
import top.jilijili.module.entity.SysNotify;
import top.jilijili.module.entity.dto.SysNotifyDto;
import top.jilijili.module.entity.vo.SysNotifyVo;
import top.jilijili.system.common.enums.ErrorType;
import top.jilijili.system.heandler.JiliException;
import top.jilijili.system.mapper.ConvertMapper;
import top.jilijili.system.mapper.SysNotifyMapper;
import top.jilijili.system.pattern.PublishService;
import top.jilijili.system.service.SysNotifyService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static top.jilijili.common.utils.KeyConstants.NOTIFY_EXCHANGE;
import static top.jilijili.system.common.utils.KeyConstants.SYS_ROUTER;

/**
 * @author admin
 * @description 针对表【sys_notify】的数据库操作Service实现
 * @createDate 2023-10-06 16:05:18
 */
@Service
@AllArgsConstructor
public class SysNotifyServiceImpl extends ServiceImpl<SysNotifyMapper, SysNotify> implements SysNotifyService {

    private ConvertMapper convertMapper;
    private RabbitTemplate rabbitTemplate;
    private PublishService publishService;
    private SysNotifyMapper sysNotifyMapper;


    /**
     * 通过通知id查询通知信息
     *
     * @param notifyId 通知id
     * @return 通知对象
     */
    @Override
    public Result<SysNotifyVo> getNotifyById(Long notifyId) {
        return Result.ok(this.convertMapper.toNotifyVo(this.getById(notifyId)));
    }

    /**
     * 查询通知所有信息
     *
     * @param sysNotifyDto 查询对象
     * @return
     */
    @Override
    public Result<IPage<SysNotifyVo>> getNotifyList(SysNotifyDto sysNotifyDto) {
        QueryWrapper<SysNotify> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(sysNotifyDto.getNotifyId() != null, "n.notify_id", sysNotifyDto.getNotifyId())
                .eq(sysNotifyDto.getNotifyStatus() != null, "n.notify_status", sysNotifyDto.getNotifyStatus())
                .eq(StringUtils.hasText(sysNotifyDto.getNotifyType()), "n.notify_type", sysNotifyDto.getNotifyType())

                .eq(StringUtils.hasText(sysNotifyDto.getUsername()), "s.username", sysNotifyDto.getUsername())
                .eq(sysNotifyDto.getUserId() != null, "s.user_id", sysNotifyDto.getUserId())

                .between(sysNotifyDto.getComparisonTime() != null && sysNotifyDto.getCreatedTime() != null, "n.created_time",
                        sysNotifyDto.getCreatedTime(), sysNotifyDto.getComparisonTime()).orderByDesc("n.created_time");
        IPage<SysNotifyVo> page = this.sysNotifyMapper.selectNotifyList(new Page<>(sysNotifyDto.getPage(), sysNotifyDto.getSize()), queryWrapper);
        return Result.ok(page);
    }

    /**
     * 添加或者修改通知信息
     *
     * @param sysNotifyDto
     * @return 操作是否成功
     */
    @Transactional(rollbackFor = JiliException.class, propagation = Propagation.REQUIRES_NEW)
    @Override
    public Result<String> addOrEditNotify(SysNotifyDto sysNotifyDto) {
        SysNotify notify = this.convertMapper.toNotify(sysNotifyDto);

        // 如果是修改
        if (Objects.nonNull(sysNotifyDto.getNotifyId())) {
            boolean updated = this.updateById(notify);
            return updated ? Result.ok() : Result.fail();

        }
        // 添加操作
        List<SysNotify> sysNotifies = new ArrayList<>();

        if (sysNotifyDto.getReceiverIds() != null && !sysNotifyDto.getReceiverIds().isEmpty()) {
            sysNotifies = sysNotifyDto.getReceiverIds().stream().map(item -> {
                SysNotify sysNotify = new SysNotify();
                BeanUtils.copyProperties(notify, sysNotify);
                sysNotify.setReceiverId(item);
                return sysNotify;
            }).collect(Collectors.toList());
        } else {
            sysNotifies.add(notify);
        }
        // 添加
        boolean saved = this.saveBatch(sysNotifies);
        if (!saved) {// 操作失败
            throw new JiliException(ErrorType.OPERATION_FAILED);
        }

        // 1: 添加并且立即发布      * 2: 草稿      * 3: 定时发布
        switch (sysNotifyDto.getPublish()) {
            case 1 -> this.publishService.publishNotify(1, sysNotifies);
            case 2 -> this.publishService.publishNotify(2, sysNotifies);
            case 3 -> this.publishService.publishNotify(3, sysNotifies);
            default -> Result.fail("操作失败");
        }
        return Result.ok("操作成功");
    }


    /**
     * @param notifyId  通过id查询通知并发布
     * @param sysNotify 通过已知通知发布公告
     */
    public void sendSysNotify(Long notifyId, SysNotify sysNotify) {
        if (sysNotify == null) {
            sysNotify = this.getById(notifyId);
        }
        List<SysNotify> list = new ArrayList<>();
        list.add(sysNotify);
        switch (sysNotify.getNotifyType()) {
            case "3","5" -> {
                this.publishService.publishNotify(1, list);
            }
            case "2" -> {
                this.publishService.publishNotify(2, list);
            }
            case "1" -> {
                this.publishService.publishNotify(3, list);
            }
            default -> {
                throw new JiliException(ErrorType.OPERATION_FAILED);
            }
        }
    }

    /**
     * 发送系统通知
     *
     * @param sysNotify 系统通知对象
     */
    public void sendSysNotify(SysNotify sysNotify) {
        if (sysNotify != null) {
            this.rabbitTemplate.convertAndSend(NOTIFY_EXCHANGE, SYS_ROUTER, JSON.toJSONString(sysNotify));
        }
    }
}




