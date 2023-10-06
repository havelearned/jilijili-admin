package top.jilijili.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.jilijili.module.entity.SysNotify;
import top.jilijili.module.entity.dto.SysNotifyDto;
import top.jilijili.module.entity.vo.Result;
import top.jilijili.module.entity.vo.SysNotifyVo;
import top.jilijili.system.mapper.ConvertMapper;
import top.jilijili.system.mapper.SysNotifyMapper;
import top.jilijili.system.service.SysNotifyService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
        IPage<SysNotifyVo> page = this.lambdaQuery()
                .eq(sysNotifyDto.getNotifyId() != null, SysNotify::getNotifyId, sysNotifyDto.getNotifyId())
                .eq(sysNotifyDto.getNotifyStatus() != null, SysNotify::getNotifyStatus, sysNotifyDto.getNotifyStatus())
                .eq(StringUtils.hasText(sysNotifyDto.getNotifyType()), SysNotify::getNotifyType, sysNotifyDto.getNotifyType())
                .between(sysNotifyDto.getComparisonTime() != null && sysNotifyDto.getCreatedTime() != null, SysNotify::getCreatedTime, sysNotifyDto.getCreatedTime(), sysNotifyDto.getComparisonTime()).orderByDesc(SysNotify::getCreatedTime)
                .page(new Page<>(sysNotifyDto.getPage(), sysNotifyDto.getSize()))
                .convert(this.convertMapper::toNotifyVo);
        return Result.ok(page);
    }

    /**
     * 添加或者修改通知信息
     *
     * @param sysNotifyDto
     * @return 操作是否成功
     */
    @Override
    public Result<String> addOrEditNotify(SysNotifyDto sysNotifyDto) {
        SysNotify notify = this.convertMapper.toNotify(sysNotifyDto);

        // 修改操作
        if (sysNotifyDto.getNotifyId() != null) {
            return this.updateById(notify) ? Result.ok("操作成功") : Result.fail("操作失败");
        }

        // 添加
        Integer publish = sysNotifyDto.getPublish();
        boolean flag = false;

        switch (publish) {
            case 1 -> { // 保存并立即发布
                this.save(notify);
                this.rabbitTemplate.convertAndSend(NOTIFY_EXCHANGE, SYS_ROUTER, notify);
                flag = true;
            }
            case 2 -> { // 仅保存
                this.save(notify);
                flag = true;
            }
            case 3 -> { // 定时发布
                // 临时解决,请考虑使用第三方 任务调用框架
                if (sysNotifyDto.getComparisonTime() != null) {
                    // 指定的时间差，以秒为单位
                    long timeDelayInSeconds = sysNotifyDto.getComparisonTime().getTime();
                    // 得到一个线程
                    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
                    // 在指定的时间后执行通知发布任务
                    executorService.schedule(() -> this.rabbitTemplate.convertAndSend(NOTIFY_EXCHANGE, SYS_ROUTER, notify), timeDelayInSeconds, TimeUnit.SECONDS);
                    flag = true;
                }
            }default -> Result.fail("操作失败");
        }

        return flag ? Result.ok("操作成功") : Result.fail("操作失败");
    }


}




