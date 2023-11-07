package top.jilijili.module.pojo.vo.echart;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Amani
 * @date 2023年11月06日 23:20
 */
@Data
@Accessors(chain = true)
public class SeriesObject {
        private String name;
        private String type;
        private String stack;
        private Number[] data;
}
