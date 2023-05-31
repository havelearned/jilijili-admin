package wang.jilijili.music.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import wang.jilijili.common.annotation.JilJilOperationLog;
import wang.jilijili.common.core.pojo.vo.Result;
import wang.jilijili.common.enums.OperationType;
import wang.jilijili.music.pojo.bo.SingerConvertBo;
import wang.jilijili.music.pojo.dto.SingerDto;
import wang.jilijili.music.pojo.entity.Singer;
import wang.jilijili.music.pojo.vo.AlibumSongVo;
import wang.jilijili.music.pojo.vo.SingerInfoVo;
import wang.jilijili.music.pojo.vo.SingerVo;
import wang.jilijili.music.service.SingerService;

import java.io.Serializable;
import java.util.List;

import static wang.jilijili.common.constant.DatabaseConstant.*;
import static wang.jilijili.common.constant.ModuleNameConstant.MUSIC_MANAGE;
import static wang.jilijili.common.constant.RoleConstant.ROLE_SUPER_ADMIN;

/**
 * 歌手管理
 *
 * @author amani
 * @since 2023-03-20 23:33:45
 */
@RestController
@RequestMapping("/singer")
@Tag(name = "歌手管理")
public class SingerController {
    /**
     * 服务对象
     */
    private final SingerService singerService;

    private final SingerConvertBo singerConvertBo;

    public SingerController(SingerService singerService, SingerConvertBo singerConvertBo) {
        this.singerService = singerService;
        this.singerConvertBo = singerConvertBo;
    }

    /**
     * 通过专辑id查询歌曲
     *
     * @param alibumId 专辑id
     * @return wang.jilijili.common.core.pojo.vo.Result<wang.jilijili.music.pojo.vo.AlibumSongVo>
     * @author Amani
     * @date 2023/4/2 14:55
     */
    @GetMapping("/getSingerByAlibumBySongAll/{alibumId}")
    public Result<AlibumSongVo> getSingerByAlibumBySongAll(@PathVariable String alibumId) {
        AlibumSongVo alibumSongVo = this.singerService.getSingerByAlibumBySongAll(alibumId);
        return Result.ok(alibumSongVo);
    }


    /**
     * 通过歌手id查询专辑
     *
     * @param singerId 歌手id
     * @return wang.jilijili.common.core.pojo.vo.Result<wang.jilijili.music.pojo.vo.SingerInfoVo>
     * @author Amani
     * @date 2023/4/2 14:02
     */
    @GetMapping("/singerInfo/{singerId}")
    public Result<SingerInfoVo> getSingerInfo(@PathVariable String singerId) {
        SingerInfoVo singerInfo = this.singerService.getSingerInfo(singerId);
        return Result.ok(singerInfo);

    }

    /**
     * 分页查询所有数据
     *
     * @param singerDto 查询条件
     * @return wang.jilijili.common.core.pojo.vo.Result<com.baomidou.mybatisplus.core.metadata.IPage < wang.jilijili.music.pojo.entity.Singer>>
     * @author Amani
     * @date 2023/3/21 14:26
     */
    @GetMapping("/list")
    public Result<IPage<SingerVo>> selectAll(SingerDto singerDto) {
        IPage<Singer> page = new Page<>(singerDto.getPage(), singerDto.getSize());
        Singer singer = this.singerConvertBo.toSinger(singerDto);
        IPage<Singer> iPage = this.singerService.page(page, new QueryWrapper<Singer>()
                .eq(StringUtils.hasText(singerDto.getId()), ID, singerDto.getId())
                .like(StringUtils.hasText(singerDto.getSingerName()), SINGERNAME, singerDto.getSingerName())
                .between((singerDto.getCreatedTime() != null && singerDto.getSpecifyTime() != null), CREATED_TIME, singerDto.getCreatedTime(), singerDto.getSpecifyTime())
        );
        return Result.ok(iPage.convert(item -> this.singerConvertBo.toSingerVo(this.singerConvertBo.toSingerDto(item))));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public Result<SingerVo> selectOne(@PathVariable Serializable id) {
        Singer singer = this.singerService.getById(id);
        SingerDto singerDto = this.singerConvertBo.toSingerDto(singer);
        return Result.ok(this.singerConvertBo.toSingerVo(singerDto));
    }

    /**
     * 新增数据
     *
     * @param singerDto 实体对象
     * @return 新增结果
     */
    @PostMapping("/")
    @RolesAllowed(value = {ROLE_SUPER_ADMIN})
    @JilJilOperationLog(moduleName = MUSIC_MANAGE, type = OperationType.ADD)
    public Result<SingerVo> insert(@RequestBody SingerDto singerDto) {
        singerDto = this.singerService.create(singerDto);
        return Result.ok(this.singerConvertBo.toSingerVo(singerDto));

    }

    /**
     * 修改数据
     *
     * @param singerDto 实体对象
     * @return 修改结果
     */
    @PutMapping("/")
    @RolesAllowed(value = {ROLE_SUPER_ADMIN})
    @JilJilOperationLog(moduleName = MUSIC_MANAGE, type = OperationType.UPDATE)
    public Result<SingerVo> update(@RequestBody SingerDto singerDto) {
        singerDto = this.singerService.update(singerDto);
        return Result.ok(this.singerConvertBo.toSingerVo(singerDto));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/idList/{idList}")
    @RolesAllowed(value = {ROLE_SUPER_ADMIN})
    @JilJilOperationLog(moduleName = MUSIC_MANAGE, type = OperationType.DELETED)
    public Result<Boolean> delete(@PathVariable List<String> idList) {
        return Result.ok(this.singerService.deleteBatch(idList));
    }

}
