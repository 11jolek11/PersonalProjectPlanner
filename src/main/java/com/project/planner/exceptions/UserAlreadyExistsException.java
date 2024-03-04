package com.project.planner.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
//import org.springframework.http.HttpStatusCode;

public class UserAlreadyExistsException extends ResponseStatusException {
    public UserAlreadyExistsException(HttpStatus status) {
        super(status);
    }

    public UserAlreadyExistsException(HttpStatus status, String reason) {
        super(status, reason);
    }

    public UserAlreadyExistsException(int rawStatusCode, String reason, Throwable cause) {
        super(rawStatusCode, reason, cause);
    }

    public UserAlreadyExistsException(HttpStatus status, String reason, Throwable cause) {
        super(status, reason, cause);
    }

    protected UserAlreadyExistsException(HttpStatus status, String reason, Throwable cause, String messageDetailCode, Object[] messageDetailArguments) {
        super(status, reason, cause, messageDetailCode, messageDetailArguments);
    }
}
