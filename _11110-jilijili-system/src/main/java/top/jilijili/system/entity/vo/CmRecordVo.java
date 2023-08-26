package top.jilijili.system.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * 多媒体聊天记录表
 * @author admin
 * @TableName cm_record
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Accessors(chain = true)
public class CmRecordVo  {
    /**
     * 记录id
     */
    private String recordId;

    /**
     * 发送者id
     */
    private Long senderId;

    /**
     * 回复者id
     */
    private Long receiverId;

    /**
     * 内容
     */
    private String message;

    /**
     * 内容类型:0 普通文本,2文件
     */
    private Integer type;

    /**
     * 发送时间
     */
    private Date createdTime;

    /**
     * 是否已读:0表示未读，1表示已读
     */
    private Integer isRead;

    /**
     * 是否删除:0表示未删除，1表示已删除
     */
    private Integer isDeleted;

}