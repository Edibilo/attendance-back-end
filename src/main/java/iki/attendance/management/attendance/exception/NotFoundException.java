package iki.attendance.management.attendance.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}
