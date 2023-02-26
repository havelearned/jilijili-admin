package wang.jilijili.music.handler;


import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import wang.jilijili.music.exception.BizException;
import wang.jilijili.music.exception.ErrorResponse;
import wang.jilijili.music.exception.ExceptionType;
import wang.jilijili.music.pojo.vo.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Amani
 * @Date: 2023/1/28 11:01
 * @Description:
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = BizException.class)
    public Result<?> bizException(BizException e) {
        return Result.fail(
                e.getCode(),
                String.format("%s", e.getMessage()));
    }


    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Result<?> accessDeniedException(AccessDeniedException e) {
        return Result.fail(
                ExceptionType.FORBIDDEN.getCode(),
                String.format("%s %s", ExceptionType.FORBIDDEN.getMessage(), e.getMessage()));

    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<List<ErrorResponse>> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ErrorResponse> responses = new ArrayList<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(ExceptionType.BAD_REQUEST.getCode());
            errorResponse.setMessage(error.getDefaultMessage());
            responses.add(errorResponse);
        });
        return Result.fail(responses);
    }


}
