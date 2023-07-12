package com.example.altTab.exception;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class BaseException extends RuntimeException {
    private int status;
    private String message;
    private Date timestamp;

    public BaseException(int status, String message) {
        super(message + "with status code " + status);
        this.status = status;
        this.message = message;
        this.timestamp = new Date();
    }
}
