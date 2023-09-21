package top.jilijili.module.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * 多媒体聊天记录表
 * @author admin
 * @TableName cm_record
 */
@TableName(value ="cm_record")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Accessors(chain = true)
public class CmRecord implements Serializable {
    /**
     * 记录id
     */
    @TableId(value = "record_id", type = IdType.AUTO)
    private Long recordId;

    /**
     * 发送者id
     */
    @TableField(value = "sender_id")
    private Long senderId;

    /**
     * 回复者id
     */
    @TableField(value = "receiver_id")
    private Long receiverId;

    /**
     * 内容
     */
    @TableField(value = "message")
    private String message;

    /**
     * 内容类型:0 普通文本,2文件
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 发送时间
     */
    @TableField(value = "created_time",fill = FieldFill.INSERT)
    private Date createdTime;

    /**
     * 是否已读:0表示未读，1表示已读
     */
    @TableField(value = "is_read")
    private Integer isRead;

    /**
     * 是否删除:0表示未删除，1表示已删除
     */
    @TableField(value = "is_deleted")
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}