package com.cryptoclient.exceptions;

public class WalletWithThatNameAlreadyExistException extends RuntimeException {
    public WalletWithThatNameAlreadyExistException(String message) {
        super(message);
    }
}
