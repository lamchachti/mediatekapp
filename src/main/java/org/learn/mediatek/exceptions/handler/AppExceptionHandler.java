package org.learn.mediatek.exceptions.handler;

import org.learn.mediatek.exceptions.EntityAlreadyExistsException;
import org.learn.mediatek.exceptions.EntityNotFoundException;
import org.learn.mediatek.shared.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<Object> entityNotFoundException(EntityNotFoundException entityNotFoundException){
        ErrorMessage errorMessage= ErrorMessage.builder()
                .message(entityNotFoundException.getMessage())
                .timestamp(new Date())
                .code(404)
                .build();
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({EntityAlreadyExistsException.class})
    public ResponseEntity<Object> entityAlreadyExistsException(EntityAlreadyExistsException entityAlreadyExistsException){
        ErrorMessage errorMessage= ErrorMessage.builder()
                .message(entityAlreadyExistsException.getMessage())
                .timestamp(new Date())
                .code(409)
                .build();
        return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> FieldsNotValid(MethodArgumentNotValidException ex){
        Map<String,String > errors= new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error->errors.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(errors,new HttpHeaders(),HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
