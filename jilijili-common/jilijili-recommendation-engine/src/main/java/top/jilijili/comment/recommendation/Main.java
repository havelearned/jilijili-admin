package top.jilijili.comment.recommendation;

import org.apache.mahout.cf.taste.recommender.RecommendedItem;

import java.util.List;

/**
 * @author Amani
 * @date 2023年10月10日 22:37
 */
public class Main {

    public static void main(String[] args) {
        RecommendationStrategy content = RecommendationFactory.getRecommendationStrategy("collaborative");
        List<RecommendedItem> recommend = content.recommend(16704L, 10);
        System.out.println(recommend.toString());
    }
}
