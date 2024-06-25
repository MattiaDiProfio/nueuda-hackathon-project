package com.neueda.stockapi.exception;

public class StockNotFoundException extends RuntimeException {
    public StockNotFoundException(String stockTicker) {
        super(String.format("The stock with ticker %s does not exist in our records", stockTicker));
    }
}
