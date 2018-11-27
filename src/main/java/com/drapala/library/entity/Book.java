package com.drapala.library.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
    String name;

    public Book(String name) {
        this.name = name;
    }

    protected Book() {
    }
}
