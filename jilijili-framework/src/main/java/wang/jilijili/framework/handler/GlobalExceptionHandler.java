package wang.jilijili.framework.handler;


import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import wang.jilijili.common.core.pojo.vo.Result;
import wang.jilijili.common.exception.BizException;
import wang.jilijili.common.exception.ErrorResponse;
import wang.jilijili.common.exception.ExceptionType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 * @Date: 2023/1/28 11:01
 * @Description:
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = JWTVerificationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<?> repeatException(Exception e) {

         log.error(e.getMessage());
        return Result.fail(HttpStatus.BAD_REQUEST, "令牌错误或者过期了,请重新登录-详细信息:" + e.getMessage());
    }

    @ExceptionHandler(value = QueryTimeoutException.class)
    public Result<?> queryTimeoutException(QueryTimeoutException e) {
         log.error(e.getMessage());
        return Result.fail(e.getMessage());

    }

    @ExceptionHandler(value = DataAccessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<?> repeatException(DataAccessException e) {
         log.error(e.getMessage());
        return Result.fail(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }


    @ExceptionHandler(value = BizException.class)
    public Result<?> bizException(BizException e) {
         log.error(e.getMessage());
        return Result.fail(
                e.getCode(),
                String.format("%s", e.getMessage()));
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Result<?> accessDeniedException(AccessDeniedException e) {
         log.error(e.getMessage());
        return Result.fail(
                ExceptionType.FORBIDDEN.getCode(),
                String.format("%s %s", ExceptionType.FORBIDDEN.getMessage(), e.getMessage()));

    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<List<ErrorResponse>> methodArgumentNotValidException(MethodArgumentNotValidException e) {
         log.error(e.getMessage());
        List<ErrorResponse> responses = new ArrayList<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(ExceptionType.BAD_REQUEST.getCode());
            errorResponse.setMessage(error.getDefaultMessage());
            responses.add(errorResponse);
        });
        return Result.fail(responses);
    }


    @ExceptionHandler(value = Exception.class)
    @Order(99)
    public Result<?> exception(Exception e) {
        log.error(e.getMessage());
        return Result.fail(e.getMessage());

    }

}
