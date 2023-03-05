package com.nextuple1.miniProject3.controller;

import com.nextuple1.miniProject3.entity.Product;
import com.nextuple1.miniProject3.repository.ProductRepository;
import com.nextuple1.miniProject3.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
//    @Autowired
//    private ProductService service;
//    @Autowired
//    private ProductRepository repository;
//    @PostMapping("/addProduct")
////    public ResponseEntity<Product> addProduct(@RequestBody Product product){
////        Product createdProduct = service.addProduct(product);
////        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
////    }
//    public String addProduct(@RequestBody Product product){
//        repository.save(product);
//        return "Product added";
//    }
//    @GetMapping("/findAllProducts")
//    public List<Product> getProducts(){
//        return repository.findAll();
//    }
    @Autowired
    private ProductService service;
    @PostMapping("/addProduct")
    public String addProduct(@RequestBody Product product){
        service.createProduct(product);
        return "Product "+ product.getId() + " Added Successfully";
    }

    @GetMapping("/findAllProducts")
    public List<Product> getProducts(){
        return service.getProducts();
    }


    @PutMapping("updateProduct/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable ("productId") String productId, @RequestBody Product updatedProduct){
        Product product= service.updateProduct(productId,updatedProduct);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable("productId") String productId){
          String message= service.deleteProduct(productId);
          return new ResponseEntity<>(message,HttpStatus.OK);
    }
    @GetMapping("/findProductByProductId/{productId}")
    public ResponseEntity<Product>getProductByID(@PathVariable("productId") String productId){
        Product product= service.getProductById(productId);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }
}
