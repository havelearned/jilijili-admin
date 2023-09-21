package top.jilijili.module.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * @author Amani
 * @date 2023年08月22日 10:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Accessors(chain = true)
public class WebRTCDto extends SuperDto {
    private String userId;
    private String type;
    private String office;
    private String candidate;
    private Date createdTime;
    private Date updatedTime;
}
