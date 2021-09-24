package com.example.demo.shop.models;

import lombok.Data;
import org.springframework.web.context.annotation.SessionScope;

import javax.persistence.*;

@Entity
@Data
@SessionScope
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Product product;
    private long quantity;

}
