package top.jilijili.module.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * 字典表
 *
 * @TableName sys_dict
 */
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Accessors(chain = true)
public class SysDictDto extends SuperDto implements Serializable {
    /**
     *
     */
    private Long dictionaryId;

    /**
     * 字段类型
     */
    private String dictionaryType;

    /**
     * 字段代码
     */
    private String dictionaryCode;

    /**
     * 字段项名称
     */
    private String dictionaryItemName;

    /**
     * 字段项值
     */
    private String dictionaryItemValue;

    /**
     * 排序
     */
    private Integer dictionaryItemOrder;

    /**
     * 状态:1开启 0停用
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date updatedTime;

}