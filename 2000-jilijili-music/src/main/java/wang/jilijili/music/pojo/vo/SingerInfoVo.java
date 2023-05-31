package wang.jilijili.music.pojo.vo;

import lombok.Data;

import java.util.List;

/**
 * 歌手信息,专辑信息,歌曲信息
 *
 * @author Amani
 * @date 2023年03月26日 9:28
 */
@Data
public class SingerInfoVo extends SingerVo {
    private String singerId;
    private List<AlibumVo> alibumVos;
}

