package top.jilijili.system.controller;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.jilijili.system.entity.SysDict;
import top.jilijili.system.entity.dto.SysDictDto;
import top.jilijili.system.entity.vo.Result;
import top.jilijili.system.mapper.ConvertMapper;
import top.jilijili.system.service.SysDictService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
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
@AllArgsConstructor
public class SysToolController {

    private SysDictService sysDictService;
    private ConvertMapper convertMapper;


    /**
     * 查询字典列表
     *
     * @param sysDictDto
     * @return 字典列表
     */
    @GetMapping("/dict/list")
    public Result<IPage<SysDict>> getDictList(SysDictDto sysDictDto) {
        IPage<SysDict> page = new Page<>(sysDictDto.getPage(), sysDictDto.getSize());
        sysDictDto.setDictionaryItemName(sysDictDto.getKeyword());
        return Result.ok(this.sysDictService.lambdaQuery()
                .like(StringUtils.hasText(sysDictDto.getDictionaryItemName()),
                        SysDict::getDictionaryItemName, sysDictDto.getDictionaryItemName())
                .like(StringUtils.hasText(sysDictDto.getDictionaryType()),
                        SysDict::getDictionaryType, sysDictDto.getDictionaryType())
                .eq(sysDictDto.getStatus() != null,
                        SysDict::getStatus, sysDictDto.getStatus())
                .eq(StringUtils.hasText(sysDictDto.getDictionaryCode()),
                        SysDict::getDictionaryCode, sysDictDto.getDictionaryCode())
                .between(sysDictDto.getCreatedTime() != null,
                        SysDict::getCreatedTime, sysDictDto.getCreatedTime(), sysDictDto.getComparisonTime())
                .page(page));

    }

    /**
     * 通过id获取字典信息
     *
     * @param id
     * @return
     */
    @GetMapping("/dict/{id}")
    public Result<?> editDictStatus(@PathVariable("id") Serializable id) {
        return Result.ok(this.sysDictService.getById(id));
    }

    /**
     * 修改字典
     *
     * @param sysDictDto 字典dto
     * @return 修改后的字典信息,
     */
    @PutMapping("/dict")
    public Result<?> editDictItem(@RequestBody SysDictDto sysDictDto) {
        SysDict sysDict = this.convertMapper.toSysDict(sysDictDto);
        boolean isSuccess = this.sysDictService.updateById(sysDict);
        log.info("修改字典:{},是否成功:{}", sysDictDto, isSuccess);
        if (isSuccess) {
            return Result.ok(sysDict, "操作成功");
        }
        return Result.fail("操作失败");
    }


    /**
     * 添加字典
     *
     * @param sysDictDto dto
     * @return 添加后的字典
     */
    @PostMapping("/dict")
    public Result<?> insert(@RequestBody SysDictDto sysDictDto) {
        SysDict sysDict = this.convertMapper.toSysDict(sysDictDto);
        sysDict.setDictionaryCode(RandomUtil.randomString(32).toUpperCase());
        boolean isSuccess = this.sysDictService.save(sysDict);
        log.info("添加字典:{},是否成功:{}", sysDictDto, isSuccess);
        if (isSuccess) {
            return Result.ok(sysDict, "操作成功");
        }
        return Result.fail("操作失败");
    }

    /**
     * 通过删除一个或者多个字典信息
     *
     * @param idList 一个或者多个id
     * @return
     */
    @DeleteMapping("/dict")
    public Result<?> delDictItems(@RequestBody List<Serializable> idList) {
        log.info("删除字典:{}", idList.toString());
        return Result.ok(this.sysDictService.removeBatchByIds(idList));
    }


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
