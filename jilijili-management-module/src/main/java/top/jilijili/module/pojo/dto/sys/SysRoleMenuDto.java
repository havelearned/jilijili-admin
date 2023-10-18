package top.jilijili.module.pojo.dto.sys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 角色和菜单关联表
 *
 * @TableName sys_role_menu
 */
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class SysRoleMenuDto implements Serializable {
    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID
     */
    private Long menuId;

    // 绑定菜单列表
    private List<Long> menuIds;
}