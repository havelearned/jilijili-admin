package wang.jilijili.common.core.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Long dictId;

    /**
     * 字典名称
     */
    private String dictName;

    /**
     * 字典类型
     */
    private String dictType;


    /**
     * 备注
     */
    private String remark;

}
