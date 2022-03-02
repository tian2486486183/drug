package com.harlon.drugmanagement.exception.customize;

public class UserNotLoginException extends RuntimeException{

    public UserNotLoginException() {super();}

    public UserNotLoginException(String message) {
        super(message);
    }
}
