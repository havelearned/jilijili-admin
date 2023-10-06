package top.jilijili.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import top.jilijili.module.entity.SysMenu;
import top.jilijili.module.entity.SysRoleMenu;
import top.jilijili.module.entity.dto.SysMenuDto;
import top.jilijili.module.entity.dto.SysRoleMenuDto;
import top.jilijili.module.entity.vo.SysMenuVo;
import top.jilijili.system.heandler.JiliException;
import top.jilijili.system.mapper.ConvertMapper;
import top.jilijili.system.mapper.SysMenuMapper;
import top.jilijili.system.service.SysMenuService;
import top.jilijili.system.service.SysRoleMenuService;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author admin
 * @description 针对表【sys_menu(菜单权限表)】的数据库操作Service实现
 * @createDate 2023-06-22 22:03:11
 */
@Service
@Slf4j
@AllArgsConstructor
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu>
        implements SysMenuService {

    private ConvertMapper convertMapper;
    private SysMenuMapper sysMenuMapper;
    private SysRoleMenuService sysRoleMenuService;


    @Transactional(rollbackFor = JiliException.class, propagation = Propagation.REQUIRES_NEW)
    @Override
    public boolean bindingMenuAndRole(SysRoleMenuDto sysRoleMenuDto) {
        // 删除角色菜单
        this.sysRoleMenuService.remove(new LambdaQueryWrapper<SysRoleMenu>()
                .eq(SysRoleMenu::getRoleId, sysRoleMenuDto.getRoleId()));
        log.info("删除角色的菜单:{}", sysRoleMenuDto.toString());

        // 重新添加角色菜单
        List<SysRoleMenu> collect = sysRoleMenuDto.getMenuIds().stream()
                .map(menuId -> SysRoleMenu.builder()
                        .roleId(sysRoleMenuDto.getRoleId())
                        .menuId(menuId).build())
                .collect(Collectors.toList());

        // 要绑定菜单的子菜单列表
        List<Long> menuIds = sysRoleMenuDto.getMenuIds();
        List<SysMenu> list = this.lambdaQuery().in(SysMenu::getParentId, menuIds).list();
        if (!list.isEmpty()) {
            list.forEach(item -> {
                SysRoleMenu build = SysRoleMenu.builder()
                        .roleId(sysRoleMenuDto.getRoleId())
                        .menuId(item.getMenuId()).build();
                collect.add(build);
            });
        }

        try {
            log.info("菜单重新绑定菜单:{}", collect.toString());
            return this.sysRoleMenuService.saveBatch(collect);
        } catch (JiliException e) {
            log.error(e.getMessage());
        }
        return false;
    }

    @Override
    public IPage<SysMenuVo> selectAll(SysMenuDto sysMenuDto) {
        IPage<SysMenu> page = new Page<>(sysMenuDto.getPage(), 1000);
        IPage<SysMenuVo> voIPage = this.lambdaQuery()
                .like(StringUtils.hasText(sysMenuDto.getMenuName()), SysMenu::getMenuName, sysMenuDto.getMenuName())
                .orderBy(true, false, SysMenu::getCreatedTime)
                .page(page).convert(this.convertMapper::toSysMenuVo);
        // 带参数查询直接返回,搜索到的内容
        if (StringUtils.hasText(sysMenuDto.getMenuName())) {
            return voIPage;
        }
        // 得到最顶层菜单
        List<SysMenuVo> records = voIPage.getRecords();
        List<SysMenuVo> parentList = records.stream()
                .filter(item -> Objects.equals(item.getParentId(), "0"))
                .sorted(Comparator.comparing(SysMenuVo::getOrderNum))
                .collect(Collectors.toList());
        // 设置顶层的子菜单
        for (SysMenuVo next : parentList) {
            next.setChildren(this.getChildren(next.getMenuId(), records));
        }

        voIPage.setRecords(parentList);
        return voIPage;
    }

    @Override
    public List<SysMenuVo> getRoleMenuList(Serializable id) {
        return this.sysMenuMapper.queryRoleMenuListByRoleId(id);

    }


    /**
     * @param menuId  当前菜单id
     * @param records 要查找的列表
     * @return
     */
    private List<SysMenuVo> getChildren(String menuId, List<SysMenuVo> records) {
        // 子菜单列表
        List<SysMenuVo> chilendList = new ArrayList<>();
        // 子菜单的子集
        for (SysMenuVo sysMenuVo : records) {
            if (Objects.equals(sysMenuVo.getParentId(), menuId)) {
                chilendList.add(sysMenuVo);
            }
        }
        // 子菜单再次循环
        for (SysMenuVo children : chilendList) {
            children.setChildren(getChildren(children.getMenuId(), records));
        }

        if (chilendList.isEmpty()) {
            return Collections.emptyList();
        }

        chilendList = chilendList.stream().sorted(Comparator.comparing(SysMenuVo::getOrderNum)).collect(Collectors.toList());
        return chilendList;
    }


}




