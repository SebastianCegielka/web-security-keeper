package com.github.sebastiancegielka.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    public ResponseEntity<List<ValidationError>> handleValidationException(ValidationException e){
        return new ResponseEntity<>(e.getErrors(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntryNotFoundException.class)
    @ResponseBody
    public ResponseEntity<String> handleEntryNotFoundException(EntryNotFoundException e){
        return new ResponseEntity<>(e.message(), HttpStatus.BAD_REQUEST);
    }
}
