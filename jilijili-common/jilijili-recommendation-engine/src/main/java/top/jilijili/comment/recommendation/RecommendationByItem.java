package top.jilijili.comment.recommendation;

import lombok.extern.slf4j.Slf4j;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.model.GenericDataModel;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.RandomRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * 基于物品
 */
@Component
@Slf4j
public class RecommendationByItem implements RecommendationStrategy {

    private FileDataModel fileDataModel;

    @Autowired
    public void setFileDatafileDataModel(FileDataModel fileDataModel) {
        this.fileDataModel = fileDataModel;
    }

    @Override
    public List<RecommendedItem> recommend(Long id, int haoMany) {
        try {

            //计算相似度，相似度算法有很多种，欧几里得、皮尔逊等等。
            ItemSimilarity itemSimilarity = new PearsonCorrelationSimilarity(fileDataModel);
            //构建推荐器，协同过滤推荐有两种，分别是基于用户的和基于物品的，这里使用基于物品的协同过滤推荐
            GenericItemBasedRecommender recommender = new GenericItemBasedRecommender(fileDataModel, itemSimilarity);
            List<RecommendedItem> recommended = recommender.mostSimilarItems(id, haoMany);
            if (recommended.isEmpty()) {
                FastByIDMap<PreferenceArray> dataMap = DataModelHelper.getDataMap(fileDataModel);
                recommended = guaranteedRecommendation(id, haoMany, dataMap);
                log.info("基于物品推荐结果为空，随机推荐");
            }
            return recommended;
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    /**
     * 通过用户或者物品模型
     *
     * @param id
     * @param haoMany
     * @param data
     * @return
     */
    @Override
    public List<RecommendedItem> recommend(Long id, int haoMany, FastByIDMap<PreferenceArray> data) {
        try {
            DataModel model = new GenericDataModel(data);
            //计算相似度，相似度算法有很多种，欧几里得、皮尔逊等等。
            ItemSimilarity itemSimilarity = new PearsonCorrelationSimilarity(model);
            //构建推荐器，协同过滤推荐有两种，分别是基于用户的和基于物品的，这里使用基于物品的协同过滤推荐
            GenericItemBasedRecommender recommender = new GenericItemBasedRecommender(model, itemSimilarity);

            return recommender.mostSimilarItems(id, haoMany);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return Collections.emptyList();
    }

    @Override
    public List<RecommendedItem> guaranteedRecommendation(Long id, int haoMany, FastByIDMap<PreferenceArray> data) {
        try {
            DataModel model = new GenericDataModel(data);
            Recommender recommender = new RandomRecommender(model);

            return recommender.recommend(id, haoMany);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return Collections.emptyList();
    }


}

