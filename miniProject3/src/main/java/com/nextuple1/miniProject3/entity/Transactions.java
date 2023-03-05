package com.nextuple1.miniProject3.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection="/findAllTransactions")
public class Transactions {

    private String type;
    private String productId;
    private String productName;
    private int quantity;
    private List<SaleItem> saleItems;
    private double price;

    public Transactions(String type, String productId, String productName, int quantity, List<SaleItem> saleItems, double price) {
        this.type = type;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.saleItems = saleItems;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<SaleItem> getSaleItems() {
        return saleItems;
    }

    public void setSaleItems(List<SaleItem> saleItems) {
        this.saleItems = saleItems;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
