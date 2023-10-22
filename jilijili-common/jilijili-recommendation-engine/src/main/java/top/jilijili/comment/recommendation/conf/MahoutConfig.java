package top.jilijili.comment.recommendation.conf;

import lombok.extern.slf4j.Slf4j;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;

/**
 * @author Amani
 * @date 2023年10月22日 10:38
 */
@Slf4j
@Configuration
public class MahoutConfig {

    @Value("${cf.file}")
    public String filePath;

    @Bean
    public FileDataModel fileDataModel() throws IOException {
        return new FileDataModel(new File(filePath));
    }


}
