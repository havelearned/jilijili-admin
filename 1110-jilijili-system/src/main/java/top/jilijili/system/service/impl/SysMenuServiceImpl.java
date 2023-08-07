package top.jilijili.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.jilijili.system.entity.SysMenu;
import top.jilijili.system.entity.dto.SysMenuDto;
import top.jilijili.system.entity.vo.SysMenuVo;
import top.jilijili.system.mapper.ConvertMapper;
import top.jilijili.system.mapper.SysMenuMapper;
import top.jilijili.system.service.SysMenuService;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author admin
 * @description 针对表【sys_menu(菜单权限表)】的数据库操作Service实现
 * @createDate 2023-06-22 22:03:11
 */
@Service
@AllArgsConstructor
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu>
        implements SysMenuService {

    private ConvertMapper convertMapper;

    @Override
    public IPage<SysMenuVo> selectAll(SysMenuDto sysMenuDto) {
        IPage<SysMenu> page = new Page<>(sysMenuDto.getPage(), sysMenuDto.getSize());
        SysMenu sysMenu = this.convertMapper.toSysMenuEntity(sysMenuDto);
        LambdaQueryWrapper<SysMenu> q = new LambdaQueryWrapper<>(sysMenu);
        q.like(StringUtils.hasText(sysMenu.getMenuName()), SysMenu::getMenuName, sysMenu.getMenuName())
                .orderBy(true, false, SysMenu::getCreatedTime);
        IPage<SysMenuVo> voIPage = this.page(page, q).convert(this.convertMapper::toSysMenuVo);
        List<SysMenuVo> records = voIPage.getRecords();
        // 得到最顶层菜单
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




