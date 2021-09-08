package com.example.demodocker.exceptions;

import com.example.demodocker.dto.ExceptionHandlerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler
    public ResponseEntity<ExceptionHandlerResponse> handleException(Exception exc){
        // catches all the exceptions. this will run if not custom exception is received.
        ExceptionHandlerResponse exceptionHandlerResponse = new ExceptionHandlerResponse();
        exceptionHandlerResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        exceptionHandlerResponse.setTimestamp(
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        exceptionHandlerResponse.setMessage(exc.getMessage());
        exceptionHandlerResponse.setThrowable(exc);
        return new ResponseEntity<>(exceptionHandlerResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionHandlerResponse> handleException(CannotDeleteUserException exc){
        // when item to be deleted is not there in cart ....
        ExceptionHandlerResponse exceptionHandlerResponse = new ExceptionHandlerResponse();
        exceptionHandlerResponse.setHttpStatus(HttpStatus.FORBIDDEN);
        exceptionHandlerResponse.setTimestamp(
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        exceptionHandlerResponse.setMessage(exc.getMessage());
        exceptionHandlerResponse.setThrowable(exc);
        return new ResponseEntity<>(exceptionHandlerResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionHandlerResponse> handleException(ProductAlreadyInTheCartException exc){
        // when item to be deleted is not there in cart ......
        ExceptionHandlerResponse exceptionHandlerResponse = new ExceptionHandlerResponse();
        exceptionHandlerResponse.setHttpStatus(HttpStatus.FORBIDDEN);
        exceptionHandlerResponse.setTimestamp(
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        exceptionHandlerResponse.setMessage(exc.getMessage());
        exceptionHandlerResponse.setThrowable(exc);
        return new ResponseEntity<>(exceptionHandlerResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionHandlerResponse> handleException(UserNotFoundException exc){
        // when user cannot be found .....
        ExceptionHandlerResponse exceptionHandlerResponse = new ExceptionHandlerResponse();
        exceptionHandlerResponse.setHttpStatus(HttpStatus.FORBIDDEN);
        exceptionHandlerResponse.setTimestamp(
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        exceptionHandlerResponse.setMessage(exc.getMessage());
        exceptionHandlerResponse.setThrowable(exc);
        return new ResponseEntity<>(exceptionHandlerResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionHandlerResponse> handleException(CannotDeleteCourseException exc){
        // when user cannot be found .....
        ExceptionHandlerResponse exceptionHandlerResponse = new ExceptionHandlerResponse();
        exceptionHandlerResponse.setHttpStatus(HttpStatus.FORBIDDEN);
        exceptionHandlerResponse.setTimestamp(
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        exceptionHandlerResponse.setMessage(exc.getMessage());
        exceptionHandlerResponse.setThrowable(exc);
        return new ResponseEntity<>(exceptionHandlerResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionHandlerResponse> handleException(RoleAlreadyPresentException exc){
        // when role cannot be added  .....
        ExceptionHandlerResponse exceptionHandlerResponse = new ExceptionHandlerResponse();
        exceptionHandlerResponse.setHttpStatus(HttpStatus.FORBIDDEN);
        exceptionHandlerResponse.setTimestamp(
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        exceptionHandlerResponse.setMessage(exc.getMessage());
        exceptionHandlerResponse.setThrowable(exc);
        return new ResponseEntity<>(exceptionHandlerResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionHandlerResponse> handleException(InterestAlreadyPresentException exc){
        // when interest cannot be added  .....
        ExceptionHandlerResponse exceptionHandlerResponse = new ExceptionHandlerResponse();
        exceptionHandlerResponse.setHttpStatus(HttpStatus.FORBIDDEN);
        exceptionHandlerResponse.setTimestamp(
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        exceptionHandlerResponse.setMessage(exc.getMessage());
        exceptionHandlerResponse.setThrowable(exc);
        return new ResponseEntity<>(exceptionHandlerResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionHandlerResponse> handleException(CartEmptyException exc){
        // when checkout cannot be completed .....
        ExceptionHandlerResponse exceptionHandlerResponse = new ExceptionHandlerResponse();
        exceptionHandlerResponse.setHttpStatus(HttpStatus.FORBIDDEN);
        exceptionHandlerResponse.setTimestamp(
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        exceptionHandlerResponse.setMessage(exc.getMessage());
        exceptionHandlerResponse.setThrowable(exc);
        return new ResponseEntity<>(exceptionHandlerResponse, HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler
    public ResponseEntity<ExceptionHandlerResponse> handleException(TechnicalFailureDuringCheckout exc){
        // when checkout cannot be completed .....
        ExceptionHandlerResponse exceptionHandlerResponse = new ExceptionHandlerResponse();
        exceptionHandlerResponse.setHttpStatus(HttpStatus.FORBIDDEN);
        exceptionHandlerResponse.setTimestamp(
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        exceptionHandlerResponse.setMessage(exc.getMessage());
        exceptionHandlerResponse.setThrowable(exc);
        return new ResponseEntity<>(exceptionHandlerResponse, HttpStatus.FORBIDDEN);
    }
}
