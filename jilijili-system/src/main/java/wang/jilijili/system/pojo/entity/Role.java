package wang.jilijili.system.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import wang.jilijili.common.pojo.entity.SuperEntity;

import java.io.Serializable;

/**
 * @author admin
 * @TableName role 角色表
 */

@TableName(value = "role")
@Schema(title = "角色", name = "角色实体类")
@Data
public class Role extends SuperEntity implements Serializable {


    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色标识
     */
    private String title;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}