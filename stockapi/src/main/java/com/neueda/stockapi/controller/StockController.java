package com.neueda.stockapi.controller;


import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.neueda.stockapi.exception.ErrorResponse;

import com.neueda.stockapi.entity.FilterRequest;
import com.neueda.stockapi.entity.Stock;
import com.neueda.stockapi.service.StockService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
    @GetMapping("/filter")
    public ResponseEntity<Object> filterStocks(@Valid @RequestBody FilterRequest payload, BindingResult result) {

        if (payload.getMin() > payload.getMax()) result.rejectValue("min", "", "Invalid range provided. Ensure min < max");

        // the messages thrown in the FilterRequest class will be caught and formatted here
        ResponseEntity<Object> error = handleInvalidPayload(result);
        if (error != null) return error;

        return new ResponseEntity<>(stockService.filterStocks(payload), HttpStatus.OK);
    }

    @PostMapping 
    public ResponseEntity<Object> createStock(@Valid @RequestBody Stock payload, BindingResult result) {
        ResponseEntity<Object> error = handleInvalidPayload(result);
        return error == null ? new ResponseEntity<>(stockService.createStock(payload), HttpStatus.CREATED) : error;
    }

    // update the price of a stock given its ticker symbol
    @PutMapping
    public ResponseEntity<Object> updateStock(@Valid @RequestBody Stock payload, BindingResult result) {
        ResponseEntity<Object> error = handleInvalidPayload(result);
        return error == null ? new ResponseEntity<>(stockService.updateStock(payload), HttpStatus.CREATED) : error;
    }

    private ResponseEntity<Object> handleInvalidPayload(BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = new ArrayList<>();
            result.getAllErrors().forEach((error) -> errors.add(error.getDefaultMessage()));
            ErrorResponse error = new ErrorResponse(errors);
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    @DeleteMapping("/{stockTicker}") 
    public ResponseEntity<Object> deleteStock(@PathVariable String stockTicker) {
        return new ResponseEntity<>(stockService.deleteStock(stockTicker), HttpStatus.NO_CONTENT);
    }

}
