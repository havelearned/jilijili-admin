package wang.jilijili.music.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import wang.jilijili.common.core.pojo.vo.Result;
import wang.jilijili.common.group.Insert;
import wang.jilijili.common.group.Updata;
import wang.jilijili.music.pojo.bo.MusicConvertBo;
import wang.jilijili.music.pojo.dto.MusicDto;
import wang.jilijili.music.pojo.entity.Music;
import wang.jilijili.music.pojo.vo.MusicDetailVo;
import wang.jilijili.music.pojo.vo.MusicVo;
import wang.jilijili.music.service.MusicService;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * 歌曲管理
 *
 * @author amani
 * @since 2023-03-27 11:04:54
 */
@RestController
@RequestMapping("/music")
@Tag(name = "歌曲管理")
public class MusicController {
    /**
     * 服务对象
     */
    MusicService musicService;
    MusicConvertBo musicConvertBo;

    public MusicController(MusicService musicService, MusicConvertBo musicConvertBo) {
        this.musicService = musicService;
        this.musicConvertBo = musicConvertBo;
    }

    /**
     * 分页查询所有数据
     *
     * @param musicDto 查询实体
     * @return wang.jilijili.common.core.pojo.vo.Result<com.baomidou.mybatisplus.core.metadata.IPage < wang.jilijili.music.pojo.vo.MusicVo>>
     * @author Amani
     * @date 27/3/2023 上午11:12
     */
    @GetMapping("/list")
    public Result<IPage<MusicVo>> selectAll(MusicDto musicDto) {
        IPage<Music> iPage = new Page<>(musicDto.getPage(), musicDto.getSize());
        IPage<MusicDto> dtoPage = this.musicService.listPage(musicDto, iPage);
        return Result.ok(dtoPage.convert(this.musicConvertBo::toMusicVo));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public Result<MusicVo> selectOne(@PathVariable Serializable id) {
        Music music = this.musicService.getById(id);
        MusicDto musicDto = this.musicConvertBo.toMusicDto(music);
        return Result.ok(this.musicConvertBo.toMusicVo(musicDto));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/queryMusicInfoById/{id}")
    public Result<MusicDetailVo> queryMusicInfoById(@PathVariable String id) {
        MusicDetailVo musicDetailVo = this.musicService.queryMusicInfoById(id);
        return Result.ok(musicDetailVo);

    }


    /**
     * 新增数据
     *
     * @param musicDto 实体对象
     * @return 新增结果
     */
    @PostMapping("/")
    public Result<MusicVo> insert(@RequestBody @Validated(value = Insert.class)
                                  MusicDto musicDto) {

        musicDto = this.musicService.create(musicDto);

        return Result.ok(this.musicConvertBo.toMusicVo(musicDto));
    }

    /**
     * 修改数据
     *
     * @param musicDto 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result<MusicVo> update(@RequestBody @Validated(value = Updata.class)
                                  MusicDto musicDto) {
        musicDto = this.musicService.update(musicDto);
        return Result.ok(this.musicConvertBo.toMusicVo(musicDto));
    }

    /**
     * 删除数据
     *
     * @param idList 主键集合
     * @return 删除结果
     */
    @DeleteMapping
    public Result<Boolean> delete(@RequestParam("idList") List<String> idList) {
        return Result.ok(this.musicService.deletedByIds(idList));
    }
}

