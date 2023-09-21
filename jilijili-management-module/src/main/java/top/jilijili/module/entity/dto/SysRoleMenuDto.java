package top.jilijili.module.entity.dto;

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

    private List<Long> menuIds;
}