package wang.jilijili.web.common;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import wang.jilijili.common.core.pojo.entity.FileManage;
import wang.jilijili.common.core.pojo.vo.Result;
import wang.jilijili.common.core.service.FileManageService;
import wang.jilijili.common.enums.UploadModule;
import wang.jilijili.framework.strategy.UploadStrategyContext;

import java.io.Serializable;
import java.util.List;

/**
 * 文件上传控制器
 *
 * @author Amani
 * @date 2023年03月16日 下午12:47
 */

@RestController
@RequestMapping("/upload")
public class UploadController {

    UploadStrategyContext uploadStrategyContext;

    /**
     * 服务对象
     */
    FileManageService fileManageService;


    public UploadController(UploadStrategyContext uploadStrategyContext, FileManageService fileManageService) {
        this.uploadStrategyContext = uploadStrategyContext;
        this.fileManageService = fileManageService;
    }

    @PostMapping("/local/image")
    public String localUpload(MultipartFile file) {
        return this.uploadStrategyContext
                .executeUploadStrategy(
                        file,
                        UploadModule.MUSIC_MPEG_LOCAL.getPath() + UploadModule.MUSIC_IMAGE_LOCAL.getType(),
                        UploadModule.MUSIC_MPEG_LOCAL.getExecutedBeanName());
    }

    @PostMapping("/oss/image")
    public String ossUpload(MultipartFile file) {
        return this.uploadStrategyContext.executeUploadStrategy(
                file,
                UploadModule.OSS_IMAGE_MUSIC.getType(),
                UploadModule.OSS_IMAGE_MUSIC.getExecutedBeanName());
    }


    /**
     * 分页查询所有数据
     *
     * @param page       分页对象
     * @param fileManage 查询实体
     * @return 所有数据
     */
    @GetMapping("/list")
    public Result selectAll(FileManage fileManage) {
        // TODO 创建FileManageDto Vo bo
        IPage<FileManage> page = new Page<>(1,10);
        return Result.ok(this.fileManageService.page(page, new QueryWrapper<>(fileManage)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public Result selectOne(@PathVariable Serializable id) {
        return Result.ok(this.fileManageService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param fileManage 实体对象
     * @return 新增结果
     */
    @PostMapping("/")
    public Result insert(@RequestBody FileManage fileManage) {
        return Result.ok(this.fileManageService.save(fileManage));
    }

    /**
     * 修改数据
     *
     * @param fileManage 实体对象
     * @return 修改结果
     */
    @PutMapping("/")
    public Result update(@RequestBody FileManage fileManage) {
        return Result.ok(this.fileManageService.updateById(fileManage));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/")
    public Result delete(@RequestParam("idList") List<Long> idList) {
        return Result.ok(this.fileManageService.removeByIds(idList));
    }
}
