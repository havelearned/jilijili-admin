package top.jilijili.module.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * 系统配置表
 *
 * @TableName sys_config
 */

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SysConfigDto extends SuperDto {
    /**
     * 主键
     */

    private Long configId;

    /**
     * 创建者id
     */

    private String userId;

    /**
     * 配置名称
     */

    private String configName;

    /**
     * 配置类型:1 web admin系统,2 app系统,3 web 应用
     */

    private Integer configType;

    /**
     * JSON格式
     */

    private String configJson;

    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 更新时间
     */
    private Date updatedTime;
}