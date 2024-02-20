package com.project.planner.exceptions;

public class LocalFileNotFoundException extends StorageException{
    public LocalFileNotFoundException(String message) {
        super(message);
    }

    public LocalFileNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
