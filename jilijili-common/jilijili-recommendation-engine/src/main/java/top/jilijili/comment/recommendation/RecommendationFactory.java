package top.jilijili.comment.recommendation;

public class RecommendationFactory {
    public static RecommendationStrategy getRecommendationStrategy(String type) {
        switch (type) {
            case "content":
                return new ContentBasedRecommendation();
            case "collaborative":
                return new CollaborativeFilteringRecommendation();
            default:
                throw new IllegalArgumentException("Unknown recommendation type: " + type);
        }
    }
}