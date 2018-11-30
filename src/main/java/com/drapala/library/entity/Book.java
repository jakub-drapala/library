package com.drapala.library.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
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



    public Book(String authorsFirstName, String authorsLastName, String title, int publicationsYear, double price) {
        this.authorsFirstName = authorsFirstName;
        this.authorsLastName = authorsLastName;
        this.title = title;
        this.publicationsYear = publicationsYear;
        this.price = price;
    }

    protected Book() {
    }
}
