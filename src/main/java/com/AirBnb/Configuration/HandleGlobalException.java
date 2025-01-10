package com.AirBnb.Configuration;

import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandleGlobalException {

   @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception e){
       return  new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
   }

}
