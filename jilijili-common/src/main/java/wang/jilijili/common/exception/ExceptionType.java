package wang.jilijili.common.exception;

/**
 * @author admin
 * @Date: 2023/1/28 10:53
 * @Description:
 */
public enum ExceptionType {

    /**
     *
     * */
    INNER_ERROR(500, "系统内部错误"),
    UNAUTHORIZED(401, "未登录"),
    BAD_REQUEST(400, "请求错误"),
    FORBIDDEN(403, "无权操作"),
    NOT_FOUND(404, "未找到3"),
    USER_NAME_DUPLICATE(4001001, "用户名重复"),
    USER_NOT_FOND(40401001,"用户未找到"),
    USERNAME_OR_PASSWORD_ERROR(40001002,"用户名或者密码错误"),
    USER_NOT_ENABLED(50001001,"用户未启用"),
    USER_NOT_LOCKED(50001002,"用户被锁定"),
    USER_EXPORT_ERROR(50001003,"导出失败"),
    UPLOAD_FAILED(500061001,"文件上传失败"),
    FILE_DOES_NOT_EXIST(500061002,"文件不存在"),
    FILE_DIR_CREATED_FAILED(500061003,"文件夹创建失败"),
    OSS_INIT_FAIL(500062001,"OSS init fail"),
    ;

    private final Integer code;
    private final String message;

    ExceptionType(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
