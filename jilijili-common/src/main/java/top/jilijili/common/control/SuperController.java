package top.jilijili.common.control;


import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.enmus.ExcelType;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import top.jilijili.common.entity.Result;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Amani
 * @date 2023年07月12日 18:57
 */
@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin
public class SuperController {

    /**
     * 导入
     *
     * @param inputStream 上传的文件流
     * @param service     具体服务
     * @param entity      实体类
     * @param <S>         服务
     * @param <E>         实体类
     * @return 读取到的数据 | 错误信息
     */
    protected <S extends IService<E>, E> Object importData(InputStream inputStream, S service, Class<E> entity) {
        ImportParams params = new ImportParams();
        params.setHeadRows(1);
        params.setTitleRows(1);
        params.setNeedSave(true);

        List<E> list = new ArrayList<>();
        ExcelImportResult<E> result = new ExcelImportResult<>();
        try {
            result = ExcelImportUtil.importExcelVerify(inputStream, entity, params);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        if (result.isVerfiyFail()) {
            // 验证失败
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            try {
                result.getWorkbook().write(outputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // 设置响应头
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", URLEncoder.encode("错误信息.xlsx", StandardCharsets.UTF_8));
            // 返回响应Entity
            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .body(outputStream.toByteArray());
        } else {
            // 校验通过，获取导入的数据
            list = result.getList();
        }
        // 持久化到数据库
        return Result.ok(service.saveBatch(list));
    }

    /**
     * 导出
     *
     * @return
     */
    protected <E> void exportData(HttpServletResponse response, String title, String sheetName, Class<E> entity, List<E> data) {
        ExportParams params = new ExportParams(title, sheetName, ExcelType.XSSF);
        params.setFixedTitle(true);
        Workbook workbook = ExcelExportUtil.exportExcel(params, entity, data);
        try {
            OutputStream os = response.getOutputStream();
            workbook.write(os);
            os.flush();
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            log.error(e.getMessage());
        }
        response.setStatus(HttpStatus.OK.value());
        log.info("文件导出完成!!!");
    }

}
