package top.jilijili.module.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Amani
 * @date 2023年09月10日 9:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Accessors(chain = true)
public class SysNotifyVo implements Serializable {
    Serializable noticeId;
    String noticeTitle;
    String noticeContent;
    Integer noticeType;
    Integer status;
    Date createdTime;
}
