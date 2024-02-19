package com.project.planner.exceptions;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class EntityInstanceDoesNotExist extends ResponseStatusException {
    public EntityInstanceDoesNotExist(HttpStatusCode status) {
        super(status);
    }

    public EntityInstanceDoesNotExist(HttpStatusCode status, String reason) {
        super(status, reason);
    }

    public EntityInstanceDoesNotExist(int rawStatusCode, String reason, Throwable cause) {
        super(rawStatusCode, reason, cause);
    }

    public EntityInstanceDoesNotExist(HttpStatusCode status, String reason, Throwable cause) {
        super(status, reason, cause);
    }

    protected EntityInstanceDoesNotExist(HttpStatusCode status, String reason, Throwable cause, String messageDetailCode, Object[] messageDetailArguments) {
        super(status, reason, cause, messageDetailCode, messageDetailArguments);
    }
}
