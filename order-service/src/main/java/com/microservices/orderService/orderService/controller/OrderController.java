package com.microservices.orderService.orderService.controller;

import com.microservices.orderService.orderService.dto.OrderRequest;
import com.microservices.orderService.orderService.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;
@PostMapping
@ResponseStatus(HttpStatus.CREATED)
    public void placeOrder(@RequestBody OrderRequest orderRequest){
    orderService.placeOrder(orderRequest);
}
}
