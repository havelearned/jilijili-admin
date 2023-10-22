package top.jilijili.comment.recommendation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static top.jilijili.comment.recommendation.conf.RecommenderType.ITEM;

@Component
public class RecommendationFactory {


    public static RecommendationStrategy getRecommendationStrategy(String type) {
        if (type.equals(ITEM.getType())) {
            return recommendationByItem;
        } else {
            return recommendationByUser;
        }
    }


    private static RecommendationByItem recommendationByItem;

    private static RecommendationByUser recommendationByUser;

    @Autowired
    public void setRecommendationByItem(RecommendationByItem recommendationByItem) {
        RecommendationFactory.recommendationByItem = recommendationByItem;
    }

    @Autowired
    public void setRecommendationByUser(RecommendationByUser recommendationByUser) {
        RecommendationFactory.recommendationByUser = recommendationByUser;
    }


}