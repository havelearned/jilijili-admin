package top.jilijili.system.heandler;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotRoleException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.jilijili.system.entity.vo.Result;

import java.util.stream.Collectors;

/**
 * @author Amani
 * @date 2023年06月22日 1:36
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(NotRoleException.class)
    @ResponseBody
    public Result<String> notLoginException(NotRoleException e) {
        log.info("{}",e.getMessage());
        return Result.fail(401,"无权访问");
    }

    @ExceptionHandler(NotLoginException.class)
    @ResponseBody
    public Result<String> notLoginException(NotLoginException e) {
        log.info("token错误=>{}", e.getMessage());
        return Result.fail(e.getMessage());
    }


    /**
     * 参数格式异常
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public String HttpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e) {
        return e.getMessage();
    }


    /**
     * 处理请求参数格式错误 @RequestBody上使用@Valid 实体上使用@NotNull等，验证失败后抛出的异常是MethodArgumentNotValidException异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Result<String> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        return Result.fail(e.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.joining("; ")));
    }


    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public String validExceptionHandler(BindException e) {
        e.printStackTrace();
//        commonResp.setMessage(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return e.getMessage();
    }

}
