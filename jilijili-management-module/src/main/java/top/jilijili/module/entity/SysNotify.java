package top.jilijili.module.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName sys_notify
 */
@TableName(value = "sys_notify")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SysNotify implements Serializable {
    /**
     * 通知id，唯一标识符
     */
    @TableId(value = "notify_id", type = IdType.ASSIGN_ID)
    private Long notifyId;

    /**
     * 发送通知的用户或系统组件的标识符
     */
    @TableField(value = "sender_id")
    private Long senderId;

    /**
     * 接收通知的用户或系统组件的标识符
     */
    @TableField(value = "receiver_id")
    private Long receiverId;

    /**
     * 通知类型，例如警报、错误、新用户注册,全体通告等
     */
    @TableField(value = "notify_type")
    private String notifyType;

    /**
     * 通知的内容，可以是文本、html、json等形式的消息
     */
    @TableField(value = "notify_content")
    private String notifyContent;

    /**
     * 通知的状态，例如已读(1)、未读(0)、已处理等
     */
    @TableField(value = "notify_status")
    private Integer notifyStatus;

    /**
     * 通知创建时间
     */
    @TableField(value = "created_time", fill = FieldFill.INSERT)
    private Date createdTime;

    /**
     * 通知更新时间
     */
    @TableField(value = "updated_time", fill = FieldFill.UPDATE)
    private Date updatedTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}