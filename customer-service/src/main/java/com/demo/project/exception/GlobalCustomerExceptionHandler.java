package com.demo.project.exception;

import java.util.Collections;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.demo.project.model.ApiError;

@ControllerAdvice
public class GlobalCustomerExceptionHandler {

    @ExceptionHandler({ OperationFailedException.class })
    public final ResponseEntity<ApiError> handleOperationFailedException(OperationFailedException exception) {
        List<String> errors = Collections.singletonList(exception.getMessage());
        ApiError apiError = new ApiError(errors);
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler({NotFoundException.class })
    public final ResponseEntity<ApiError> handleNotFoundException(NotFoundException exception) {
        List<String> errors = Collections.singletonList(exception.getMessage());
        ApiError apiError = new ApiError(errors);
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler({BadRequestException.class })
    public final ResponseEntity<ApiError> handleBadRequestException(BadRequestException exception) {
        List<String> errors = Collections.singletonList(exception.getMessage());
        ApiError apiError = new ApiError(errors);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler({EmptyResultDataAccessException.class})
    public final ResponseEntity<ApiError> handleEmptyResultDataAccessException(EmptyResultDataAccessException exception) {
        List<String> errors = Collections.singletonList(exception.getMessage());
        ApiError apiError = new ApiError(errors);
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }


}
