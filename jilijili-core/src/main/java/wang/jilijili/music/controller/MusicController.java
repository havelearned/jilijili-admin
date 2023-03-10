package wang.jilijili.music.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import wang.jilijili.music.common.annotation.JilJilOperationLog;
import wang.jilijili.music.common.enums.OperationType;
import wang.jilijili.music.mapper.MusicMapper;
import wang.jilijili.music.pojo.bo.MusicConvertBo;
import wang.jilijili.music.pojo.dto.MusicDto;
import wang.jilijili.music.pojo.entity.Music;
import wang.jilijili.music.pojo.request.MusicCreateRequest;
import wang.jilijili.music.pojo.request.MusicQueryRequest;
import wang.jilijili.music.pojo.request.MusicUpdateRequest;
import wang.jilijili.music.pojo.vo.MusicVo;
import wang.jilijili.music.pojo.vo.Result;
import wang.jilijili.music.service.MusicService;

import static wang.jilijili.music.common.constant.ModuleNameConstant.MUSIC_MANAGE;
import static wang.jilijili.music.common.constant.RoleConstant.ROLE_SUPER_ADMIN;

/**
 * @author Amani
 * @date 2023年03月09日 10:23
 */
@RestController
@RequestMapping("/multipleMusic")
@Tag(name = "歌曲管理")
public class MusicController extends BaseController<Music, MusicMapper, MusicService> {

    MusicService musicService;
    MusicConvertBo musicConvertBo;

    public MusicController(MusicMapper mapper, MusicService musicService, MusicConvertBo musicConvertBo) {
        super(mapper, musicService);
        this.musicService = musicService;
        this.musicConvertBo = musicConvertBo;
    }

    /**
     * 分页查询
     *
     * @param musicQueryRequest 查询条件
     * @return wang.jilijili.music.pojo.vo.Result<wnag.jilijili.music.pojo.vo.MusicVo>
     * @author Amani
     * @date 2023/3/9 11:03
     */

    @GetMapping("/list")
    @RolesAllowed(value = {ROLE_SUPER_ADMIN})
    @JilJilOperationLog(moduleName = MUSIC_MANAGE, type = OperationType.SELECT)
    public Result<IPage<MusicVo>> list(@RequestBody MusicQueryRequest musicQueryRequest) {

        IPage<Music> tPage = new Page<>(musicQueryRequest.getPage(), musicQueryRequest.getSize());

        IPage<MusicDto> dtoIpage = this.musicService.search(tPage, musicQueryRequest);

        IPage<MusicVo> page = dtoIpage.convert((item) -> this.musicConvertBo.toMusicVo(item));
        return Result.ok(page);
    }

    /**
     * 删除歌曲
     *
     * @param id 歌曲id
     * @return wang.jilijili.music.pojo.vo.Result<wnag.jilijili.music.pojo.vo.MusicVo>
     * @author Amani
     * @date 2023/3/9 11:03
     */
    @DeleteMapping("/{id}")
    @RolesAllowed(value = {ROLE_SUPER_ADMIN})
    @JilJilOperationLog(moduleName = MUSIC_MANAGE, type = OperationType.DELETED)
    public Result<MusicVo> delete(@PathVariable String id) {
        this.musicService.delete(id);

        return Result.ok();
    }

    /**
     * 修改歌曲
     *
     * @param musicUpdateRequest from表单
     * @return wang.jilijili.music.pojo.vo.Result<wnag.jilijili.music.pojo.vo.MusicVo>
     * @author Amani
     * @date 2023/3/9 11:02
     */
    @PutMapping("/")
    @RolesAllowed(value = {ROLE_SUPER_ADMIN})
    @JilJilOperationLog(moduleName = MUSIC_MANAGE, type = OperationType.UPDATE)
    public Result<MusicVo> update(@RequestBody @Validated MusicUpdateRequest musicUpdateRequest) {
        MusicDto dto = this.musicService.update(musicUpdateRequest);
        MusicVo musicVo = this.musicConvertBo.toMusicVo(dto);
        return Result.ok(musicVo);
    }

    /**
     * 创建歌曲
     *
     * @param musicCreateRequest 表单
     * @return wang.jilijili.music.pojo.vo.Result<wnag.jilijili.music.pojo.vo.MusicVo>
     * @author Amani
     * @date 2023/3/9 10:54
     */
    @PostMapping("/")
    @RolesAllowed(value = {ROLE_SUPER_ADMIN})
    @JilJilOperationLog(moduleName = MUSIC_MANAGE, type = OperationType.ADD)
    public Result<MusicVo> create(@RequestBody @Validated MusicCreateRequest musicCreateRequest) {
        MusicDto dto = this.musicService.create(musicCreateRequest);
        return Result.ok(this.musicConvertBo.toMusicVo(dto));
    }


}
