package com.nextuple1.miniProject3.controller;

import com.nextuple1.miniProject3.entity.Product;
import com.nextuple1.miniProject3.entity.SaleOrder;
import com.nextuple1.miniProject3.service.SaleOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SaleOrderController {
    @Autowired
    private SaleOrderService service;
    @PostMapping("/saleOrder")
    public String addSaleOrder(@RequestBody SaleOrder saleOrder){
        service.addSaleOrder(saleOrder);
        return "Sale Order "+ saleOrder.getSaleOrderId()+ " Added Successfully";
    }
    @DeleteMapping("/deleteSaleOrder/{saleOrderId}")
    public ResponseEntity<String> deleteSaleOrder(@PathVariable("saleOrderId") String saleOrderId){
        String message=service.deleteSaleOrder(saleOrderId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @GetMapping("/findSaleOrderById/{saleOrderId}")
    public ResponseEntity<SaleOrder> getSaleOrderById(@PathVariable("saleOrderId") String saleOrderId) {
        SaleOrder saleOrder = service.getSaleOrderById(saleOrderId);
        return new ResponseEntity<>(saleOrder, HttpStatus.OK);
    }
    @GetMapping("/findAllSaleOrders")
    public ResponseEntity<List<SaleOrder>> getAllSaleOrders()
    {
        List<SaleOrder> saleOrders= service.getAllSaleOrders();
        return new ResponseEntity<>(saleOrders,HttpStatus.OK);
    }

}
