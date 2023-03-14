package wang.jilijili.framework.handler;


import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import wang.jilijili.common.exception.BizException;
import wang.jilijili.common.exception.ErrorResponse;
import wang.jilijili.common.exception.ExceptionType;
import wang.jilijili.system.pojo.vo.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 * @Date: 2023/1/28 11:01
 * @Description:
 */
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = TokenExpiredException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<?> repeatException(TokenExpiredException e) {
        e.printStackTrace();
        return Result.fail(HttpStatus.BAD_REQUEST, "令牌过期了,请重新登录");
    }

    @ExceptionHandler(value = DataAccessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<?> repeatException(DataAccessException e) {

        return Result.fail(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }


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
