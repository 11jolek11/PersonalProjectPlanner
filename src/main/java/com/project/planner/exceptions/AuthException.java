package com.project.planner.exceptions;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class AuthException extends ResponseStatusException {
    public AuthException(HttpStatusCode status) {
        super(status);
    }

    public AuthException(HttpStatusCode status, String reason) {
        super(status, reason);
    }

    public AuthException(int rawStatusCode, String reason, Throwable cause) {
        super(rawStatusCode, reason, cause);
    }

    public AuthException(HttpStatusCode status, String reason, Throwable cause) {
        super(status, reason, cause);
    }

    protected AuthException(HttpStatusCode status, String reason, Throwable cause, String messageDetailCode, Object[] messageDetailArguments) {
        super(status, reason, cause, messageDetailCode, messageDetailArguments);
    }
}
