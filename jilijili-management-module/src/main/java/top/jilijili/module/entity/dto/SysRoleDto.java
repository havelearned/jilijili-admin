package top.jilijili.module.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * @author Amani
 * @date 2023年06月22日 13:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Accessors(chain = true)
public class SysRoleDto extends SuperDto {
    private Long roleId;

    private List<Long> menuIds;

    /**
     * 角色名称
     */
    private String roleName;


    /**
     * 角色标识
     */
    private String title;


    private String icon;
}
