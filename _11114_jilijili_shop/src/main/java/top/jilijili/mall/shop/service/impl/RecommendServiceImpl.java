package top.jilijili.mall.shop.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.jilijili.comment.recommendation.RecommendationFactory;
import top.jilijili.comment.recommendation.RecommendationStrategy;
import top.jilijili.mall.shop.service.RecommendService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Amani
 * @date 2023年10月22日 13:46
 */
@Slf4j
@Service
public class RecommendServiceImpl implements RecommendService {



    @Value("${cf.model}")
    private String CFMode;


    /**
     * 默认使用文件模型
     *
     * @param itemId  推荐的用户Id
     * @param haoMany 推荐多少条数据
     * @return 推荐的商品列表
     */
    @Override
    public List<Long> recommend(Long itemId, Integer haoMany) {
        RecommendationStrategy strategy = RecommendationFactory.getRecommendationStrategy(CFMode);
        List<RecommendedItem> recommend = strategy.recommend(itemId, haoMany);
        return recommend.stream().map(RecommendedItem::getItemID).collect(Collectors.toList());

    }

    /**
     * 通过用户或者物品推荐
     *
     * @param id      用户id
     * @param haoMany 推荐大小
     * @param data    源数据,建议1000-100之间
     * @return
     */
    @Override
    public List<Long> recommendByByItem(Long id, int haoMany, FastByIDMap<PreferenceArray> data) {
        RecommendationStrategy strategy = RecommendationFactory.getRecommendationStrategy(CFMode);
        List<RecommendedItem> recommend = strategy.recommend(id, haoMany, data);
        return recommend.stream().map(RecommendedItem::getItemID).collect(Collectors.toList());
    }








}
