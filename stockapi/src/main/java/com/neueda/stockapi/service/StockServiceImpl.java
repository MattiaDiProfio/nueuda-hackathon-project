package com.neueda.stockapi.service;

import java.util.List;
import com.neueda.stockapi.exception.StockNotFoundException;
import com.neueda.stockapi.repository.StockRepository;
import com.neueda.stockapi.entity.FilterRequest;
import com.neueda.stockapi.entity.Stock;
import lombok.AllArgsConstructor;
import java.util.Optional;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class StockServiceImpl implements StockService {

    private StockRepository stockRepository;

    public List<Stock> getAllStocks() {
        return (List<Stock>) stockRepository.findAll();
    }

    public Stock getStockByTicker(String stockTicker) {
        return unwrapStock(stockRepository.findByTicker(stockTicker), stockTicker);
    }

    public List<Stock> filterStocks(FilterRequest limits) {
        return (List<Stock>) stockRepository.findStocksByPriceLimits(limits.getMin(), limits.getMax());
    }

    public Stock createStock(Stock stock) {
        return stockRepository.save(stock);
    }

    public Stock updateStock(Stock stock) {
        Stock fetchedStock = getStockByTicker(stock.getTicker());
        fetchedStock.setPrice(stock.getPrice()); // only update the price for now, we can allow to change the stock name later on as well
        fetchedStock.setCompanyName(stock.getCompanyName());
        return stockRepository.save(fetchedStock);
    }

    public void deleteStock(String stockTicker) {
        // check that the stock exists first - if not then an exception will be thrown
        getStockByTicker(stockTicker);
        stockRepository.deleteByTicker(stockTicker);
    }

    /* 
        Some methods from an interface which extends CrudRepository, such as StockRepository, 
        return an object of type Optional in case the entity we are trying to fetch doesnt exist
        given this, we must unwrap the optional to obtain the stock object if it exists, 
        otherwise we through a checked exception
    */
    public static Stock unwrapStock(Optional<Stock> entity, String stockTicker) {
        if (entity.isPresent()) return entity.get();
        else throw new StockNotFoundException(stockTicker);
    }

}
