package com.CodeToDie.TestAlgorithms.service.serviceimpl;

import com.CodeToDie.TestAlgorithms.DTO.ProductDTO;
import com.CodeToDie.TestAlgorithms.entity.ImageProduct;
import com.CodeToDie.TestAlgorithms.entity.Product;
import com.CodeToDie.TestAlgorithms.repository.ImageRepository;
import com.CodeToDie.TestAlgorithms.repository.ProductRepository;
import com.CodeToDie.TestAlgorithms.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Override
    public Product createProduct(Product product) {
       return productRepository.save(product);
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public void deleteProductById(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public void postProductOke(ProductDTO productDTO) {
        Product product = new Product();
        product.setProductName(productDTO.getProductName());
        product.setProductPrice(productDTO.getProductPrice());
        product.setUnit(productDTO.getUnit());
        product.setProductSize(productDTO.getProductSize());
        product.setDiscount(productDTO.getDiscount());

        productRepository.save(product);
        Long productID = product.getProductId();
        Date currentDate = new Date();

        List<ImageProduct> images = productDTO.getImages().stream().map
                (imageDTO -> {
                    ImageProduct imageProduct = new ImageProduct();
                    imageProduct.setPathImage(imageDTO.getPathImage());
                    imageProduct.setName(imageDTO.getName());
                    imageProduct.setCreateDate(currentDate);
                    imageProduct.setProductId(productID);
                    return imageProduct;
                }).collect(Collectors.toList());
        imageRepository.saveAll(images);
    }


}
