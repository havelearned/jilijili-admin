package wang.jilijili.music.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import wang.jilijili.music.pojo.bo.CreateTokenBo;

/**
 * @author Amani
 * @date 2023年03月01日 10:28
 */
@EqualsAndHashCode(callSuper = true)
@Schema(name = "创建微信Token条件")
@Data
public class CreateWeChatTokenDto extends CreateTokenBo {

    @NotBlank
    private String openId;


}
