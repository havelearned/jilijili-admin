package top.jilijili.system.entity.vo;

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

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SysConfigVo implements Serializable {
    /**
     * 主键
     */

    private String configId;

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