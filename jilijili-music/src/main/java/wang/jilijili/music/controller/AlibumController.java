package wang.jilijili.music.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.web.bind.annotation.*;
import wang.jilijili.common.annotation.JilJilOperationLog;
import wang.jilijili.common.core.pojo.vo.Result;
import wang.jilijili.common.enums.OperationType;
import wang.jilijili.music.pojo.bo.AlibumConvertBo;
import wang.jilijili.music.pojo.dto.AlibumDto;
import wang.jilijili.music.pojo.entity.Alibum;
import wang.jilijili.music.pojo.vo.AlibumVo;
import wang.jilijili.music.service.AlibumService;

import java.util.List;

import static wang.jilijili.common.constant.ModuleNameConstant.MUSIC_MANAGE;
import static wang.jilijili.common.constant.RoleConstant.ROLE_SUPER_ADMIN;

/**
 * 专辑表控制层
 *
 * @author amani
 * @since 2023-03-21 15:21:52
 */
@RestController
@RequestMapping("/alibum")
@Tag(name = "专辑管理模块")
public class AlibumController {
    /**
     * 服务对象
     */

    AlibumService alibumService;

    AlibumConvertBo alibumConvertBo;

    public AlibumController(AlibumService alibumService, AlibumConvertBo alibumConvertBo) {
        this.alibumService = alibumService;
        this.alibumConvertBo = alibumConvertBo;
    }

    /**
     * 分页查询
     *
     * @param alibumDto 筛选条件
     * @return 查询结果
     */
    @GetMapping("/list")
    public Result<IPage<AlibumVo>> queryByPage(AlibumDto alibumDto) {
        IPage<Alibum> page = new Page<>(alibumDto.getPage(), alibumDto.getSize());

        Alibum alibum = alibumConvertBo.toAlibum(alibumDto);

        IPage<AlibumVo> voIpage = this.alibumService.page(page, new QueryWrapper<>(alibum))
                .convert(item -> this.alibumConvertBo.toAlibumVo(this.alibumConvertBo.toAlibumDto(item)));
        return Result.ok(voIpage);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public Result<AlibumVo> queryById(@PathVariable("id") String id) {
        AlibumVo alibumVo =
                this.alibumConvertBo.toAlibumVo(
                        this.alibumConvertBo.toAlibumDto(
                                this.alibumService.getById(id)));

        return Result.ok(alibumVo);
    }

    /**
     * 新增数据
     *
     * @param alibumDto 实体
     * @return 新增结果
     */
    @PostMapping("/")
    @RolesAllowed(value = {ROLE_SUPER_ADMIN})
    @JilJilOperationLog(moduleName = MUSIC_MANAGE, type = OperationType.ADD)
    public Result<AlibumVo> add(AlibumDto alibumDto) {
        alibumDto = this.alibumService.create(alibumDto);
        return Result.ok(this.alibumConvertBo.toAlibumVo(alibumDto));
    }

    /**
     * 编辑数据
     *
     * @param alibumDto 实体
     * @return 编辑结果
     */
    @PutMapping("/")
    @RolesAllowed(value = {ROLE_SUPER_ADMIN})
    @JilJilOperationLog(moduleName = MUSIC_MANAGE, type = OperationType.UPDATE)
    public Result<AlibumVo> edit(AlibumDto alibumDto) {
        alibumDto = this.alibumService.update(alibumDto);
        return Result.ok(this.alibumConvertBo.toAlibumVo(alibumDto));
    }

    /**
     * 删除数据
     *
     * @param idList 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/")
    @RolesAllowed(value = {ROLE_SUPER_ADMIN})
    @JilJilOperationLog(moduleName = MUSIC_MANAGE, type = OperationType.DELETED)
    public Result<?> delete(@RequestParam("idList") List<String> idList) {
        return Result.ok(this.alibumService.removeByIds(idList));
    }

}

