package com.jpa.domain.item;

public class NotEnoughStockException extends RuntimeException {
    public NotEnoughStockException(String msg) {
        super(msg);
    }
}
