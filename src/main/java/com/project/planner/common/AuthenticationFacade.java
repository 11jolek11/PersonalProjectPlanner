package com.project.planner.common;


import org.springframework.security.core.Authentication;

public interface AuthenticationFacade {
    // TODO(11jolek11): Add getEmail or getName method?
    public Authentication getAuthentication();
}

