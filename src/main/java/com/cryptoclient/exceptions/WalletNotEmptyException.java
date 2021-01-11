package com.cryptoclient.exceptions;

public class WalletNotEmptyException extends RuntimeException {
    public WalletNotEmptyException(String message) {
        super(message);
    }
}
