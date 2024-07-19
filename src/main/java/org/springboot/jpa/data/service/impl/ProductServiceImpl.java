package org.springboot.jpa.data.service.impl;

import org.springboot.jpa.data.dao.ProductDAO;
import org.springboot.jpa.data.dto.ProductDto;
import org.springboot.jpa.data.dto.ProductResponseDto;
import org.springboot.jpa.data.entity.Product;
import org.springboot.jpa.data.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;

    @Autowired
    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public ProductResponseDto getProduct(Long number) {
        Product product = productDAO.selectProduct(number);

        ProductResponseDto prd = new ProductResponseDto();
        prd.setName(product.getName());
        prd.setNumber(product.getNumber());
        prd.setPrice(product.getPrice());
        prd.setStock(product.getStock());

        return prd;
    }

    @Override
    public ProductResponseDto saveProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setStock(productDto.getStock());
        product.setPrice(productDto.getPrice());
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        Product savedProduct = productDAO.insertProduct(product);

        ProductResponseDto prd = new ProductResponseDto();
        prd.setName(product.getName());
        prd.setNumber(product.getNumber());
        prd.setPrice(product.getPrice());
        prd.setStock(product.getStock());

        return prd;
    }

    @Override
    public ProductResponseDto changeProductName(Long number, String name) throws Exception {
        Product changedProduct = productDAO.updateProductName(number, name);

        ProductResponseDto prd = new ProductResponseDto();
        prd.setName(changedProduct.getName());
        prd.setNumber(changedProduct.getNumber());
        prd.setPrice(changedProduct.getPrice());
        prd.setStock(changedProduct.getStock());

        return prd;
    }

    @Override
    public void deleteProduct(Long number) throws Exception {
        productDAO.deleteProduct(number);
    }
}
