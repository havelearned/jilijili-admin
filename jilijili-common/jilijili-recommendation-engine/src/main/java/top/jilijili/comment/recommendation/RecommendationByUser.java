package top.jilijili.comment.recommendation;


import lombok.extern.slf4j.Slf4j;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.model.GenericDataModel;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.RandomRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

import static top.jilijili.comment.recommendation.DataModelHelper.getDataMap;

/**
 * 基于用户
 */
@Component
@Slf4j
public class RecommendationByUser implements RecommendationStrategy {

    private FileDataModel fileDataModel;

    @Autowired
    public void setFileDataModel(FileDataModel fileDataModel) {
        this.fileDataModel = fileDataModel;
    }

    /**
     * 通过文件模型
     *
     * @param id
     * @param haoMany
     * @return
     */
    @Override
    public List<RecommendedItem> recommend(Long id, int haoMany) {

        try {
            UserSimilarity similarity = new PearsonCorrelationSimilarity(fileDataModel);
            UserNeighborhood userNeighborhood = new NearestNUserNeighborhood(100, similarity, fileDataModel);
            Recommender recommender = new GenericUserBasedRecommender(fileDataModel, userNeighborhood, similarity);
            List<RecommendedItem> recommend = recommender.recommend(id, haoMany);
            if (recommend.isEmpty()) {
                FastByIDMap<PreferenceArray> dataMap = getDataMap(fileDataModel);
                recommend = guaranteedRecommendation(id, haoMany, dataMap);
                log.info("基于用户推荐结果为空，随机推荐");
            }
            return recommend;
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

            UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
            UserNeighborhood userNeighborhood = new NearestNUserNeighborhood(100, similarity, model);
            Recommender recommender = new GenericUserBasedRecommender(model, userNeighborhood, similarity);
            List<RecommendedItem> recommend = recommender.recommend(id, haoMany);
            if (recommend.isEmpty()) {
                recommend = guaranteedRecommendation(id, haoMany, data);
                log.info("基于用户推荐结果为空，随机推荐");
            }
            return recommend;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return Collections.emptyList();
    }

    /**
     * 随机推荐
     *
     * @param id
     * @param haoMany
     * @param data
     * @return
     */
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
