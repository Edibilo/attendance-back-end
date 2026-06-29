package iki.attendance.management.attendance.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorEntity> notFound(NotFoundException notFoundException, WebRequest request){
        ErrorEntity errorEntity=new ErrorEntity();
        errorEntity.setMessage(notFoundException.getMessage());
        errorEntity.setErrorEntity(request.getDescription(false));
        errorEntity.setLocalDateTime(LocalDateTime.now());
        return ResponseEntity.ok(errorEntity);
    }

    @ExceptionHandler(AlreadyFound.class)
    public ResponseEntity<ErrorEntity> alreadyFound(AlreadyFound alreadyFound,WebRequest request){
        ErrorEntity errorEntity=new ErrorEntity();
        errorEntity.setMessage(alreadyFound.getMessage());
        errorEntity.setLocalDateTime(LocalDateTime.now());
        errorEntity.setErrorEntity(request.getDescription(false));
        return ResponseEntity.ok(errorEntity);
    }
}
