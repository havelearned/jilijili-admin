package wang.jilijili.framework.strategy.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import wang.jilijili.common.exception.BizException;
import wang.jilijili.common.exception.ExceptionType;
import wang.jilijili.framework.config.UploadStoreProperties;

import java.io.IOException;

/**
 * @author Amani
 * @date 2023年03月16日 下午4:20
 */
@Slf4j
@Service
public class OssUploadStrategyImpl extends AbstractUploadStrategyImpl{

    UploadStoreProperties uploadStoreProperties;

    public OssUploadStrategyImpl(UploadStoreProperties uploadStoreProperties) {
        this.uploadStoreProperties = uploadStoreProperties;
    }

    OSS ossClient;

    @Override
    public void initClient() {
        UploadStoreProperties.ConfigEntity oss = uploadStoreProperties.getOss();
        try {
            ossClient = new OSSClientBuilder().build(oss.getEndpoint(),
                    oss.getAccessKey(),
                    oss.getAccessKeySecret());
            if(!this.ossClient.doesBucketExist(oss.getBucket())){
                ossClient.createBucket(oss.getBucket());
            }

        }catch (Exception e){
            e.printStackTrace();
            throw new BizException(ExceptionType.OSS_INIT_FAIL);
        }
        log.info("OssClient Init Success...");

    }

    @Override
    public boolean checkFileIsExisted(String fileRelativePath) {
        return true;

    }

    @Override
    public void executeUpload(MultipartFile file, String fileRelativePath) throws IOException {
        this.ossClient.putObject(uploadStoreProperties.getOss().getBucket(),
                fileRelativePath,
                file.getInputStream());

    }

    @Override
    public String getPublicNetworkAccessUrl(String fileRelativePath) {
        return uploadStoreProperties.getOss().getDomainUrl()+fileRelativePath;
    }
}
