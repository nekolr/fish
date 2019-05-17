package com.nekolr.fish.exception.handler;

import com.nekolr.fish.exception.BadRequestException;
import com.nekolr.fish.exception.EntityExistException;
import com.nekolr.fish.exception.ErrorResponse;
import com.nekolr.fish.util.ThrowableUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理所有未知异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error(ThrowableUtils.getStackTrace(e));
        ErrorResponse errorResponse = new ErrorResponse(BAD_REQUEST.value(), e.getMessage());
        return this.buildResponseEntity(errorResponse);
    }

    /**
     * 处理所有无效请求异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException e) {
        log.error(ThrowableUtils.getStackTrace(e));
        ErrorResponse errorResponse = new ErrorResponse(e.getStatus(), e.getMessage());
        return this.buildResponseEntity(errorResponse);
    }

    /**
     * 处理所有实体已经存在异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = EntityExistException.class)
    public ResponseEntity<ErrorResponse> handleEntityExistException(EntityExistException e) {
        log.error(ThrowableUtils.getStackTrace(e));
        ErrorResponse errorResponse = new ErrorResponse(BAD_REQUEST.value(), e.getMessage());
        return this.buildResponseEntity(errorResponse);
    }

    /**
     * 创建响应实体
     *
     * @param errorResponse
     * @return
     */
    private ResponseEntity<ErrorResponse> buildResponseEntity(ErrorResponse errorResponse) {
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorResponse.getStatus()));
    }
}
