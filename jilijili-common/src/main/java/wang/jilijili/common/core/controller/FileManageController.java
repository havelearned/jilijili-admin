package wang.jilijili.common.core.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import wang.jilijili.common.annotation.JilJilOperationLog;
import wang.jilijili.common.core.pojo.bo.FileManageConvertBo;
import wang.jilijili.common.core.pojo.dto.FileManageDto;
import wang.jilijili.common.core.pojo.entity.FileManage;
import wang.jilijili.common.core.pojo.vo.Result;
import wang.jilijili.common.core.service.FileManageService;
import wang.jilijili.common.enums.OperationType;

import java.util.List;

import static wang.jilijili.common.constant.ModuleNameConstant.FILE_MANAGE;

/**
 * 文件管理
 *
 * @author amani
 * @since 2023-04-04 10:34:09
 */
@RestController
@RequestMapping("/fileManage")
@Tag(name = "文件管理",description = "本地和oss存储")
public class FileManageController {
    /**
     * 服务对象
     */

    FileManageService fileManageService;

    FileManageConvertBo fileManageConvertBo;

    public FileManageController(FileManageService fileManageService, FileManageConvertBo fileManageConvertBo) {
        this.fileManageService = fileManageService;
        this.fileManageConvertBo = fileManageConvertBo;
    }

    /**
     * 分页查询所有数据
     *
     * @param fileManageDto 分页对象
     * @return 所有数据
     */
    @GetMapping("/list")
    public Result<IPage<FileManage>> selectAll(FileManageDto fileManageDto) {
        IPage<FileManage> iPage = new Page<>(fileManageDto.getPage(), fileManageDto.getSize());
        return Result.ok(this.fileManageService.list(iPage, fileManageDto));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public Result<FileManageDto> selectOne(@PathVariable String id) {
        return Result.ok(this.fileManageConvertBo.toFileManageDto(
                this.fileManageService.getById(id)));
    }

    /**
     * 新增数据
     *
     * @param fileManage 实体对象
     * @return 新增结果
     */
    @JilJilOperationLog(moduleName = FILE_MANAGE, type = OperationType.ADD)
    @PostMapping("/")
    public Result<Boolean> insert(@RequestBody FileManage fileManage) {
        return Result.ok(this.fileManageService.save(fileManage));
    }

    /**
     * 修改数据
     *
     * @param fileManage 实体对象
     * @return 修改结果
     */
    @JilJilOperationLog(moduleName = FILE_MANAGE, type = OperationType.UPDATE)
    @PutMapping("/")
    public Result<Boolean> update(@RequestBody FileManage fileManage) {
        return Result.ok(this.fileManageService.updateById(fileManage));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @JilJilOperationLog(moduleName = FILE_MANAGE, type = OperationType.DELETED)
    @DeleteMapping("/")
    public Result<Boolean> delete(@RequestParam("idList") List<String> idList) {
        return Result.ok(this.fileManageService.removeByIds(idList));
    }
}

