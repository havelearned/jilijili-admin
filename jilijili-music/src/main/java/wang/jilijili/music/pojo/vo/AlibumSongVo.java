package wang.jilijili.music.pojo.vo;

import lombok.Data;

import java.util.List;

/**
 * 专辑下面的所有歌曲vo
 *
 * @author Amani
 * @date 2023年04月02日 14:46
 */
@Data
public class AlibumSongVo extends AlibumVo {
    List<MusicVo> musicVoList;





}
