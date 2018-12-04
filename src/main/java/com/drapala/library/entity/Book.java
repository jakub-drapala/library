package com.drapala.library.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.transaction.Transactional;

@Slf4j
@Data
@Entity
@Transactional
public class Book {

    @Id
    @GeneratedValue
    private long id;


    @Column/*(nullable = false)*/
    private String authorsFirstName;

    @Column
    private String authorsLastName;

    @Column
    private String title;

    @Column
    private int publicationsYear;

    @Column
    private double price;

    @Column
    private int quantityInStock = 0;

    @Column
    private int reserved = 0;



    public Book(String authorsFirstName, String authorsLastName, String title, int publicationsYear, double price) {
        this.authorsFirstName = authorsFirstName;
        this.authorsLastName = authorsLastName;
        this.title = title;
        this.publicationsYear = publicationsYear;
        this.price = price;
    }

    protected Book() {
    }

/*    public void adjustStock (int quantity) {
        int newQuantity = this.quantityInStock + quantity;

        log.info("New quantity {}", newQuantity);

        if (newQuantity >= 0) {
            this.quantityInStock = newQuantity;
            log.info("Quantity in stock {}", this.quantityInStock);
        }
    }*/

}



