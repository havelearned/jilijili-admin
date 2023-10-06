package top.jilijili.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import top.jilijili.module.entity.SysRole;
import top.jilijili.module.entity.SysRoleMenu;
import top.jilijili.system.heandler.JiliException;
import top.jilijili.system.mapper.SysRoleMapper;
import top.jilijili.system.mapper.SysRoleMenuMapper;
import top.jilijili.system.service.SysRoleService;

import java.util.List;

/**
 * @author admin
 * @description 针对表【sys_role(角色表)】的数据库操作Service实现
 * @createDate 2023-06-22 13:48:04
 */
@Service
@AllArgsConstructor
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole>
        implements SysRoleService {

    private SysRoleMenuMapper sysRoleMenuMapper;


    @Transactional(rollbackFor = JiliException.class, propagation = Propagation.REQUIRES_NEW)
    @Override
    public Boolean removeRoleByRoleIds(List<Long> ids) {
        if (ids.isEmpty()) {
            return true;
        }
        // 删除角色信息
        boolean removed = this.removeByIds(ids);
        // 删除角色关联的菜单信息
        this.sysRoleMenuMapper.delete(new LambdaQueryWrapper<SysRoleMenu>()
                .in(SysRoleMenu::getRoleId, ids));
        return removed;
    }
}




