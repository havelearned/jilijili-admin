package wang.jilijili.common.core.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Amani
 * @date 2023年04月29日 11:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysDictDataDto extends QueryDto {
    /**
     * 字典编码
     */
    private Long dictCode;

    /**
     * 字典排序
     */
    private Integer dictSort;

    /**
     * 字典标签
     */
    private String dictLabel;

    /**
     * 字典键值
     */
    private String dictValue;

    /**
     * 字典类型
     */
    private String dictType;

    /**
     * 状态（0正常 1停用）
     */
    private String status;

    /**
     * 备注
     */

    private String remark;

}
