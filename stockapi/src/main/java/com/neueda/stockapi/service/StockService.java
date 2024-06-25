package com.neueda.stockapi.service;

import java.util.List;

import com.neueda.stockapi.entity.Stock;
import com.neueda.stockapi.entity.FilterRequest;

public interface StockService {

    List<Stock> getAllStocks();
    List<Stock> filterStocks(FilterRequest limits);
    Stock getStockByTicker(String stockTicker);
    Stock createStock(Stock stock);
    Stock updateStock(Stock stock);
    Stock deleteStock(String stockTicker);
}
