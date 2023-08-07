package top.jilijili.common.core.pojo.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author Amani
 * @date 2023年04月04日 10:40
 */
@Data
public class FileManageDto extends QueryDto {
    /**
     * 文件名称(不包含后缀)
     */
    private String filename;

    /**
     * 文件全路径url
     */
    private String filepath;

    /**
     * 文件类型: .png  .jpg
     */
    private String type;

    /**
     * 文件大小 字节
     */
    private Long filesize;

    /**
     * 是否锁定
     */
    private Integer locked;


    /**
     * 访问时间
     */
    private Date accessTime;
}
