package wang.jilijili.common.core.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * 邮箱DTO
 *
 * @author ican
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MailDTO {

    /**
     * 接收者邮箱号
     */
    private String toEmail;

    /**
     * 主题
     */
    private String subject;

    /**
     * 内容
     */
    private String content;

    /**
     * 内容信息
     */
    private Map<String, Object> contentMap;

    /**
     * 邮件模板
     */
    private String template;
}
