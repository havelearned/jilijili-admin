package top.jilijili.comment.recommendation.conf;

import lombok.Getter;

@Getter
public enum RecommenderType {
    USER("user"),
    ITEM("item");

    private final String type;


    RecommenderType(String type) {
        this.type = type;
    }
}
