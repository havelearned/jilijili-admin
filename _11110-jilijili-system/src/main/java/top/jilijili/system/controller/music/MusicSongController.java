package top.jilijili.system.controller.music;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.jilijili.system.common.utils.KeyConstants;
import top.jilijili.system.common.utils.MinioUtil;
import top.jilijili.system.common.utils.WebUtil;
import top.jilijili.module.entity.MusicSong;
import top.jilijili.module.entity.dto.MusicSongDto;
import top.jilijili.module.entity.vo.MusicSongVo;
import top.jilijili.module.entity.vo.Result;
import top.jilijili.system.controller.SuperController;
import top.jilijili.system.mapper.ConvertMapper;
import top.jilijili.system.service.MusicSongService;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * 歌曲管理
 *
 * @author amani
 * @since 2023-07-15 06:22:39
 */
@RestController
@RequestMapping("/song")
@AllArgsConstructor
@Slf4j
public class MusicSongController extends SuperController {
    /**
     * 服务对象
     */
    private MusicSongService musicSongService;
    private ConvertMapper convertMapper;


    /**
     * 分页查询所有数据
     *
     * @param musicSongDto 查询条件
     * @return 分页查询
     */
    @GetMapping("/list")
    public Result<IPage<MusicSongVo>> selectAll(MusicSongDto musicSongDto) {
        IPage<MusicSong> page = new Page<>(musicSongDto.getPage(), musicSongDto.getSize());
        IPage<MusicSongVo> voIPage = this.musicSongService.lambdaQuery()
                .orderByDesc(MusicSong::getCreatedTime)
                .page(page).convert(this.convertMapper::toSongVo);
        return Result.ok(voIPage);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param songId 主键
     * @return 单条数据
     */
    @GetMapping("/get")
    public Result<MusicSongVo> selectOne(@RequestParam(value = "songId") Serializable songId) {
        return Result.ok(this.musicSongService.selectSongInfoBySongId(songId));
    }

    /**
     * 新增数据
     *
     * @param musicSongDto 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result<MusicSongVo> insert(@RequestBody MusicSongDto musicSongDto) {
        MusicSong musicSong = this.musicSongService.addOrUpdateBefore(musicSongDto);
        boolean save = this.musicSongService.save(musicSong);
        MusicSongVo songVo = this.convertMapper.toSongVo(musicSong);
        log.info("添加歌曲信息:{},是否成功{}", musicSongDto, save);
        if (!save) {
            return Result.fail(songVo, "操作失败");
        }
        return Result.ok(songVo, "操作成功");
    }

    /**
     * 修改歌曲状态
     *
     * @param musicSong
     * @return
     */
    @PutMapping("/putStatus")
    public Result<String> changeStatus(@RequestBody MusicSong musicSong) {
        boolean update = this.musicSongService.lambdaUpdate()
                .set(musicSong.getStatus() != null, MusicSong::getStatus, musicSong.getStatus())
                .eq(MusicSong::getSongId, musicSong.getSongId())
                .update();
        log.info("修改歌曲状态:{},是否成功{}", musicSong, update);
        if (update) {
            return Result.ok("操作成功");
        }
        return Result.fail("操作失败");

    }

    /**
     * 修改数据
     *
     * @param musicSongDto 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result<MusicSongVo> update(@RequestBody MusicSongDto musicSongDto) {
        MusicSong musicSong = this.musicSongService.addOrUpdateBefore(musicSongDto);
        boolean b = this.musicSongService.updateById(musicSong);
        MusicSongVo songVo = this.convertMapper.toSongVo(musicSong);
        log.info("修改歌曲信息:{},是否成功{}", musicSongDto, b);
        if (b) {
            return Result.ok(songVo, "操作成功");
        }
        return Result.fail(songVo, "操作失败");
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public Result<Boolean> delete(@RequestParam("idList") List<Long> idList) {
        log.info("删除歌曲信息:{}", idList);
        return Result.ok(this.musicSongService.removeByIds(idList));
    }


    /**
     * Excel导入
     *
     * @param file Excel文件
     * @return
     */
    @PostMapping("/import")
    public Object importExcel(@RequestPart MultipartFile file) throws IOException {
        return super.importData(file.getInputStream(), this.musicSongService, MusicSong.class);
    }

    /**
     * Excel导出
     *
     * @return
     */
    @GetMapping("/export/{idList}")
    public void exportExcel(@PathVariable(value = "idList", required = false) List<Long> idList, HttpServletResponse response) {
        List<MusicSong> musicSongs;
        if (!CollectionUtils.isEmpty(idList)) {
            musicSongs = this.musicSongService.listByIds(idList);
        } else {
            musicSongs = this.musicSongService.list();
        }
        WebUtil.setDownloadRequestHeader(response, "歌曲数据.xlsx");
        super.exportData(response, "歌曲数据", "歌曲", MusicSong.class, musicSongs);
    }

    /**
     * 获取导入模板
     *
     * @return
     */
    @GetMapping("/get/exportTemplate")
    public void getExportTemplate(HttpServletResponse response) {
        String url = MinioUtil.getFileUrl(KeyConstants.SONG_DATA_IMPORT_TEMPLATE);
        byte[] bytes;
        WebUtil.setDownloadRequestHeader(response, "歌曲数据导入模板.xlsx");
        try {
            bytes = UrlResource.from(url).getInputStream().readAllBytes();
            response.getOutputStream().write(bytes);
            response.setStatus(HttpStatus.OK.value());
        } catch (IOException e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            log.error(e.getMessage());
        }
    }
}

