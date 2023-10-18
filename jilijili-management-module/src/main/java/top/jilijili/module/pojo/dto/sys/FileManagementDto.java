package top.jilijili.module.pojo.dto.sys;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import top.jilijili.common.group.Query;
import top.jilijili.module.pojo.dto.SuperDto;

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
public class FileManagementDto extends SuperDto {
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
    @Pattern(regexp = "^.*/$", message = "文件路径格式不正确", groups = Query.class)
    private String filePath;

    /**
     * 文件类型id
     */
    private String fileType;

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