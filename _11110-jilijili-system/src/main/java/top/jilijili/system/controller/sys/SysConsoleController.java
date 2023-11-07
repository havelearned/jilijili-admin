package top.jilijili.system.controller.sys;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson2.JSON;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.icepear.echarts.Bar;
import org.icepear.echarts.Line;
import org.icepear.echarts.Option;
import org.icepear.echarts.charts.line.LineAreaStyle;
import org.icepear.echarts.charts.line.LineSeries;
import org.icepear.echarts.components.coord.cartesian.CategoryAxis;
import org.icepear.echarts.render.Engine;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.jilijili.common.entity.Result;
import top.jilijili.module.pojo.vo.echart.DataDisplayCard;
import top.jilijili.module.pojo.vo.echart.SeriesObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

/**
 * 统计数据
 *
 * @author Amani
 * @date 2023年11月06日 11:31
 */
@Slf4j
@RestController
@RequestMapping("/console")
@AllArgsConstructor
public class SysConsoleController {
    private SqlSessionTemplate sqlSessionTemplate;

    private ExecutorService customizeThreadPool;

    private SimpMessagingTemplate simpMessagingTemplate;


    /**
     * 发送订单信息
     *
     * @param orders 订单信息
     */
    @GetMapping("/sendOrderInfo")
    public void sendOrderInfo(@RequestParam Map<String, Object> orders) {
        // 只给管理员发送实时定订单交付信息
        if (Objects.nonNull(orders)) {
            simpMessagingTemplate
                    .convertAndSendToUser("1", "/queue/order", Result.ok(orders));
        }
    }

    /**
     * 获取头部卡片信息
     *
     * @return Result对象，包含头部卡片数据的List
     */
    @GetMapping("/headCard")
    public Result<List<DataDisplayCard>> headCard() {
        String[] array = new String[5];
        Long[] data = new Long[5];
        for (int i = 0; i < 5; i++) {
            data[i] = RandomUtil.randomLong(1000, 10000);
        }
        for (int i = 0; i < 5; i++) {
            array[i] = getData(i + 1);
        }
        List<CompletableFuture<DataDisplayCard>> futures = Arrays.asList(
                CompletableFuture.supplyAsync(() -> getVisits("card_1", "访问量:", "日平均访问量", array, data), customizeThreadPool),
                CompletableFuture.supplyAsync(this::getUserCount, customizeThreadPool),
                CompletableFuture.supplyAsync(() -> getVisits("card_3", "商城订单量:", "日平均订单量:", array, data), customizeThreadPool),
                CompletableFuture.supplyAsync(() -> getVisits("card_4", "博客评论量::", "日平均评论量:", array, data), customizeThreadPool)
        );
        List<DataDisplayCard> resultList = futures
                .stream()
                .map(CompletableFuture::join)
                .toList();
        customizeThreadPool.shutdown();
        return Result.ok(resultList);
    }

    /**
     * 折线图对象
     *
     * @return Result<jsonStr> 返回卡片数据的JSON字符串
     */
    @GetMapping("/centralLineChart")
    public Result<String> centralCard() {
        List<SeriesObject> so = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            SeriesObject seriesObject = new SeriesObject();
            seriesObject.setName("Email");
            seriesObject.setType("line");
            seriesObject.setStack("Total");
            Number[] numbers = new Number[7];
            for (int j = 0; j < numbers.length; j++) {
                numbers[j] = RandomUtil.randomLong(1000, 10000);
            }
            seriesObject.setData(numbers);
            so.add(seriesObject);
        }
        Line lineChart = new Line()
                .addXAxis(new CategoryAxis()
                        .setData(new String[]{"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"})
                        .setBoundaryGap(false))
                .addYAxis()
                .addSeries(new LineSeries()
                        .setData(so)
                        .setAreaStyle(new LineAreaStyle()));
        Option option = lineChart.getOption();
//        Engine engine = new Engine();
//        String jsonStr = engine.renderJsonOption(lineChart);
        return Result.ok(JSON.toJSONString(option));
    }

    /**
     * 获取中心直方图
     * @return 返回中心直方图的Json字符串
     */
    @GetMapping("/centralHistogram")
    public Result<String> centralHistogram() {
        // All methods in ECharts Java supports method chaining
        Bar bar = new Bar()
                .setLegend()
                .setTooltip("item")
                .addXAxis(new String[]{"Matcha Latte", "Milk Tea", "Cheese Cocoa", "Walnut Brownie"})
                .addYAxis()
                .addSeries("星期一", new Number[]{RandomUtil.randomInt(10, 10000)})
                .addSeries("星期二", new Number[]{RandomUtil.randomDouble(10, 10000)})
                .addSeries("星期三", new Number[]{RandomUtil.randomDouble(10, 10000)})
                .addSeries("星期四", new Number[]{RandomUtil.randomDouble(10, 10000)})
                .addSeries("星期五", new Number[]{RandomUtil.randomDouble(10, 10000)})
                .addSeries("星期六", new Number[]{RandomUtil.randomDouble(10, 10000)})
                .addSeries("星期七", new Number[]{RandomUtil.randomDouble(10, 10000)});
        Engine engine = new Engine();
        Option option = bar.getOption();
        String json = engine.renderJsonOption(option);
        return Result.ok(json);
    }


    /**
     * 获取用户量数据展示卡片
     *
     * @return 数据展示卡片
     */
    private DataDisplayCard getUserCount() {
        // 查询用户总数
        Long value = sqlSessionTemplate.selectOne("top.jilijili.system.mapper.SysUserMapper.queryUserCount");
        // 查询日期列表
        List<String> dateList = sqlSessionTemplate.selectList("top.jilijili.system.mapper.SysUserMapper.queryDate");
        // 查询数据列表
        List<Long> list = sqlSessionTemplate.selectList("top.jilijili.system.mapper.SysUserMapper.querySeriesData");
        // 查询平均值
        Long avg = sqlSessionTemplate.selectOne("top.jilijili.system.mapper.SysUserMapper.queryAvg");
        // 构建数据展示卡片
        return DataDisplayCard.builder()
                .id("card_2")
                .title("用户量:")
                .value(value)
                .otherTitle("日平均用户注册量")
                .xData(dateList.toArray(new String[dateList.size()]))
                .seriesData(list.toArray(new Long[list.size()]))
                .avg(avg + "%")
                .build();
    }

    /**
     * 获取访问量数据展示卡片
     *
     * @param id    卡片ID
     * @param title 卡片标题
     * @param avg   平均值
     * @param array x轴数据
     * @param data  系列数据
     * @return 数据展示卡片
     */
    private static DataDisplayCard getVisits(String id, String title, String avg, String[] array, Long[] data) {
        return DataDisplayCard.builder()
                .id(id)
                .title(title)
                .value(RandomUtil.randomLong(1000, 10000))
                .otherTitle(avg)
                .xData(array)
                .seriesData(data)
                .avg(RandomUtil.randomInt(0, 100) + "")
                .build();
    }

    /**
     * 获取指定天数的未来日期
     *
     * @param day 未来天数
     * @return 未来日期的字符串，格式为"yyyy-MM-dd"
     */
    public String getData(int day) {
        // 获取当前日期
        LocalDate currentDate = LocalDate.now();
        // 往后5天
        LocalDate futureDate = currentDate.plusDays(day);
        // 格式化日期为 "yyyy-MM-dd"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return futureDate.format(formatter);
    }


}
