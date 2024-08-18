package com.project.planner.exceptions;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class ResourceDoesNotExist extends ResponseStatusException {
    public ResourceDoesNotExist(HttpStatusCode status) {
        super(status);
    }

    public ResourceDoesNotExist(HttpStatusCode status, String reason) {
        super(status, reason);
    }

    public ResourceDoesNotExist(int rawStatusCode, String reason, Throwable cause) {
        super(rawStatusCode, reason, cause);
    }

    public ResourceDoesNotExist(HttpStatusCode status, String reason, Throwable cause) {
        super(status, reason, cause);
    }

    protected ResourceDoesNotExist(HttpStatusCode status, String reason, Throwable cause, String messageDetailCode, Object[] messageDetailArguments) {
        super(status, reason, cause, messageDetailCode, messageDetailArguments);
    }
}
