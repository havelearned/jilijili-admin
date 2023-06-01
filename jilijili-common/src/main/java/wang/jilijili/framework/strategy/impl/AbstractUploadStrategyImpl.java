package wang.jilijili.framework.strategy.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.http.HttpStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import wang.jilijili.common.exception.BizException;
import wang.jilijili.framework.strategy.UploadStrategy;

import java.io.IOException;

import static wang.jilijili.common.exception.ExceptionType.UPLOAD_FAILED;

/**
 * @author admin
 */
@Getter
@Setter
@Slf4j
public abstract class AbstractUploadStrategyImpl implements UploadStrategy {

    @Override
    public String uploadFile(MultipartFile file, String filePath) {

        try {
            //region 获取文件md5值 -> 获取文件后缀名 -> 生成相对路径
            String fileMd5 = DigestUtil.md5Hex(file.getInputStream());
            String suffix = FileUtil.getSuffix(file.getOriginalFilename());
            String fileRelativePath = String.format("%s/%s.%s", filePath, fileMd5, suffix);

            initClient();

            //region 检测文件是否已经存在，不存在则进行上传操作
            if (!checkFileIsExisted(fileRelativePath)) {
                executeUpload(file, fileRelativePath);
            }


            return getPublicNetworkAccessUrl(fileRelativePath);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BizException(UPLOAD_FAILED.getMessage() + ":" + e.getMessage(), HttpStatus.HTTP_INTERNAL_ERROR);
        }
    }


    /**
     * 初始化客户端
     */
    public abstract void initClient();

    /**
     * 检查文件是否已经存在（文件MD5值唯一）
     *
     * @param fileRelativePath 文件相对路径
     * @return true 已经存在  false 不存在
     */
    public abstract boolean checkFileIsExisted(String fileRelativePath);

    /**
     * 执行上传操作
     *
     * @param file             文件
     * @param fileRelativePath 文件相对路径
     * @throws IOException io异常信息
     */
    public abstract void executeUpload(MultipartFile file, String fileRelativePath) throws IOException;

    /**
     * 获取公网访问路径
     *
     * @param fileRelativePath 文件相对路径
     * @return 公网访问绝对路径
     */
    public abstract String getPublicNetworkAccessUrl(String fileRelativePath);


    public abstract void saveFileInfo(MultipartFile file, String fileRelativePath);
}
