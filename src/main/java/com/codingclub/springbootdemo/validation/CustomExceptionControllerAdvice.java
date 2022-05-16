package com.codingclub.springbootdemo.validation;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@RestController
public class CustomExceptionControllerAdvice {

    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<?> handleException(ConstraintViolationException ex, WebRequest request){
        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<?> constraintViolation : ex.getConstraintViolations()){
            errors.add(constraintViolation.getRootBeanClass().getName()+" "+ constraintViolation.getPropertyPath()+": "+ constraintViolation.getMessage());
        }
        ErrorRespone errorRespone = new ErrorRespone();
        errorRespone.setStatus(HttpStatus.BAD_REQUEST);
        errorRespone.setMessage(ex.getLocalizedMessage());
        errorRespone.setErrors(errors);
        return new ResponseEntity<Object>(errorRespone, new HttpHeaders(), errorRespone.getStatus());
    }
}
