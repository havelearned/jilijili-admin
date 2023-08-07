package top.jilijili.common.core.pojo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author Amani
 * @date 2023年02月21日 18:12
 */
@Data
public class Code2SessionDto extends CreateTokenDto {

    @NotBlank(message = "code不能为空")
    private String code;

}
