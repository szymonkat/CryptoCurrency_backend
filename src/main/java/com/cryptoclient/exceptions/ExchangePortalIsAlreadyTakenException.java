package com.cryptoclient.exceptions;

public class ExchangePortalIsAlreadyTakenException extends RuntimeException {
    public ExchangePortalIsAlreadyTakenException(String message) {
        super(message);
    }
}
