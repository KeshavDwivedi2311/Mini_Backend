package com.nextuple1.miniProject3.service;

import com.nextuple1.miniProject3.entity.Inventory;
import com.nextuple1.miniProject3.entity.Product;
import com.nextuple1.miniProject3.entity.SaleItem;
import com.nextuple1.miniProject3.entity.SaleOrder;
import com.nextuple1.miniProject3.repository.InventoryRepository;
import com.nextuple1.miniProject3.repository.SaleOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleOrderService {
    @Autowired
    private SaleOrderRepository repository;
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private  ProductService productService;
    @Autowired
    private InventoryService inventoryService;
    public SaleOrder addSaleOrder(SaleOrder saleOrder){
//        SaleOrder savedSaleOrder= repository.save(saleOrder);
//        List<SaleItem> saleItems=saleOrder.getSaleItems();
//        for(SaleItem saleItem:saleItems){
//            String productId=saleItem.getProductId();
//            int quantitySold=saleItem.getQuantity();
//            Product product=productService.getProductById(productId);
//            Optional<Inventory> inventory=inventoryRepository.findById(productId);
//            if(inventory!=null)
//            {
//                int quantityAvaliable= inventory.get().getAvailableQuantity();
//                int newQuantity=quantityAvaliable-quantitySold;
//                inventory.get().setAvailableQuantity(newQuantity);
//            }

//        }
        for(SaleItem saleItem:saleOrder.getSaleItems()){
            Optional<Inventory> inventory=inventoryRepository.findById(saleItem.getProductId());
            if (inventory == null || inventory.get().getAvailableQuantity() < saleItem.getQuantity()) {
                throw new RuntimeException("Product with id " + saleItem.getProductId() + " is out of stock.");
            }
        }
        for(SaleItem saleItem:saleOrder.getSaleItems()){
            Optional<Inventory> optionalInventory= inventoryRepository.findById(saleItem.getProductId());
            Inventory inventory =optionalInventory.get();
            inventory.setAvailableQuantity(inventory.getAvailableQuantity()-saleItem.getQuantity());
            inventoryRepository.save(inventory);
        }

//        saleOrder.setTotalPrice();
        return  repository.save(saleOrder);

    }
//    private double getTotalPrice(List<SaleItem> saleItems)
//    {
//        double totalPrice=0.0;
//        for(SaleItem saleItem:saleItems){
//            Optional<Inventory> optionalInventory=inventoryRepository.findById(saleItem.getProductId());
//            Inventory inventory=optionalInventory.get();
//            totalPrice+=(saleItem.getQuantity()* saleItem.getPrice());
//        }
//    }
    public SaleOrder getSaleOrderById(String saleOrderId) {
        Optional<SaleOrder> saleOrder = repository.findById(saleOrderId);
        if (saleOrder.isPresent()) {
            return saleOrder.get();
        } else {
            throw new ResourceNotFoundException("Sale order not found with id " + saleOrderId);
        }
    }
    public List<SaleOrder> getAllSaleOrders()
    {
        return repository.findAll();
    }
    public String deleteSaleOrder(String saleOrderId){
        Optional<SaleOrder> saleOrder=repository.findById(saleOrderId);
        if(saleOrder.isPresent()){
            repository.deleteById(saleOrderId);
            return "Sale Order with id " + saleOrderId + " has been successfully deleted.";
        }
        else
        {
            return "Sale Order not found with id " + saleOrderId;
        }

    }
}
