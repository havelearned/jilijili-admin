package top.jilijili.system.common.enums;

import lombok.Getter;

/**
 * 用户角色
 *
 * @author Amani
 * @date 2023年06月22日 14:31
 */
@Getter
public enum RoleEnum {

    SYS_ADMIN("SYS_ADMIN", "管理员", 1L),
    SYS_USER("SYS_USER", "普通用户", 2L),
    SYS_VISITOR("SYS_VISITOR", "游客", 3L);


    private final String roleName;
    private final String title;
    private final Long id;

    RoleEnum(String roleName, String title, Long id) {
        this.roleName = roleName;
        this.title = title;
        this.id = id;
    }
}