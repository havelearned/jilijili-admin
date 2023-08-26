package top.jilijili.system.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * @author Amani
 * @date 2023年08月23日 14:24
 */
@Slf4j
public class OfficeUtil {

    public static void setLicense() {
        Resource resource = new ClassPathResource("license.xml");
        com.aspose.words.License license = new com.aspose.words.License();
//        com.aspose.pdf.License pdfLicense = new com.aspose.pdf.License();
        try {
            license.setLicense(resource.getInputStream());
//            pdfLicense.setLicense(resource.getInputStream());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
