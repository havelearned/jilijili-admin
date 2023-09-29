package top.jilijili.module.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * @author Amani
 * @date 2023年09月28日 9:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Accessors(chain = true)
public class FileManagementDto {
    /**
     *
     */
    private Long fileId;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 文件类型id
     */
    private Long fileTypeId;

    /**
     *
     */
    private Date updatedTime;

    /**
     *
     */
    private Date createdTime;

    /**
     * 文件版本号
     */
    private Integer version;
}
