package top.jilijili.system.controller.multchat;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.jilijili.module.entity.CmRecord;
import top.jilijili.module.entity.dto.CmRecordDto;
import top.jilijili.module.entity.vo.CmRecordVo;
import top.jilijili.common.entity.Result;
import top.jilijili.system.controller.SuperController;
import top.jilijili.system.mapper.ConvertMapper;
import top.jilijili.system.service.CmRecordService;

import java.io.Serializable;
import java.util.List;

/**
 * 多媒体聊天记录表(CmRecord)表控制层
 *
 * @author makejava
 * @since 2023-08-05 21:37:00
 */
@RestController
@RequestMapping("cmRecord")
@AllArgsConstructor
@Slf4j
public class CmRecordController extends SuperController {
    /**
     * 服务对象
     */
    private CmRecordService cmRecordService;
    private ConvertMapper convertMapper;

    /**
     * 分页查询所有数据
     *
     * @param cmRecordDto 查询实体
     * @return 所有数据
     */
    @GetMapping
    public Result<IPage<CmRecordVo>> selectAll(CmRecordDto cmRecordDto) {
        IPage<CmRecord> page = new Page<>(cmRecordDto.getPage(), cmRecordDto.getSize());
        CmRecord cmRecord = this.convertMapper.toCmRecord(cmRecordDto);
        page = this.cmRecordService.page(page, new QueryWrapper<>(cmRecord));
        IPage<CmRecordVo> iPage = page.convert(this.convertMapper::toCmRecordVo);
        return Result.ok(iPage);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result<CmRecordVo> selectOne(@PathVariable Serializable id) {
        return Result.ok(this.convertMapper.toCmRecordVo(this.cmRecordService.getById(id)));
    }

    /**
     * 新增数据
     *
     * @param cmRecordDto 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result<CmRecordVo> insert(@RequestBody CmRecordDto cmRecordDto) {
        CmRecord cmRecord = this.convertMapper.toCmRecord(cmRecordDto);
        boolean save = this.cmRecordService.save(cmRecord);
        log.info("添加聊天记录:{},是否成功{}", cmRecordDto, save);
        if (save) {
            return Result.ok(this.convertMapper.toCmRecordVo(cmRecord), "操作成功");
        }
        return Result.fail("操作失败");
    }

    /**
     * 修改数据
     *
     * @param cmRecordDto 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result<CmRecordVo> update(@RequestBody CmRecordDto cmRecordDto) {
        CmRecord cmRecord = this.convertMapper.toCmRecord(cmRecordDto);
        boolean update = this.cmRecordService.updateById(cmRecord);
        log.info("修改聊天记录:{},是否成功{}", cmRecordDto, update);
        if (update) {
            return Result.ok(this.convertMapper.toCmRecordVo(cmRecord), "操作成功");
        }
        return Result.fail("操作失败");
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public Result<Boolean> delete(@RequestParam("idList") List<Long> idList) {
        log.info("删除聊天记录:{}", idList.toString());
        return Result.ok(this.cmRecordService.removeByIds(idList));
    }
}

