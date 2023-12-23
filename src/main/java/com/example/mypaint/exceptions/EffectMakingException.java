package com.example.mypaint.exceptions;

public class EffectMakingException extends RuntimeException{
    public EffectMakingException() {
    }

    public EffectMakingException(String message) {
        super(message);
    }

    public EffectMakingException(String message, Throwable cause) {
        super(message, cause);
    }

    public EffectMakingException(Throwable cause) {
        super(cause);
    }

    public EffectMakingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
