package wang.jilijili.common.core.pojo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import wang.jilijili.common.group.Insert;
import wang.jilijili.common.group.Updata;

/**
 * @author Amani
 * @date 2023年04月29日 11:48
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysDictTypeDto extends QueryDto {

    /**
     * 字典主键
     */
    @NotNull(message = "字典类型不能为空", groups = Updata.class)
    @NotBlank(message = "字典类型不能为空", groups = Updata.class)
    private Long dictId;

    /**
     * 字典名称
     */
    @NotNull(message = "字典类型不能为空", groups = Insert.class)
    @NotBlank(message = "字典类型不能为空", groups = Insert.class)
    private String dictName;

    /**
     * 字典类型
     */
    @NotNull(message = "字典类型不能为空", groups = Insert.class)
    @NotBlank(message = "字典类型不能为空", groups = Insert.class)
    private String dictType;


    /**
     * 备注
     */
    private String remark;

}
