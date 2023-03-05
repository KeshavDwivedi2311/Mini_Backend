package com.nextuple1.miniProject3.controller;

import com.nextuple1.miniProject3.entity.Inventory;
import com.nextuple1.miniProject3.entity.Product;
import com.nextuple1.miniProject3.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;
    @PostMapping("addInventory")
    public String addInventory(@RequestBody Inventory inventory){
        inventoryService.addInventory(inventory);
        return "Inventory Added Successfully";
    }
    @GetMapping("/findAllInventories")
    public List<Inventory> getAllInventory(){
        return inventoryService.getAllInventory();

    }

}
