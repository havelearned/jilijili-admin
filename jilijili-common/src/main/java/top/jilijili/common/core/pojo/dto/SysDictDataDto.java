package top.jilijili.common.core.pojo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.jilijili.common.group.Insert;
import top.jilijili.common.group.Updata;

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
    @NotNull(message = "字典类型不能为空", groups = Updata.class)
    @NotBlank(message = "字典类型不能为空", groups = Updata.class)
    private Long dictCode;

    /**
     * 字典排序
     */
    private Integer dictSort;

    /**
     * 字典标签
     */
    @NotNull(message = "字典类型不能为空", groups = Insert.class)
    @NotBlank(message = "字典类型不能为空", groups = Insert.class)
    private String dictLabel;

    /**
     * 字典键值
     */
    private String dictValue;

    /**
     * 字典类型
     */
    @NotNull(message = "字典类型不能为空", groups = Insert.class)
    @NotBlank(message = "字典类型不能为空", groups = Insert.class)
    private String dictType;

    /**
     * 状态（0正常 1停用）
     */
    private Integer status;

    /**
     * 备注
     */

    private String remark;

}
