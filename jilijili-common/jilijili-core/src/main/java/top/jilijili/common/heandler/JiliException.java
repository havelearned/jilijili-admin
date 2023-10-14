package top.jilijili.common.heandler;

import lombok.Getter;
import top.jilijili.common.enums.ErrorType;

/**
 * @author Amani
 * @date 2023年09月05日 16:23
 */
@Getter
public class JiliException extends RuntimeException {
    private String msg;
    private Integer code;

    public JiliException(String msg) {
        super(msg);

    }

    public JiliException(String msg, Integer code) {
        super(msg);
        this.code = code;

    }

    public JiliException(ErrorType errorType) {
        this.code = errorType.getCode();
        this.msg = errorType.getMsg();
    }


}
