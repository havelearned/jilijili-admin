package top.jilijili.comment.recommendation;

import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RecommendationStrategy {

    /**
     * 通过文件模型
     *
     * @param id
     * @param haoMany
     * @return
     */
    List<RecommendedItem> recommend(Long id, int haoMany);

    /**
     * 通过用户或者物品模型
     *
     * @param id
     * @param haoMany
     * @param data
     * @return
     */
    List<RecommendedItem> recommend(Long id, int haoMany, FastByIDMap<PreferenceArray> data);


    /**
     * 随机推荐
     *
     * @param id
     * @param haoMany
     * @param data
     * @return
     */
    List<RecommendedItem> guaranteedRecommendation(Long id, int haoMany, FastByIDMap<PreferenceArray> data);


}
