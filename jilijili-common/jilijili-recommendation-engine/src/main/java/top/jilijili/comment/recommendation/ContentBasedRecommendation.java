package top.jilijili.comment.recommendation;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;

import java.io.File;
import java.util.Collections;
import java.util.List;

/**
 * 基于物品
 */
public class ContentBasedRecommendation implements RecommendationStrategy {
    @Override
    public List<RecommendedItem> recommend(Long id, int haoMany) {
        try {
            String filePath = "E:\\idea_project\\jilijili-admin\\jilijili-common\\jilijili-recommendation-engine\\src\\main\\resources\\shop_user_ratings.csv";
            DataModel model = new FileDataModel(new File(filePath));
            //计算相似度，相似度算法有很多种，欧几里得、皮尔逊等等。
            ItemSimilarity itemSimilarity = new PearsonCorrelationSimilarity(model);
            //构建推荐器，协同过滤推荐有两种，分别是基于用户的和基于物品的，这里使用基于物品的协同过滤推荐
            GenericItemBasedRecommender recommender = new GenericItemBasedRecommender(model, itemSimilarity);

            return recommender.mostSimilarItems(id, haoMany);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}

