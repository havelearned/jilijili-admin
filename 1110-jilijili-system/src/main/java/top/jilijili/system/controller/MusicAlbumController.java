package top.jilijili.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.jilijili.system.common.utils.KeyConstants;
import top.jilijili.system.common.utils.MinioUtil;
import top.jilijili.system.common.utils.WebUtil;
import top.jilijili.system.entity.MusicAlbum;
import top.jilijili.system.entity.dto.MusicAlbumDto;
import top.jilijili.system.entity.vo.MusicAlbumVo;
import top.jilijili.system.entity.vo.Result;
import top.jilijili.system.mapper.ConvertMapper;
import top.jilijili.system.service.MusicAlbumService;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * 专辑管理
 *
 * @author makejava
 * @since 2023-07-15 07:05:37
 */
@RestController
@RequestMapping("/album")
@AllArgsConstructor
@Slf4j
public class MusicAlbumController extends SuperController {
    /**
     * 服务对象
     */
    private MusicAlbumService musicAlbumService;
    private ConvertMapper convertMapper;


    /**
     * 分页查询所有数据
     *
     * @param musicAlbumDto 查询实体
     * @return 所有数据
     */
    @GetMapping("/list")
    public Result<IPage<MusicAlbumVo>> selectAll(MusicAlbumDto musicAlbumDto) {
        IPage<MusicAlbumVo> musicAlbumVoIPage = this.musicAlbumService.pageSelectAlbum(musicAlbumDto);
        return Result.ok(musicAlbumVoIPage);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param albumId 主键
     * @return 单条数据
     */
    @GetMapping("/get")
    public Result<MusicAlbum> selectOne(@RequestParam("albumId") Serializable albumId) {
        return Result.ok(this.musicAlbumService.getById(albumId));
    }

    /**
     * 新增数据
     *
     * @param musicAlbumDto 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result<MusicAlbumDto> insert(@RequestBody @Validated MusicAlbumDto musicAlbumDto) {
        Boolean saved = this.musicAlbumService.saveAlbum(musicAlbumDto);
        if (saved) {
            return Result.ok(musicAlbumDto, "操作失败");
        }
        return Result.fail("操作失败");
    }

    /**
     * 修改数据
     *
     * @param musicAlbumDto 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result<Boolean> update(@RequestBody MusicAlbumDto musicAlbumDto) {
        return Result.ok(null, this.musicAlbumService.updateAlbum(musicAlbumDto));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public Result<Boolean> delete(@RequestParam("idList") List<Long> idList) {
        return Result.ok(null, this.musicAlbumService.removeAlbum(idList));
    }

    /**
     * Excel导入
     *
     * @param file Excel文件
     * @return
     */
    @PostMapping("/import")
    public Object importExcel(@RequestPart MultipartFile file) throws IOException {
        return super.importData(file.getInputStream(), this.musicAlbumService, MusicAlbum.class);
    }

    /**
     * Excel导出
     *
     * @return
     */
    @GetMapping("/export/{idList}")
    public void exportExcel(@PathVariable(value = "idList", required = false) List<Long> idList, HttpServletResponse response) {
        List<MusicAlbum> list;
        if (!CollectionUtils.isEmpty(idList)) {
            list = this.musicAlbumService.listByIds(idList);
        } else {
            list = this.musicAlbumService.list();
        }
        WebUtil.setDownloadRequestHeader(response, "专辑数据.xlsx");
        super.exportData(response, "专辑数据", "专辑", MusicAlbum.class, list);
    }

    /**
     * 获取导入模板
     *
     * @return
     */
    @GetMapping("/get/exportTemplate")
    public void getExportTemplate(HttpServletResponse response) {
        String url = MinioUtil.getFileUrl(KeyConstants.Album_DATA_IMPORT_TEMPLATE);
        byte[] bytes = new byte[0];
        WebUtil.setDownloadRequestHeader(response, "专辑数据导入模板.xlsx");
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

