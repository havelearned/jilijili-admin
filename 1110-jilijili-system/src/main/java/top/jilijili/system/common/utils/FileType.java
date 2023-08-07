package top.jilijili.system.common.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.crypto.digest.DigestUtil;
import org.apache.commons.compress.utils.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Amani
 * @date 2023年03月27日 下午6:31
 */
public class FileType {

    public static final String WIN_UPLOAD_DIR = "E:\\idea_project\\jilijili-admin\\upload\\image\\";
    public static final String linux_UPLOAD_DIR = "\\temp\\jilijili-admin\\upload\\image\\";

    /**
     * 图片
     */
    public static final String IMAGE = "images";

    /**
     * 软件应用
     */
    public static final String SOFTWARE = "software";
    /**
     * 音视频文件
     */
    public static final String AUDIO = "audio";

    public static final String OTHER = "other";
    public static final Map<String, String[]> ASSORT_EXTENSION = new HashMap<>() {
        {
            put("images", new String[]{"bmp", "gif", "jpg", "jpeg", "png"});
            put("other", new String[]{"doc", "docx", "xls", "xlsx", "ppt", "pptx", "html", "htm", "txt"});
            put("software", new String[]{"rar", "zip", "gz", "bz2"});
            put("audio", new String[]{"mp4", "avi", "rmvb", "mp3", "wav", "wma", "mpeg", "mpg", "mid", "flac", "ape"});
        }
    };


    /**
     * @param endName 后缀名称
     * @return 归档目录
     */
    public static String assignFileTypes(String endName) {
        if (endName.contains(".")) {
            endName = endName.replace(".", "");
        }
        for (Map.Entry<String, String[]> next : ASSORT_EXTENSION.entrySet()) {
            String[] value = next.getValue();
            for (String val : value) {
                if (val.contains(endName)) {
                    return next.getKey();
                }
            }
        }
        return OTHER;
    }

    public static String downloadImage(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);
        InputStream in = url.openStream();
        String suffix = "." + FileUtil.getSuffix(imageUrl);
        suffix = suffix.substring(0, suffix.indexOf('?'));
        String filename = DigestUtil.md5Hex(imageUrl);

        Path tempImagePath;
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            tempImagePath = Files.createTempFile(filename, suffix); // 创建临时文件
        } else {
            tempImagePath = Files.createTempFile(filename, suffix);
        }
        Files.copy(in, tempImagePath, StandardCopyOption.REPLACE_EXISTING);
        IOUtils.closeQuietly(in);
        System.out.println(tempImagePath.toString());
        return tempImagePath.toString();
    }
}
