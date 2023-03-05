package com.nextuple1.miniProject3.service;

import com.nextuple1.miniProject3.entity.Product;
import com.nextuple1.miniProject3.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public Product createProduct(Product product){
        return repository.save(product);
    }
    public List<Product> getProducts(){
        return repository.findAll();
    }
    public Product updateProduct(String productId,Product updatedProduct){
        Optional<Product> product= repository.findById(productId);
        if(product.isPresent()){
            updatedProduct.setId(productId);
            return repository.save(updatedProduct);
        }
        else {
            throw new ResourceNotFoundException("Product Not Found with id " + productId);
        }
    }
    public String deleteProduct(String productId){
        Optional<Product> product=repository.findById(productId);
        if(product.isPresent()){
            repository.deleteById(productId);
            return "Product with id " + productId + " has been successfully deleted.";
        }
        else
        {
            return "Product not found with id " + productId;
        }

    }
    public Product getProductById(String productId){
        Optional<Product> product = repository.findById(productId);
        if(product.isPresent()){
            return product.get();
        }
        else{
            throw new ResourceNotFoundException("Product not found with id " + productId);
        }
    }


}
