package wang.jilijili.common.core.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.jilijili.common.core.pojo.bo.OperationLogConvertBo;
import wang.jilijili.common.core.pojo.dto.OperationLogDto;
import wang.jilijili.common.core.pojo.entity.OperationLog;
import wang.jilijili.common.core.pojo.vo.Result;
import wang.jilijili.common.core.service.OperationLogService;

/**
 * 日志记录管理
 *
 * @author amani
 * @since 2023-04-04 11:14:24
 */
@Tag(name = "日志管理", description = "日志记录只提供查询服务")
@RestController
@RequestMapping("/operationLog")
public class OperationLogController {
    /**
     * 服务对象
     */
    OperationLogService operationService;

    OperationLogConvertBo operationLogConvertBo;

    public OperationLogController(OperationLogService operationService, OperationLogConvertBo operationLogConvertBo) {
        this.operationService = operationService;
        this.operationLogConvertBo = operationLogConvertBo;
    }

    /**
     * 分页查询所有数据
     *
     * @param operationLogDto 查询实体
     * @return 所有数据
     */
    @GetMapping("/list")
    public Result<IPage<OperationLog>> selectAll(OperationLogDto operationLogDto) {
        IPage<OperationLog> page = new Page<>(operationLogDto.getPage(), operationLogDto.getSize());
        OperationLog operationLogEntity = operationLogConvertBo.toOperationLogEntity(operationLogDto);
        return Result.ok(this.operationService.page(page, new QueryWrapper<>(operationLogEntity)));
    }

}

