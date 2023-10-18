package top.jilijili.system.service.impl;

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.minio.ListObjectsArgs;
import io.minio.RemoveObjectsArgs;
import io.minio.Result;
import io.minio.errors.*;
import io.minio.messages.DeleteError;
import io.minio.messages.DeleteObject;
import io.minio.messages.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import top.jilijili.system.common.config.MinioConfig;
import top.jilijili.module.pojo.entity.sys.FileItem;
import top.jilijili.module.pojo.entity.sys.FileManagement;
import top.jilijili.module.pojo.dto.sys.FileManagementDto;
import top.jilijili.module.pojo.vo.sys.FileManagementVo;
import top.jilijili.system.service.FileManagementService;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class FileManagementServiceImplTest {

    public static Logger log = LogManager.getLogger(FileManagementServiceImplTest.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private FileManagementService fileManagementService;

    @Autowired
    MinioConfig minioConfig;

    @Test
    public void download() {
        top.jilijili.common.entity.Result<String> stringResult = this.fileManagementService.fileDownload("other/asd.webp", 10);

        System.out.println("===============================================================>");
        System.out.println(JSONObject.toJSONString(stringResult));
    }

    @Test
    public void queryFile() {
        FileManagementDto build = FileManagementDto.builder()
                .page(0)
                .size(100)
                .filePath("other/")
                .build();
        top.jilijili.common.entity.Result<IPage<FileManagementVo>> list = fileManagementService.getList(build);
        System.out.println("===============================================================>");
        System.out.println(JSONObject.toJSONString(list));
    }

    @Test
    public void removeFIleDir() {
        // Minio 不支持删除目录,必须递归删除目录下的所有文件
        DeleteObject object = new DeleteObject("test/logo.ico");
        List<DeleteObject> objects = new ArrayList();
        objects.add(object);
        Iterable<Result<DeleteError>> results = MinioConfig.minioClient.removeObjects(RemoveObjectsArgs.builder()
                .bucket(minioConfig.getBucket())
                .objects(objects)
                .bypassGovernanceMode(true)
                .build());

        Iterator<Result<DeleteError>> iterator = results.iterator();
        try {
            while (iterator.hasNext()) {
                Result<DeleteError> nexted = iterator.next();
                DeleteError deleteError = nexted.get();
                System.out.println(deleteError.message());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void addFileDir() {
        FileManagementDto build = FileManagementDto.builder()
                .filePath("test/")
                .build();
        List<FileManagementDto> objects = new ArrayList<>();
        objects.add(build);
        top.jilijili.common.entity.Result<String> stringResult = fileManagementService.addFileDir(objects);
        System.out.println(stringResult);
    }

    @Test
    public void minioTest() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        Iterable<Result<Item>> results = MinioConfig.minioClient.listObjects(
                ListObjectsArgs.builder()
                        .bucket(minioConfig.getBucket())
                        .prefix("Excel/")
//                        .recursive(true)
                        .build());

        Iterator<Result<Item>> iterator = results.iterator();
        System.out.println("===============================================>");
        List<FileItem> resultItem = new ArrayList<>();
        while (iterator.hasNext()) {
            Result<Item> next = iterator.next();
            Item item = next.get();
            FileItem fileItem = new FileItem();
            BeanUtils.copyProperties(item, fileItem);
            resultItem.add(fileItem);

//            Item item = next.get();
//            log.info("文件名称:{}\t 文件大小:{}\t 最后修改时间:{}\t 文件版本:{}\t 是否目录:{}\t",
//                    item.objectName(), item.size(), item.lastModified() == null ? "修改时间为空" : item.lastModified(), item.etag(), item.isDir());

        }
        String jsonString = JSONObject.toJSONString(resultItem);
        System.out.println(jsonString);
        System.out.println("===============================================>");


    }

    @Test
    public void fileTest() {
        long count = this.fileManagementService.lambdaQuery().count();
        List<FileManagement> list = this.fileManagementService.list();
        List<FileManagement> list1 = list.stream().map(file -> file.setFileType(FileUtil.getSuffix(file.getFileName())))
                .toList();

//        boolean b = this.fileManagementService.updateBatchById(list1);
//        System.out.println("操作成功 ?" + b);
    }


}