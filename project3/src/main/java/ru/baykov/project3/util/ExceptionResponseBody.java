package ru.baykov.project3.util;

import java.time.LocalDateTime;

public class ExceptionResponseBody {
    private String message;
    private LocalDateTime createdAt;

    public ExceptionResponseBody(String message, LocalDateTime createdAt) {
        this.message = message;
        this.createdAt = createdAt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
