package wang.jilijili.music.pojo.bo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.util.StringUtils;
import wang.jilijili.music.pojo.entity.Music;
import wang.jilijili.music.pojo.request.MusicQueryRequest;


/**
 * 为歌曲的查询业务条件封装
 *
 * @author Amani
 * @date 2023/3/9 11:25
 */
public class MusicSearchBo {

    /**
     * @param musicQueryRequest 查询条件构造
     * @return LambdaQueryWrapper<Music>
     */
    public static LambdaQueryWrapper<Music> getMusicLambdaQueryWrapper(MusicQueryRequest musicQueryRequest) {
        LambdaQueryWrapper<Music> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .like(StringUtils.hasText(musicQueryRequest.getName()), Music::getName, musicQueryRequest.getName());
        return queryWrapper;
    }


}
