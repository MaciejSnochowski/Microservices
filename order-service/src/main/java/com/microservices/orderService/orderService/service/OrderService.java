package com.microservices.orderService.orderService.service;

import com.microservices.orderService.orderService.dto.OrderLineItemsDto;
import com.microservices.orderService.orderService.dto.OrderRequest;
import com.microservices.orderService.orderService.model.Order;
import com.microservices.orderService.orderService.model.OrderLineItems;
import com.microservices.orderService.orderService.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;


    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> list=orderRequest.getOrderLineItemsDto()
                .stream()
                .map(this::mapToOrderLine)
                .toList();
        orderRepository.save(order);

    }

    private OrderLineItems mapToOrderLine(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems(); // TODO: replace with builder
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return  orderLineItems;
    }

    }
