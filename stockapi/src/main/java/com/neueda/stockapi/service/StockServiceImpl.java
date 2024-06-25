package com.neueda.stockapi.service;

import java.util.List;
import com.neueda.stockapi.repository.StockRepository;
import com.neueda.stockapi.entity.FilterRequest;
import com.neueda.stockapi.entity.Stock;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class StockServiceImpl implements StockService {

    private StockRepository stockRepository;

    public List<Stock> getAllStocks() {
        return null;
    }

    public Stock getStockByTicker(String stockTicker) {
        return null;
    }

    public List<Stock> filterStocks(FilterRequest limits) {
        return null;
    }

    public Stock createStock(Stock stock) {
        return null;
    }

    public Stock updateStock(Stock stock) {
        return null;
    }

    public void deleteStock(String stockTicker) {

    }

}
