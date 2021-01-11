package com.cryptoclient.exceptions;

public class NotEnoughUsdInTheWalletException extends RuntimeException {
    public NotEnoughUsdInTheWalletException(String message) {
        super(message);
    }
}
