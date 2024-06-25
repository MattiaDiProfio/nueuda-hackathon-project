package com.neueda.stockapi.entity;


import lombok.Data; 
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

/*
    the lombok dependency automatically generates a variety of 
    getters, setters, and constructors for the annotated class

    the @Data annotation merges the @Getter, @Setter, @AllArgsConstructor 
    annotations, keeping the code cleaner

    the jakarta dependecy allows spring boot to map classes to tables in a DB

*/

@Data 
@NoArgsConstructor
@Entity
@Table(name = "stocks")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long ID;

    @Column(name = "ticker", unique = true, nullable = false)
    private String ticker;

    @Min(value = (long) 0.01, message = "price cannot be less than $0.01")
    @Column(name = "price", nullable = false)
    private Double price;

}
