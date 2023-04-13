package wang.jilijili.music.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import wang.jilijili.music.pojo.dto.MusicDto;
import wang.jilijili.music.pojo.entity.Music;

/**
 * @author admin
 * @description 针对表【music(歌词表)】的数据库操作Service
 * @createDate 2023-03-27 11:04:06
 */

@DS("slave_1")
public interface MusicService extends IService<Music> {


    /**
     * 分页查询
     * @param musicDto
     * @param iPage
     * @return 返回vo
     */
    IPage<MusicDto> listPage(MusicDto musicDto, IPage<Music> iPage);

    /**
     * 创建歌曲
     * @param musicDto
     * @return 返回DTO
     */
    MusicDto create(MusicDto musicDto);

    /**
     * 修改歌曲
     * @param musicDto
     * @return 返回DTO
     */
    MusicDto update(MusicDto musicDto);

}
