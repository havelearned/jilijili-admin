package wang.jilijili.music.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import wang.jilijili.common.annotation.JilJilOperationLog;
import wang.jilijili.common.core.controller.BaseController;
import wang.jilijili.common.core.pojo.vo.Result;
import wang.jilijili.common.enums.OperationType;
import wang.jilijili.music.mapper.SingerMapper;
import wang.jilijili.music.pojo.bo.SingerConvertBo;
import wang.jilijili.music.pojo.dto.SingerDto;
import wang.jilijili.music.pojo.entity.Singer;
import wang.jilijili.music.pojo.vo.SingerVo;
import wang.jilijili.music.service.SingerService;

import java.io.Serializable;
import java.util.List;

import static wang.jilijili.common.constant.ModuleNameConstant.MUSIC_MANAGE;
import static wang.jilijili.common.constant.RoleConstant.ROLE_SUPER_ADMIN;

/**
 * 歌手控制器
 *
 * @author makejava
 * @since 2023-03-20 23:33:45
 */
@RestController
@RequestMapping("/singer")
@Tag(name = "歌手管理")
public class SingerController extends BaseController<SingerMapper> {
    /**
     * 服务对象
     */
    private final SingerService singerService;

    private final SingerConvertBo singerConvertBo;

    public SingerController(SingerService singerService, SingerConvertBo singerConvertBo) {
        this.singerService = singerService;
        this.singerConvertBo = singerConvertBo;
    }

    /**
     * 分页查询所有数据
     *
     * @param singerDto 查询条件
     * @return wang.jilijili.common.core.pojo.vo.Result<com.baomidou.mybatisplus.core.metadata.IPage < wang.jilijili.music.pojo.entity.Singer>>
     * @author Amani
     * @date 2023/3/21 14:26
     */
    @GetMapping("/list")
    public Result<IPage<Singer>> selectAll(SingerDto singerDto) {
        IPage<Singer> page = new Page<>(singerDto.getPage(), singerDto.getSize());
        Singer singer = this.singerConvertBo.toSinger(singerDto);
        return Result.ok(this.singerService.page(page, new QueryWrapper<>(singer)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public Result<SingerVo> selectOne(@PathVariable Serializable id) {
        Singer singer = this.singerService.getById(id);
        SingerDto singerDto = this.singerConvertBo.toSingerDto(singer);
        return Result.ok(this.singerConvertBo.toSingerVo(singerDto));
    }

    /**
     * 新增数据
     *
     * @param singerDto 实体对象
     * @return 新增结果
     */
    @PostMapping("/")
    @RolesAllowed(value = {ROLE_SUPER_ADMIN})
    @JilJilOperationLog(moduleName = MUSIC_MANAGE, type = OperationType.ADD)
    public Result<?> insert(@RequestBody SingerDto singerDto) {
        singerDto = this.singerService.create(singerDto);
        return Result.ok(this.singerConvertBo.toSingerVo(singerDto));

    }

    /**
     * 修改数据
     *
     * @param singerDto 实体对象
     * @return 修改结果
     */
    @PutMapping("/")
    @RolesAllowed(value = {ROLE_SUPER_ADMIN})
    @JilJilOperationLog(moduleName = MUSIC_MANAGE, type = OperationType.UPDATE)
    public Result<?> update(@RequestBody SingerDto singerDto) {
        singerDto = this.singerService.update(singerDto);
        return Result.ok(this.singerConvertBo.toSingerVo(singerDto));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/")
    @RolesAllowed(value = {ROLE_SUPER_ADMIN})
    @JilJilOperationLog(moduleName = MUSIC_MANAGE, type = OperationType.DELETED)
    public Result<?> delete(@RequestParam("idList") List<String> idList) {
        return Result.ok(this.singerService.removeByIds(idList));
    }

    /**
     * 导出歌手信息
     *
     * @param singerDto 导出条件
     * @param response 响应
     * @author Amani
     * @date 2023/3/21 14:30
     */
    @GetMapping("/export")
    @RolesAllowed(value = {ROLE_SUPER_ADMIN})
    @JilJilOperationLog(moduleName = MUSIC_MANAGE, type = OperationType.EXPORT)
    public void export(@RequestBody SingerDto singerDto, HttpServletResponse response) {
        super.export(singerDto, response);
    }

}
