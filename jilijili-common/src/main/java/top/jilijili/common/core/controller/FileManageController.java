package top.jilijili.common.core.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.jilijili.common.annotation.JilJilOperationLog;
import top.jilijili.common.constant.ModuleNameConstant;
import top.jilijili.common.enums.OperationType;
import top.jilijili.common.core.pojo.bo.FileManageConvertBo;
import top.jilijili.common.core.pojo.dto.FileManageDto;
import top.jilijili.common.core.pojo.entity.FileManage;
import top.jilijili.common.core.pojo.vo.FileTypeTreeVO;
import top.jilijili.common.core.pojo.vo.Result;
import top.jilijili.common.core.service.FileManageService;

import java.util.List;

/**
 * 文件管理
 *
 * @author amani
 * @since 2023-04-04 10:34:09
 */
@RestController
@RequestMapping("/fileManage")
@Tag(name = "文件管理", description = "")
public class FileManageController {
    /**
     * 服务对象
     */

    FileManageService fileManageService;

    FileManageConvertBo fileManageConvertBo;

    @Autowired
    public FileManageController(FileManageService fileManageService, FileManageConvertBo fileManageConvertBo) {
        this.fileManageService = fileManageService;
        this.fileManageConvertBo = fileManageConvertBo;
    }


    /**
     * 通过字典类型查询
     *
     * @param dictType
     * @return 树形结构
     */
    @GetMapping("/queryByDict")
    public Result<FileTypeTreeVO> queryByDict(@RequestParam(value = "dictType",defaultValue = "filetype")
                                                  String dictType) {
        FileTypeTreeVO fileTypeTreeVO = this.fileManageService.queryByDict(dictType);
        return Result.ok(fileTypeTreeVO);
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
    @JilJilOperationLog(moduleName = ModuleNameConstant.FILE_MANAGE, type = OperationType.ADD)
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
    @JilJilOperationLog(moduleName = ModuleNameConstant.FILE_MANAGE, type = OperationType.UPDATE)
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
    @JilJilOperationLog(moduleName = ModuleNameConstant.FILE_MANAGE, type = OperationType.DELETED)
    @DeleteMapping("/")
    public Result<Boolean> delete(@RequestParam("idList") List<String> idList) {
        return Result.ok(this.fileManageService.removeByIds(idList));
    }
}

