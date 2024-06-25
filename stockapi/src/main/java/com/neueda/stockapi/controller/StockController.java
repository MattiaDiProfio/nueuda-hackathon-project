package com.neueda.stockapi.controller;


import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.neueda.stockapi.entity.FilterRequest;
import com.neueda.stockapi.entity.Stock;
import com.neueda.stockapi.service.StockService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import java.util.List;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@RestController
@RequestMapping("/stock")
public class StockController {
    
    // the @AllArgsConstructor will tell Spring to automatically inject 
    // the StockServiceImpl class into the controller 
    // by carrying out dependency injection, the code is more testable and maintainable
    private StockService stockService;

    @GetMapping("/all")
    public ResponseEntity<List<Stock>> getAllStocks() {
        return new ResponseEntity<>(stockService.getAllStocks(), HttpStatus.OK);
    }

    @GetMapping("/{stockTicker}")
    public ResponseEntity<Stock> getStockByTicker(@PathVariable String stockTicker) {
        return new ResponseEntity<>(stockService.getStockByTicker(stockTicker), HttpStatus.OK);
    }

    // return all stocks with price between min, max specified in request payload
    @GetMapping("/stock/filter")
    public ResponseEntity<List<Stock>> filterStocks(@Valid @RequestBody FilterRequest payload) {
        return new ResponseEntity<>(stockService.filterStocks(payload), HttpStatus.OK);
    }

    @PostMapping("/") 
    public ResponseEntity<Stock> createStock(@Valid @RequestBody Stock payload) {
        return new ResponseEntity<>(stockService.createStock(payload), HttpStatus.CREATED);
    }

    // update the price of a stock given its ticker symbol
    @PutMapping("/{stockTicker}")
    public ResponseEntity<Stock> updateStock(@Valid @RequestBody Stock payload) {
        return new ResponseEntity<>(stockService.updateStock(payload), HttpStatus.CREATED);
    }

    @DeleteMapping("/{stockTicker}") 
    public ResponseEntity<HttpStatus> deleteStock(@PathVariable String stockTicker) {
        stockService.deleteStock(stockTicker);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
