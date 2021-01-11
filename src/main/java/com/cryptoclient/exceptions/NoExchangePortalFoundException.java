package com.cryptoclient.exceptions;

public class NoExchangePortalFoundException extends RuntimeException {
    public NoExchangePortalFoundException(String message) {
        super(message);
    }
}
