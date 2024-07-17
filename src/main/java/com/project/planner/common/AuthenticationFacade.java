package com.project.planner.common;


import org.springframework.security.core.Authentication;

public interface AuthenticationFacade {
    public Authentication getAuthentication();
}

