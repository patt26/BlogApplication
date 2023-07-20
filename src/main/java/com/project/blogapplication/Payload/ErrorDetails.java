package com.project.blogapplication.Payload;

import java.util.Date;

public class ErrorDetails {
    private Date timestamps;
    private String message;
    private String details;
    private int statusCode;

    public ErrorDetails(Date timestamps, String message, String details, int code) {
        this.timestamps = timestamps;
        this.message = message;
        this.details = details;
        this.statusCode = code;
    }

    public Date getTimestamps() {
        return timestamps;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
