package com.microservices.orderService.orderService.controller;

import com.microservices.orderService.orderService.dto.OrderRequest;
import com.microservices.orderService.orderService.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/order")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;
@PostMapping
@ResponseStatus(HttpStatus.CREATED)
@CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
@TimeLimiter(name="inventory")
public CompletableFuture<String> placeOrder(@RequestBody OrderRequest orderRequest){
    return CompletableFuture.supplyAsync(()->orderService.placeOrder(orderRequest));

    }
    public CompletableFuture<String> fallbackMethod(OrderRequest orderRequest, RuntimeException exception  ){

        return CompletableFuture.supplyAsync(()->"FallbackMethod appears, something went wrong");
    }
}
