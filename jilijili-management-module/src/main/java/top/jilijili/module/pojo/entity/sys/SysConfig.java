package top.jilijili.module.pojo.entity.sys;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统配置表
 *
 * @TableName sys_config
 */
@TableName(value = "sys_config")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SysConfig implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "config_id", type = IdType.AUTO)
    private Long configId;

    /**
     * 创建者id
     */
    @TableField(value = "user_id")
    private String userId;

    /**
     * 配置名称
     */
    @TableField(value = "config_name")
    private String configName;

    /**
     * 配置类型:1 web admin系统,2 app系统,3 web 应用
     */
    @TableField(value = "config_type")
    private Integer configType;

    /**
     * JSON格式
     */
    @TableField(value = "config_json")
    private String configJson;

    /**
     * 创建时间
     */
    @TableField(value = "created_time", fill = FieldFill.INSERT)
    private Date createdTime;

    /**
     * 更新时间
     */
    @TableField(value = "updated_time", fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}