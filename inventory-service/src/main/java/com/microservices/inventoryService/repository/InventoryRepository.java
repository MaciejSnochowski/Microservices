package com.microservices.inventoryService.repository;

import com.microservices.inventoryService.model.Inventory;
import lombok.Builder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
public interface InventoryRepository extends JpaRepository<Inventory,Long> {
    List<Inventory> findBySkuCodeIn(List<String> skuCode);
}
