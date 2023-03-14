package wang.jilijili.system.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author Amani
 * @date 2023年03月01日 10:28
 */
@Schema(name = "创建微信Token条件")
@Data
public class CreateWeChatTokenDto extends CreateTokenDto {

    @NotBlank
    private String openId;


}
