package iki.attendance.management.attendance.exception;

import java.time.LocalDateTime;

public class ErrorEntity {
    private String message;
    private String errorEntity;
    private LocalDateTime localDateTime;

    public ErrorEntity(){

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorEntity() {
        return errorEntity;
    }

    public void setErrorEntity(String errorEntity) {
        this.errorEntity = errorEntity;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public ErrorEntity(String message, String errorEntity, LocalDateTime localDateTime) {
        this.message = message;
        this.errorEntity = errorEntity;
        this.localDateTime = localDateTime;
    }
}
