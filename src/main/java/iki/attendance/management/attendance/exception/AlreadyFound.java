package iki.attendance.management.attendance.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class AlreadyFound extends RuntimeException{
    public AlreadyFound(String message) {
        super(message);
    }
}
