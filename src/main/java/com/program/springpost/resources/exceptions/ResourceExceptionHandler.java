package com.program.springpost.resources.exceptions;

import com.program.springpost.service.exceptions.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> ObjectNotFoundException(ObjectNotFoundException e, HttpServletRequest request){
        Instant timestamp = Instant.now();
        HttpStatus status = HttpStatus.NOT_FOUND;
        String error = "Not Found";
        String message = e.getMessage();
        String path = request.getRequestURI();
        StandardError standardError = new StandardError(timestamp, status.value(), error, message, path);
        return ResponseEntity.status(status).body(standardError);
    }

}
