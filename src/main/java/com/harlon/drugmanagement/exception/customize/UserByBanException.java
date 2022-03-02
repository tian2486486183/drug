package com.harlon.drugmanagement.exception.customize;

public class UserByBanException extends RuntimeException{

    public UserByBanException() {
        super();
    }

    public UserByBanException(String message) {
        super(message);
    }
}
