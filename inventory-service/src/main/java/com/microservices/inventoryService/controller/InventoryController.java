package com.microservices.inventoryService.controller;

import com.microservices.inventoryService.dto.InventoryResponse;
import com.microservices.inventoryService.model.Inventory;
import com.microservices.inventoryService.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping("/{sku-code}")
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@PathVariable("sku-code") String skuCode){

        return inventoryService.isInStock(skuCode);

    }
}
