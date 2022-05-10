package com.codingclub.springbootdemo.dto;

public class ResponeMessage {
    private String message;

    public ResponeMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
