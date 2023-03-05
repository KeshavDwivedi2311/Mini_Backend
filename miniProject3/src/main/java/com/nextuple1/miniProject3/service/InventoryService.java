package com.nextuple1.miniProject3.service;

import com.nextuple1.miniProject3.entity.Inventory;
import com.nextuple1.miniProject3.entity.Product;
import com.nextuple1.miniProject3.entity.Transactions;
import com.nextuple1.miniProject3.repository.InventoryRepository;
import com.nextuple1.miniProject3.repository.ProductRepository;
import com.nextuple1.miniProject3.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.core.support.TransactionalRepositoryFactoryBeanSupport;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    public Inventory addInventory(Inventory inventory)
    {
        //check if the product exists
        Product product =  productRepository.findById(inventory.getProductId()).orElseThrow(()->new RuntimeException("Product not found"));
        //check if there's an inventory record for this product
        Optional<Inventory> optionalInventory= inventoryRepository.findById(inventory.getProductId());

        if(optionalInventory.isPresent())
        {
            Inventory existingInventory= optionalInventory.get();
            existingInventory.setAvailableQuantity(existingInventory.getAvailableQuantity()+inventory.getAvailableQuantity());
            existingInventory.setProductName(product.getName());
//            Transactions transaction = new Transactions();
//            transaction.setType("Purchase");
//            transaction.setProductId(inventory.getProductId());
////            transaction.setProductId(inventory.getProduct().getId());
//            transaction.setQuantity(inventory.getAvailableQuantity());
////            transaction.setQuantity(quantity);
//            transaction.setSaleItems(null);
////            transaction.setPrice(quantity * product.getPrice()inventory.getProductId());
//            //bug in this line
//            S save = transactionRepository.save(transactionRepository);
            return inventoryRepository.save(existingInventory);
        }
        else
        {
            inventory.setProductName(product.getName());
            return inventoryRepository.save(inventory);
        }


    }
    public List<Inventory> getAllInventory(){
        return inventoryRepository.findAll();
    }
}
