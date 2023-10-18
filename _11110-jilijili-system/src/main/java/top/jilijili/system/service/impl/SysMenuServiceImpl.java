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
import top.jilijili.module.pojo.entity.sys.SysMenu;
import top.jilijili.module.pojo.entity.sys.SysRoleMenu;
import top.jilijili.module.pojo.dto.sys.SysMenuDto;
import top.jilijili.module.pojo.dto.sys.SysRoleMenuDto;
import top.jilijili.module.pojo.vo.sys.SysMenuVo;
import top.jilijili.common.heandler.JiliException;;
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


    /**
     * 绑定菜单
     *
     * @param sysRoleMenuDto
     * @return
     */
    @Transactional(rollbackFor = JiliException.class, propagation = Propagation.REQUIRES_NEW)
    @Override
    public boolean bindingMenuAndRole(SysRoleMenuDto sysRoleMenuDto) {
        // 删除角色菜单
        this.sysRoleMenuService.remove(new LambdaQueryWrapper<SysRoleMenu>()
                .eq(SysRoleMenu::getRoleId, sysRoleMenuDto.getRoleId()));
        log.info("删除角色的菜单:{}", sysRoleMenuDto.toString());


        log.info("菜单重新绑定菜单:{}", sysRoleMenuDto.getMenuIds());
        List<SysRoleMenu> collect = sysRoleMenuDto.getMenuIds().stream()
                .map(menuId -> SysRoleMenu.builder()
                        .roleId(sysRoleMenuDto.getRoleId())
                        .menuId(menuId).build()).collect(Collectors.toList());
        return this.sysRoleMenuService.saveBatch(collect);
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
    public Map<String, Object> getRoleMenuList(Serializable id) {
        List<SysMenuVo> resulList = this.sysMenuMapper.queryRoleMenuListByRoleId(id);
        // 当前菜单的勾选条目
        List<String> selected = resulList.stream().map(SysMenuVo::getMenuId).toList();

        // 当前不可选条目
        SysMenuDto sysMenuDto = SysMenuDto.builder()
                .page(0)
                .size(1000).build();
        IPage<SysMenuVo> result = this.selectAll(sysMenuDto);
        resulList = result.getRecords();

        Map<String, Object> resulMap = new HashMap<>(3);
        resulMap.put("ticked", selected);
        resulMap.put("treeMenu", resulList);
        resulMap.put("expanded", selected);
        return resulMap;

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




