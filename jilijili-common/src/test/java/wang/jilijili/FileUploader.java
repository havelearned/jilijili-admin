package wang.jilijili;

import io.minio.*;
import io.minio.errors.MinioException;
import io.minio.messages.Item;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;

public class FileUploader {
    public static void main(String[] args)
            throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        try {
            // Create a minioClient with the MinIO server playground, its access key and secret key.



            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint("150.158.94.163",9000,false)
                            .credentials("minioAdmin", "minioAdmin")
                            .region("Asia/Shanghai")
                            .build();


            ListObjectsArgs listObjectsArgs = new ListObjectsArgs();
            Iterable<Result<Item>> results = minioClient.listObjects(listObjectsArgs);
            Iterator<Result<Item>> iterator = results.iterator();
            while(iterator.hasNext()){
                Result<Item> next = iterator.next();
                System.out.println(next.get().size());
            }
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
            System.out.println("HTTP trace: " + e.httpTrace());
        }
    }
}