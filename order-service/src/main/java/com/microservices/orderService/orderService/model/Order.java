package com.microservices.orderService.orderService.model;

import com.fasterxml.jackson.databind.util.ClassUtil;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="t_orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String orderNumber;
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderLineItems> orderLineItems;
}
