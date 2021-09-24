package com.example.demo.shop.models;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Data
@Entity
public class UserOrders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Autowired
    @OneToMany(cascade = CascadeType.ALL)
    private List<Cart> cart;
    private Date date;
    private BigDecimal cartValue;
    @ManyToOne
    private OrderState orderState;
    @OneToOne
    private UserEntity userEntity;

}
