package top.jilijili.module.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * 字典item Vo
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SysDictItemVo implements Serializable {
    /**
     *
     */
    private String dictItemId;

    /**
     * 字段类型
     */
    private String dictionaryType;


    /**
     * 字段项值
     */
    private String itemLabel;

    /**
     * 字段项值
     */
    private String itemValue;

    /**
     * 排序
     */
    private Integer itemOrder;

    /**
     * 状态:1开启 0停用
     */
    private Integer status;

}