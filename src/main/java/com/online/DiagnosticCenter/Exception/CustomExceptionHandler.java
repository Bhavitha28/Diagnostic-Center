package com.online.DiagnosticCenter.Exception;


import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.GenericJDBCException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {



    @ExceptionHandler(ResourceNotFoundException.class)
    public static ResponseEntity<ErrorResponse> dataNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UniqueConstraintException.class)
    public static ResponseEntity<ErrorResponse> uniqueConstraintException(UniqueConstraintException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage(), HttpStatus.CONFLICT.value()),
                HttpStatus.CONFLICT);
    }

    //StandardServletMultipartResolver
    @ExceptionHandler({MultipartException.class, MaxUploadSizeExceededException.class})
    public static ResponseEntity<ErrorResponse> handleMultipartException(MultipartException e) {
        return new ResponseEntity<>(new ErrorResponse(NestedExceptionUtils.getMostSpecificCause(e).getMessage(),
                HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public static ResponseEntity<ErrorResponse> httpException(Exception ex) {
        ErrorResponse error = new ErrorResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({DataIntegrityViolationException.class, GenericJDBCException.class, JpaSystemException.class})
    public ResponseEntity<ErrorResponse> handleTriggerException(Exception ex) {
        return new ResponseEntity<>(new ErrorResponse(NestedExceptionUtils.getMostSpecificCause(ex).getMessage(),
                HttpStatus.CONFLICT.value()), HttpStatus.CONFLICT);
    }

    @Override
    @NonNull
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  @Nullable HttpHeaders headers, @NonNull HttpStatus status, @Nullable WebRequest request) {
        ErrorResponse apiError = new ErrorResponse("Validation Error",
                ex.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList()));
        return new ResponseEntity<>(apiError, headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<ErrorResponse> handleConstraintViolatioException(Exception ex) {
        return new ResponseEntity<>(new ErrorResponse(NestedExceptionUtils.getMostSpecificCause(ex).getMessage(),
                HttpStatus.CONFLICT.value()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({InvalidDataException.class})
    public ResponseEntity<ErrorResponse> handleInvalidInputException(Exception ex) {
        return new ResponseEntity<>(new ErrorResponse(NestedExceptionUtils.getMostSpecificCause(ex).getMessage(),
                HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }
}

