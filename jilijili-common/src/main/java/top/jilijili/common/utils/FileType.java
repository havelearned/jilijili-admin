package top.jilijili.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Amani
 * @date 2023年03月27日 下午6:31
 */
public class FileType {

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
    public String assignFileTypes(String endName) {
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

}
