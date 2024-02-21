package com.project.planner.common;

import com.project.planner.common.responses.ErrorResponse;
import com.project.planner.exceptions.LocalFileNotFoundException;
import com.project.planner.exceptions.StorageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(StorageException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handle_StorageException(
            StorageException exception,
            WebRequest request
    ) {
        ErrorResponse err_response = ErrorResponse
                .builder()
                .setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .setMessage(exception.getMessage())
                .build();

        return ResponseEntity.status(err_response.getStatusCode()).body(err_response);
    }
}
