package com.nextuple1.miniProject3.repository;

import com.nextuple1.miniProject3.entity.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends MongoRepository<Inventory,String> {
    Optional<Inventory> findById(String productId);
}
