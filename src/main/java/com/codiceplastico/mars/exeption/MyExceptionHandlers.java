package com.codiceplastico.mars.exeption;

import com.codiceplastico.mars.model.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyExceptionHandlers {
    @ExceptionHandler(ObstacleCollisionException.class)
    public ResponseEntity<?> handle(ObstacleCollisionException ex) {
        return ResponseEntity.internalServerError().body(new ApiResponse(ex.getMessage())) ;
    }
}