package com.project.planner.common.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import net.minidev.json.annotate.JsonIgnore;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthenticationResponse {
    private String email;
    private String password;
    private String token;
    private String cookieToken;

    public AuthenticationResponse() {
    }

    public AuthenticationResponse(String email, String password, String token, String cookieToken) {
        this.email = email;
        this.password = password;
        this.token = token;
        this.cookieToken = cookieToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCookieToken() {
        return cookieToken;
    }

    public void setCookieToken(String cookieToken) {
        this.cookieToken = cookieToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthenticationResponse that = (AuthenticationResponse) o;
        return Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(token, that.token) && Objects.equals(cookieToken, that.cookieToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password, token, cookieToken);
    }

    @Override
    public String toString() {
        return "AuthenticationResponse{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                ", cookieToken='" + cookieToken + '\'' +
                '}';
    }

    public static AuthenticationResponseBuilder builder() {
        return new AuthenticationResponseBuilder();
    }

    public static class AuthenticationResponseBuilder {
        private String email;
        private String password;
        private String token;
        private String cookieToken;

        public AuthenticationResponse build() {
            return new AuthenticationResponse(this.email, this.password, this.token, this.cookieToken);
        }

        public AuthenticationResponseBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public AuthenticationResponseBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public AuthenticationResponseBuilder setToken(String token) {
            this.token = token;
            return this;
        }

        public AuthenticationResponseBuilder setCookieToken(String cookieToken) {
            this.cookieToken = cookieToken;
            return this;
        }
    }
}
