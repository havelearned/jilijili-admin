package top.jilijili.comment.recommendation;

import org.apache.mahout.cf.taste.recommender.RecommendedItem;

import java.util.List;

public interface RecommendationStrategy {
    List<RecommendedItem> recommend(Long id, int haoMany);
}
