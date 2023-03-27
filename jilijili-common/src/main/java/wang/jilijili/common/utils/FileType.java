package wang.jilijili.common.utils;

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
    public static final String IMAGE = "IMAGE";
    /**
     * 办公文件
     */
    public static final String OFFICE = "OFFICE";
    /**
     * 压缩为文件
     */
    public static final String COMPRESSED_FILE = "COMPRESSED_FILE";
    /**
     * 音视频文件
     */
    public static final String AUDIO = "AUDIO";
    /**
     * PDF文件
     */
    public static final String PDF = "PDF";

    public static final String OTHER = "OTHER";

    public static final Map<String, String[]> ASSORT_EXTENSION = new HashMap<>() {
        {
            put("IMAGE", new String[]{"bmp", "gif", "jpg", "jpeg", "png"});
            put("OFFICE", new String[]{"doc", "docx", "xls", "xlsx", "ppt", "pptx", "html", "htm", "txt"});
            put("COMPRESSED_FILE", new String[]{"rar", "zip", "gz", "bz2"});
            put("AUDIO", new String[]{"mp4", "avi", "rmvb",});
            put("PDF", new String[]{"pdf"});
        }
    };

    public static final String[] DEFAULT_ALLOWED_EXTENSION = {
            // 图片
            "bmp", "gif", "jpg", "jpeg", "png",
            // word excel powerpoint
            "doc", "docx", "xls", "xlsx", "ppt", "pptx", "html", "htm", "txt",
            // 压缩文件
            "rar", "zip", "gz", "bz2",
            // 视频格式
            "mp4", "avi", "rmvb",
            // pdf
            "pdf"};
}
