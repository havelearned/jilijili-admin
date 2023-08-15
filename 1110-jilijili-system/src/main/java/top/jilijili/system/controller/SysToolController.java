package top.jilijili.system.controller;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.lang.UUID;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 系统工具管理
 *
 * @author Amani
 * @date 2023年08月09日 12:25
 */
@Slf4j
@RestController
@RequestMapping("/sysTool")
public class SysToolController {

    /**
     * 图片转换
     *
     * @param type     图片类型: png,jpe,webp,gif
     * @param files    一个或者多个图片文件
     * @param response 返回zip文件
     * @throws IOException
     */
    @PostMapping("/convertImg/{type}")
    public void convertImg(
            @PathVariable(value = "type") String type,
            @RequestPart(value = "files") MultipartFile[] files,
            HttpServletResponse response) throws IOException {

        log.info("{}个图片文件转{}", files.length, type);
        if (files.length < 1) {
            return;
        }

        response.setContentType("application/zip");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode("ConvertImg.zip", StandardCharsets.UTF_8));

        ZipOutputStream zipOut = new ZipOutputStream(response.getOutputStream());
        for (MultipartFile file : files) {
            // 随机文件名称
            String filename = UUID.fastUUID().toString(true) + "." + type;

            // 加入zip item
            ZipEntry zipEntry = new ZipEntry(filename);
            zipOut.putNextEntry(zipEntry);

            // 得到当前文件字节数组,转为png图片
            InputStream inputStream = file.getInputStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream(file.getBytes().length);
            outputStream.write(file.getBytes());

            ImgUtil.convert(inputStream, type, outputStream);

            // 输出到zip内
            zipOut.write(outputStream.toByteArray());

            outputStream.close();
            inputStream.close();
            zipOut.closeEntry();

        }
        zipOut.close();
    }
}
