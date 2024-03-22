package com.CodeToDie.TestAlgorithms.service;

import com.CodeToDie.TestAlgorithms.DTO.ProductDTO;
import com.CodeToDie.TestAlgorithms.entity.Product;

import java.util.List;

public interface ProductService {

    public Product createProduct(Product product);

    public List<Product> getProducts();

    public void deleteProductById(Long productId);


    void postProductOke(ProductDTO productDTO);
}
