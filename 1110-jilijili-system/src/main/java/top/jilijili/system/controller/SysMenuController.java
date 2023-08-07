package top.jilijili.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.jilijili.system.entity.SysMenu;
import top.jilijili.system.entity.dto.SysMenuDto;
import top.jilijili.system.entity.vo.Result;
import top.jilijili.system.entity.vo.SysMenuVo;
import top.jilijili.system.mapper.ConvertMapper;
import top.jilijili.system.service.SysMenuService;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单权限表(SysMenu)表控制层
 *
 * @author makejava
 * @since 2023-08-03 22:28:49
 */
@RestController
@RequestMapping("/sysMenu")
@AllArgsConstructor
public class SysMenuController extends SuperController {
    /**
     * 服务对象
     */
    private SysMenuService sysMenuService;
    private ConvertMapper convertMapper;

    /**
     * 分页查询所有数据
     *
     * @param sysMenuDto
     * @return
     */
    @GetMapping("/list")
    public Result<IPage<SysMenuVo>> selectAll(SysMenuDto sysMenuDto) {
        IPage<SysMenuVo> menuVoIpage = this.sysMenuService.selectAll(sysMenuDto);
        return Result.ok(menuVoIpage);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result<SysMenuVo> selectOne(@PathVariable Serializable id) {
        return Result.ok(this.convertMapper.toSysMenuVo(this.sysMenuService.getById(id)));
    }

    /**
     * 新增数据
     *
     * @param sysMenu 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result<SysMenuVo> insert(@RequestBody SysMenu sysMenu) {
        boolean save = this.sysMenuService.save(sysMenu);
        if (save) {
            return Result.ok(this.convertMapper.toSysMenuVo(sysMenu), "操作成功");
        }
        return Result.fail("操作失败");
    }

    /**
     * 修改数据
     *
     * @param sysMenu 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result<SysMenuVo> update(@RequestBody SysMenu sysMenu) {
        boolean update = this.sysMenuService.updateById(sysMenu);
        if (update) {
            return Result.ok(this.convertMapper.toSysMenuVo(sysMenu), "操作成功");
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
    public Result<?> delete(@RequestParam("idList") List<Long> idList) {
        return Result.ok(this.sysMenuService.removeByIds(idList));
    }
}
