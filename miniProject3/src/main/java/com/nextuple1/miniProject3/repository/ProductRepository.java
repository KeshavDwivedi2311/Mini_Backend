package com.nextuple1.miniProject3.repository;

import com.nextuple1.miniProject3.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ProductRepository extends MongoRepository<Product,String> {
}
