package com.CodeToDie.TestAlgorithms.controllers;

import com.CodeToDie.TestAlgorithms.DTO.ProductDTO;
import com.CodeToDie.TestAlgorithms.entity.Product;
import com.CodeToDie.TestAlgorithms.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping("/create-product")
    public Product createProduct(@RequestBody Product product){
        return  productService.createProduct(product);

    }
    @GetMapping("/get-products")
    public List<Product> getProducts(){
       return productService.getProducts();
    }
    @DeleteMapping("/delete/{id}")
    public String deleteProductById(@PathVariable("id") Long productId){
        productService.deleteProductById(productId);
        return "Delete Success!";
    }

    @PostMapping("/post-productoke")
    public ResponseEntity<String> postProductOke(@RequestBody ProductDTO productDTO){
       try {
           productService.postProductOke(productDTO);
           return new ResponseEntity<>("Create product success!!!",HttpStatus.OK);
       }catch (Exception e){
           return new ResponseEntity<>("Fail to create product!!",HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

}
