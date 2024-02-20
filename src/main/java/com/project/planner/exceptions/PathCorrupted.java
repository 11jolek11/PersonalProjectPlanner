package com.project.planner.exceptions;

public class PathCorrupted extends StorageException{
    // TODO(11jolek11): Should result in HTTP 500 Error
    public PathCorrupted(String message) {
        super(message);
    }

    public PathCorrupted(String message, Throwable throwable) {
        super(message, throwable);
    }
}
