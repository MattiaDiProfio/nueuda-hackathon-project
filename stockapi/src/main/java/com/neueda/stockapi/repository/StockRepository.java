package com.neueda.stockapi.repository;

import org.springframework.data.repository.CrudRepository;
import com.neueda.stockapi.entity.Stock;

public interface StockRepository extends CrudRepository<Stock, String> {

}