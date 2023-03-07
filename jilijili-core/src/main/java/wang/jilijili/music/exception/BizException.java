package wang.jilijili.music.exception;

/**
 * @author admin
 * @Date: 2023/1/28 10:52
 * @Description:
 */
public class BizException extends RuntimeException {
    private final Integer code;

    public BizException(ExceptionType exceptionType) {
        super(exceptionType.getMessage());
        this.code = exceptionType.getCode();
    }

    public BizException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
