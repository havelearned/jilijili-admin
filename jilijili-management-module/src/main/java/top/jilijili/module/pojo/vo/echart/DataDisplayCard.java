package top.jilijili.module.pojo.vo.echart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class DataDisplayCard {
    private String id;
    private String title;
    private Long value;
    private String avg;
    private String otherTitle;
    // x 抽数据
    private String[] xData;
    // 显示数据
    private Long[] seriesData;
    // 提示Label
    private String[] legendData;


}
