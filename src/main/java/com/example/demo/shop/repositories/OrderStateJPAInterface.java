package com.example.demo.shop.repositories;

import com.example.demo.shop.models.OrderState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderStateJPAInterface extends JpaRepository<OrderState, Long> {

    @Query("select os from OrderState os WHERE os.stateName = ?1")
    OrderState findByName(String name);
}
