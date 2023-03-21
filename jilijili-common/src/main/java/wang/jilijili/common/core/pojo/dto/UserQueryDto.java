package wang.jilijili.common.core.pojo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author Amani
 * @date 2023年02月12日 22:13
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class UserQueryDto extends QueryDto implements Serializable {

    private String id;

    // TODO 构建查询字段
    /**
     * 用户名
     */
    private String username;

    /**
     * 用户昵称
     */
    private String nickname;


    /**
     * 性别
     */
    private String gender;

    /**
     * 是否可用,1-是,0-否
     */
    private Integer unseal = 1;


}
