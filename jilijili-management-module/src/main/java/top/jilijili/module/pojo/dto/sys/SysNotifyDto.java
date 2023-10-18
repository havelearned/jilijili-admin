package top.jilijili.module.pojo.dto.sys;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import top.jilijili.common.group.Insert;
import top.jilijili.module.pojo.dto.SuperDto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @TableName sys_notify
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SysNotifyDto extends SuperDto implements Serializable {
    /**
     * 通知id，唯一标识符
     */
    private Long notifyId;

    /**
     * 发送通知的用户或系统组件的标识符
     */
    private Long senderId;

    /**
     * 接收通知的用户或系统组件的标识符
     */
    private Long receiverId;
    private List<Long> receiverIds;

    /**
     * 通知类型，例如警报、错误、新用户注册,全体通告等
     */
    private String notifyType;

    /**
     * 通知的内容，可以是文本、html、json等形式的消息
     */
    private String notifyContent;

    /**
     * 通知的状态，例如已读(1)、未读(0)、已处理等
     */
    private Integer notifyStatus;

    /**
     * 通知创建时间
     */
    private Date createdTime;

    /**
     * 通知更新时间
     */
    private Date updatedTime;

    /**
     * 是否立即发送通知
     * 1: 添加并且立即发布
     * 2: 草稿
     * 3: 定时发布
     */
    @NotNull(message = "发布模式不能为空",groups = Insert.class)
    private Integer publish;

    private String username;
    private Long userId;


}