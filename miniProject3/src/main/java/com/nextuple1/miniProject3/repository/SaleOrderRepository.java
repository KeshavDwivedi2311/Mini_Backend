package com.nextuple1.miniProject3.repository;

import com.nextuple1.miniProject3.entity.SaleOrder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleOrderRepository extends MongoRepository<SaleOrder,String> {
}
