package wang.jilijili.common.core.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.jilijili.common.core.pojo.bo.OperationLogConvertBo;
import wang.jilijili.common.core.pojo.dto.OperationLogDto;
import wang.jilijili.common.core.pojo.vo.OperationLogVO;
import wang.jilijili.common.core.pojo.vo.Result;
import wang.jilijili.common.core.service.OperationLogService;

import java.util.List;

/**
 * 日志记录管理
 *
 * @author amani
 * @since 2023-04-04 11:14:24
 */
@Tag(name = "日志管理", description = "日志记录")
@RestController
@RequestMapping("/sys/log")
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
    public Result<IPage<OperationLogVO>> selectAll(OperationLogDto operationLogDto) {
        IPage<OperationLogVO> page = new Page<>(operationLogDto.getPage(), operationLogDto.getSize());

        List<OperationLogVO> list = this.operationService.list(page, operationLogDto);
        page.setRecords(list);
        return Result.ok(page);
    }

}

