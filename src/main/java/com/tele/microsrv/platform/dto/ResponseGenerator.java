package com.tele.microsrv.platform.dto;

import com.tele.microsrv.platform.security.JwtAuthenticationResponse;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;


public class ResponseGenerator  {
    private String message;
    private String error;
    private long timestamp = new Date().getTime();
    private String status;
    private String path;
    private String token;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

    @Override
    public String toString() {
        return "ResponseGenerator{" +
                "message='" + message + '\'' +
                ", error='" + error + '\'' +
                ", timestamp=" + timestamp +
                ", status='" + status + '\'' +
                ", path='" + path + '\'' +
                ", token='" + token + '\'' +
                '}';
    }


}
