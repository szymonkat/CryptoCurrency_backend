package com.cryptoclient.exceptions;

public class ExchangePortalPriceTooOldException extends RuntimeException {
    public ExchangePortalPriceTooOldException(String message) {
        super(message);
    }
}
