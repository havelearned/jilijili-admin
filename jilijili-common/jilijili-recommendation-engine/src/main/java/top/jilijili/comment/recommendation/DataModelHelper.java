package top.jilijili.comment.recommendation;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.GenericItemPreferenceArray;
import org.apache.mahout.cf.taste.impl.model.GenericPreference;
import org.apache.mahout.cf.taste.impl.model.GenericUserPreferenceArray;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.Preference;
import org.apache.mahout.cf.taste.model.PreferenceArray;

import java.util.ArrayList;
import java.util.List;

public class DataModelHelper {

    private DataModelHelper() {
    }


    public static FastByIDMap<PreferenceArray> getDataMap(DataModel model) throws TasteException {
        LongPrimitiveIterator userIDs = model.getUserIDs();
        LongPrimitiveIterator itemIDs = model.getItemIDs();

        List<Preference> preferences = new ArrayList<>();

        while (userIDs.hasNext()) {
            Long next = userIDs.next();
            while (itemIDs.hasNext()) {
                Long itemId = itemIDs.next();
                Preference preference = new GenericPreference(next, itemId, model.getPreferenceValue(next, itemId));
                preferences.add(preference);
            }
        }

        return DataModelHelper.createUserDataMap(preferences);
    }

    /**
     * 创建用户数据模型
     *
     * @param preferences
     * @return
     */
    public static FastByIDMap<PreferenceArray> createUserDataMap(List<Preference> preferences) {
        FastByIDMap<PreferenceArray> userData = new FastByIDMap<>();

        for (Preference preference : preferences) {
            long userID = preference.getUserID();

            List<Preference> userPreferences = new ArrayList<>();
            userPreferences.add(preference);

            PreferenceArray userPreferenceArray = new GenericUserPreferenceArray(userPreferences);
            userData.put(userID, userPreferenceArray);
        }
        return userData;
    }

    /**
     *
     * 创建物品数据模型
     * @param preferences
     * @return
     */
    public static FastByIDMap<PreferenceArray> createItemDataMap(List<Preference> preferences) {
        FastByIDMap<PreferenceArray> itemData = new FastByIDMap<>();

        for (Preference preference : preferences) {
            long userID = preference.getUserID();

            List<Preference> itemPreferences = new ArrayList<>();
            itemPreferences.add(preference);

            PreferenceArray itemPreferenceArray = new GenericItemPreferenceArray(itemPreferences);
            itemData.put(userID, itemPreferenceArray);
        }
        return itemData;
    }
}
