package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;



@Entity
@Table(name="Books")

public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private  String Title;
    private float price;

    public String getTitle() {
        return Title;
    }

    public float getPrice() {
        return price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Book() {

    }

    public Book(Long id, String title, float price) {
        this.id = id;
        Title = title;
        this.price = price;
    }


}
