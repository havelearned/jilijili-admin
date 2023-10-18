package top.jilijili.module.pojo.vo.sys;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author Amani
 * @date 2023年06月22日 13:51
 */
@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysRoleVo {

    private String roleId;

    /**
     * 角色名称
     */
    private String roleName;


    /**
     * 角色标识
     */
    private String title;

    private String icon;

    private Date createdTime;

    /**
     * 更新时间
     */

    private Date updatedTime;
}
