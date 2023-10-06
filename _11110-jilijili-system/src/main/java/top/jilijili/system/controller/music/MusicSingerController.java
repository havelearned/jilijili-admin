package top.jilijili.system.controller.music;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.jilijili.system.common.utils.KeyConstants;
import top.jilijili.system.common.utils.MinioUtil;
import top.jilijili.system.common.utils.WebUtil;
import top.jilijili.module.entity.MusicSinger;
import top.jilijili.module.entity.MusicSong;
import top.jilijili.module.entity.dto.MusicSingerDto;
import top.jilijili.module.entity.vo.MusicSingerVo;
import top.jilijili.module.entity.vo.Result;
import top.jilijili.system.controller.SuperController;
import top.jilijili.system.mapper.ConvertMapper;
import top.jilijili.system.service.MusicSingerService;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * 歌手管理
 *
 * @author makejava
 * @since 2023-07-06 22:14:02
 */
@Slf4j
@RestController
@RequestMapping("/singer")
@AllArgsConstructor
public class MusicSingerController extends SuperController {
    /**
     * 服务对象
     */
    private MusicSingerService musicSingerService;
    private ConvertMapper convertMapper;

    /**
     * TODO 查询歌手所有歌曲
     * TODO 查询歌手所有专辑
     */
    @GetMapping()
    public Result<MusicSong> selectSingerByAllSong(@RequestParam("singerId") Serializable singerId) {
        return this.musicSingerService.selectSingerByAllSong(singerId);
    }


    /**
     * 分页查询所有数据
     *
     * @param musicSingerDto
     * @return
     */
    @GetMapping("/list")
    public Result<IPage<MusicSingerVo>> selectAll(MusicSingerDto musicSingerDto) {
        Page<MusicSinger> page = new Page<MusicSinger>(musicSingerDto.getPage(), musicSingerDto.getSize());
        MusicSinger musicSinger = this.convertMapper.toSingerEntity(musicSingerDto);
        IPage<MusicSingerVo> voIPage = this.musicSingerService.lambdaQuery()
                .like(StringUtils.hasText(musicSinger.getName()), MusicSinger::getName, musicSinger.getName())
                .eq(musicSinger.getStatus() != null, MusicSinger::getStatus, musicSinger.getStatus())
                .like(StringUtils.hasText(musicSinger.getDesc()), MusicSinger::getDesc, musicSinger.getDesc())
                .orderByDesc(MusicSinger::getCreatedTime)
                .page(page).convert(this.convertMapper::toSingerVo);
        return Result.ok(voIPage);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param singerId 主键
     * @return 单条数据
     */
    @GetMapping("/get")
    public Result<MusicSinger> getSinger(@RequestParam(value = "singerId") Serializable singerId) {
        return Result.ok(this.musicSingerService.getById(singerId));
    }

    /**
     * 添加歌手
     *
     * @param musicSinger 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result<MusicSinger> insert(@RequestBody MusicSinger musicSinger) {
        if (!StringUtils.hasText(musicSinger.getDesc())) {
            musicSinger.setDesc("暂无介绍");
        }
        boolean saved = this.musicSingerService.save(musicSinger);

        log.info("添加歌手信息:{},是否成功:{}", musicSinger, saved);
        if (saved) {
            return Result.ok(musicSinger, "操作成功", saved);
        }
        return Result.fail("操作失败");
    }

    /**
     * 修改歌手
     *
     * @param musicSinger 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result<MusicSinger> update(@RequestBody MusicSinger musicSinger) {
        boolean updated = this.musicSingerService.updateById(musicSinger);
        log.info("修改歌手信息:{},是否成功:{}", musicSinger, updated);
        if (updated) {
            return Result.ok(musicSinger, "操作成功", updated);
        }
        return Result.fail("操作失败");

    }

    /**
     * 删除歌手
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public Result<Boolean> delete(@RequestParam("idList") List<Long> idList) {
        log.info("删除歌手:{}", idList.toString());
        return Result.ok(this.musicSingerService.removeByIds(idList));
    }


    /**
     * Excel导入
     *
     * @param file
     * @return
     */
    @PostMapping("/import")
    public Object importExcel(@RequestPart MultipartFile file) throws IOException {
        return super.importData(file.getInputStream(), this.musicSingerService, MusicSinger.class);
    }

    /**
     * Excel导出
     *
     * @return
     */
    @GetMapping("/export/{idList}")
    public void exportExcel(@PathVariable(value = "idList", required = false) List<Long> idList, HttpServletResponse response) {
        List<MusicSinger> musicSingers;
        if (!CollectionUtils.isEmpty(idList)) {
            musicSingers = this.musicSingerService.listByIds(idList);
        } else {
            musicSingers = this.musicSingerService.list();
        }
        WebUtil.setDownloadRequestHeader(response, "歌手数据.xlsx");
        super.exportData(response, "歌手数据", "歌手", MusicSinger.class, musicSingers);
    }

    /**
     * 获取导入模板
     *
     * @return
     */
    @GetMapping("/get/exportTemplate")
    public void getExportTemplate(HttpServletResponse response) {
        String url = MinioUtil.getFileUrl(KeyConstants.SINGER_DATA_IMPORT_TEMPLATE);
        byte[] bytes = new byte[0];
        WebUtil.setDownloadRequestHeader(response, "歌手数据导入模板.xlsx");
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

