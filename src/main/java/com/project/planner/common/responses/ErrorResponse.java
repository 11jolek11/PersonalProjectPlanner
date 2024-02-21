package com.project.planner.common.responses;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse  {
    private final int statusCode;
    private final String message;
    private String stackTrace;

    public ErrorResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public ErrorResponse(int statusCode, String message, String stackTrace) {
        this.statusCode = statusCode;
        this.message = message;
        this.stackTrace = stackTrace;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorResponse that = (ErrorResponse) o;
        return statusCode == that.statusCode && Objects.equals(message, that.message) && Objects.equals(stackTrace, that.stackTrace);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statusCode, message, stackTrace);
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "status_code=" + statusCode +
                ", message='" + message + '\'' +
                ", stackTrace='" + stackTrace + '\'' +
                '}';
    }

    public static ErrorResponseBuilder builder() {
        return new ErrorResponseBuilder();
    }

    public static class ErrorResponseBuilder {

        private int statusCode;
        private String message;
        private String stackTrace;
        public ErrorResponseBuilder() {
        }

        public ErrorResponseBuilder setStatusCode(int statusCode) {
            this.statusCode = statusCode;
            return this;
        }
        public ErrorResponseBuilder setMessage(String message) {
            this.message = message;
            return this;
        }

        public ErrorResponseBuilder setStackTrace(String stackTrace) {
            this.stackTrace = stackTrace;
            return this;
        }

        public ErrorResponse build() {
            return new ErrorResponse(this.statusCode, this.message, this.stackTrace);
        }


    }



}
