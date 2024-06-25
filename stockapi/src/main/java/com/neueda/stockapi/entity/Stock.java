package com.neueda.stockapi.entity;


import lombok.Data; 
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "stock ticker symbol cannot be blank")
    @Column(name = "ticker", nullable = false)
    private String ticker; // since ticker symbols are unique, we can use them as the primary key in our Stocks table

    @Min(value = (long) 0.01, message = "price cannot be less than $0.01")
    @Column(name = "price", nullable = false)
    private Double price;

    @NotBlank(message = "company name cannot be blank")
    @Column(name = "companyName", nullable = false)
    private String companyName;

}
