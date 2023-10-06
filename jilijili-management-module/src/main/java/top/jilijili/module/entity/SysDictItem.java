package top.jilijili.module.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import top.jilijili.common.group.Query;
import top.jilijili.module.entity.dto.SuperDto;

import java.io.Serializable;

/**
 * 字典item表
 *
 * @TableName sys_dict_item
 */
@TableName(value = "sys_dict_item")
@Data
public class SysDictItem extends SuperDto implements Serializable {
    /**
     *
     */
    @TableId(value = "dict_item_id", type = IdType.ASSIGN_ID)
    private Long dictItemId;

    /**
     * 字段类型
     */
    @TableField(value = "dictionary_type")
    @NotNull(message = "字典类型不能为空", groups = Query.class)
    private String dictionaryType;


    /**
     * 字段项值
     */
    @TableField(value = "item_label")
    private String itemLabel;

    /**
     * 字段项值
     */
    @TableField(value = "item_value")
    private String itemValue;

    /**
     * 排序
     */
    @TableField(value = "item_order")
    private Integer itemOrder;

    /**
     * 状态:1开启 0停用
     */
    @TableField(value = "status")
    private Integer status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}