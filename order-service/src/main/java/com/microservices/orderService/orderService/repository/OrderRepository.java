package com.microservices.orderService.orderService.repository;

import com.microservices.orderService.orderService.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
