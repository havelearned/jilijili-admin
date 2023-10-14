package top.jilijili.comment.recommendation;


import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.io.File;
import java.util.Collections;
import java.util.List;

/**
 * 基于用户
 */
public class CollaborativeFilteringRecommendation implements RecommendationStrategy {

    @Override
    public List<RecommendedItem> recommend(Long id, int haoMany) {

        try {
            String filePath = "E:\\idea_project\\jilijili-admin\\jilijili-common\\jilijili-recommendation-engine\\src\\main\\resources\\shop_user_ratings.csv";
            DataModel model = new FileDataModel(new File(filePath));

            UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
            UserNeighborhood userNeighborhood = new NearestNUserNeighborhood(100, similarity, model);
            Recommender recommender = new GenericUserBasedRecommender(model, userNeighborhood, similarity);
            return recommender.recommend(id, haoMany);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
