package com.birkan.rig.exception;

public class NoStockLeftException extends RuntimeException {

    public NoStockLeftException(String message) {
        super(message);
    }
}
