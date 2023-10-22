package top.jilijili.comment.recommendation;

import com.alibaba.fastjson2.JSON;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.model.GenericPreference;
import org.apache.mahout.cf.taste.impl.model.GenericUserPreferenceArray;
import org.apache.mahout.cf.taste.model.Preference;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Amani
 * @date 2023年10月10日 22:37
 */
public class Main {

    public static void main(String[] args) {

        long l = System.currentTimeMillis();
        // 通过物品推荐
        RecommendationStrategy content = RecommendationFactory.getRecommendationStrategy("item");
        FastByIDMap<PreferenceArray> userData = new FastByIDMap<>();

        for (long i = 0; i < 1000; i++) { // 用户
            List<Preference> user3Preferences = new ArrayList<>();

            for (int j = 0; j < 1000; j++) { // 物品
                user3Preferences.add(new GenericPreference(i, (j + 100L), (float) (Math.random() * 10f)));

            }
            PreferenceArray user3PreferenceArray = new GenericUserPreferenceArray(user3Preferences);
            userData.put(i, user3PreferenceArray);

        }
        // 通过物品id 300,推荐10个数据
        List<RecommendedItem> recommend = content.recommend(300L, 10, userData);

        System.out.println(JSON.toJSON(recommend));
        long l1 = System.currentTimeMillis();
        System.out.println(l1 + " - " + l + "=" + ((l1 - l) / 1000) + "秒");
    }
}
