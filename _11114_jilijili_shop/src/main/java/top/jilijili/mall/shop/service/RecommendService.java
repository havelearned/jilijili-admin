package top.jilijili.mall.shop.service;

import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.model.PreferenceArray;

import java.util.List;

/**
 * @author Amani
 * @date 2023年10月22日 13:46
 */
public interface RecommendService {

    /**
     * 默认使用文件模型
     * @param userId 用户id
     * @param haoMany 推荐大小
     * @return
     */
    List<Long> recommendByItem(Long userId,Integer haoMany);

    /**
     * 通过用户或者物品推荐
     * @param id 用户id
     * @param haoMany 推荐大小
     * @param data 源数据,建议1000-100之间
     * @return
     */
    List<Long> recommendByByItem(Long id, int haoMany, FastByIDMap<PreferenceArray> data);


}
