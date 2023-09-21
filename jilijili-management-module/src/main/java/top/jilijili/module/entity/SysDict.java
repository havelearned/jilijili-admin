package top.jilijili.module.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@TableName(value = "sys_dict")
@Data
public class SysDict implements Serializable {
    /**
     *
     */
    @TableId(value = "dictionary_id", type = IdType.AUTO)
    private Long dictionaryId;

    /**
     * 字段类型
     */
    @TableField(value = "dictionary_type")
    private String dictionaryType;

    /**
     * 字段代码
     */
    @TableField(value = "dictionary_code")
    private String dictionaryCode;

    /**
     * 字段项名称
     */
    @TableField(value = "dictionary_item_name")
    private String dictionaryItemName;

    /**
     * 字段项值
     */
    @TableField(value = "dictionary_item_value")
    private String dictionaryItemValue;

    /**
     * 排序
     */
    @TableField(value = "dictionary_item_order")
    private Integer dictionaryItemOrder;

    /**
     * 状态:1开启 0停用
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 创建时间
     */
    @TableField(value = "created_time", fill = FieldFill.INSERT)
    private Date createdTime;

    /**
     * 更新时间
     */
    @TableField(value = "updated_time", fill = FieldFill.UPDATE)
    private Date updatedTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}