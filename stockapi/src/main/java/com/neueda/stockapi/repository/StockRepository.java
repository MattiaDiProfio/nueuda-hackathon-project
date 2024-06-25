package com.neueda.stockapi.repository;

import org.springframework.data.repository.CrudRepository;
import com.neueda.stockapi.entity.Stock;
import java.util.Optional;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface StockRepository extends CrudRepository<Stock, String> {

    @Query("""
        SELECT s
        FROM Stock s
        WHERE s.price >= :min AND s.price <= :max
    """)
    List<Stock> findStocksByPriceLimits(Double min, Double max);

    @Query("""
        SELECT s
        FROM Stock s
        WHERE s.ticker = :ticker
    """)
    Optional<Stock> findByTicker(String ticker);

    @Transactional
    @Modifying
    @Query("""
        DELETE
        FROM Stock s
        WHERE s.ticker = :ticker
    """)
    void deleteByTicker(String ticker);
}