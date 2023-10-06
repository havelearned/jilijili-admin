package top.jilijili.system.controller.sys;


import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.jilijili.module.entity.SysMenu;
import top.jilijili.module.entity.dto.SysMenuDto;
import top.jilijili.module.entity.dto.SysRoleMenuDto;
import top.jilijili.module.entity.vo.Result;
import top.jilijili.module.entity.vo.SysMenuVo;
import top.jilijili.system.controller.SuperController;
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
     * 通过id角色获取菜单列表
     *
     * @param id
     * @return
     */
    @GetMapping("/getRoleMenuList/{id}")
    public Result<List<SysMenuVo>> getRoleMenuList(@PathVariable("id") Serializable id) {
        List<SysMenuVo> menuList = this.sysMenuService.getRoleMenuList(id);
        return Result.ok(menuList);
    }

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
     * 角色重新绑定菜单
     *
     * @param sysRoleMenuDto
     * @return
     */
    @PostMapping("/bindingRole")
    public Result<?> bindingMenuAndRole(@RequestBody SysRoleMenuDto sysRoleMenuDto) {
        boolean isSuccess = this.sysMenuService.bindingMenuAndRole(sysRoleMenuDto);
        return isSuccess ? Result.ok("操作成功") : Result.fail("操作失败");


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
    public Result<?> delete(@RequestBody List<Serializable> idList) {
        return Result.ok(this.sysMenuService.removeByIds(idList));
    }
}

