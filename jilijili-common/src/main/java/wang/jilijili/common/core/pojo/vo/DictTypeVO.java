package wang.jilijili.common.core.pojo.vo;

import lombok.Data;
import wang.jilijili.common.core.pojo.entity.SysDictData;

import java.util.List;

/**
 * @author Amani
 * @date 2023年05月02日 23:14
 */
@Data
public class DictTypeVO {

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

    private String remark;

    private List<SysDictData> sysDictDataList;
}
