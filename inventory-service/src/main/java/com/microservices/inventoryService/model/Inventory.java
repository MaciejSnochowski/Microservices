package com.microservices.inventoryService.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "t_inventory")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {

    @Id
    @SequenceGenerator(
            name="inventory_sequence",
            sequenceName="inventory_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "inventory-sequence"
   )
    private Long id;
    private String skuCode;
    private Integer quantity;

}
