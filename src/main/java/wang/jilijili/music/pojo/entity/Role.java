package wang.jilijili.music.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色表
 *
 * @TableName role
 */

@TableName(value = "role")
@Data
public class Role extends SuperEntity implements Serializable {


    @TableId(type = IdType.INPUT)
    @TableField(fill = FieldFill.INSERT)
    protected String id;

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