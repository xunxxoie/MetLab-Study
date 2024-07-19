package org.springboot.jpa.data.dao.impl;

import org.springboot.jpa.data.Repository.ProductRepository;
import org.springboot.jpa.data.dao.ProductDAO;
import org.springboot.jpa.data.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class ProductDAOImpl implements ProductDAO {
    private final ProductRepository productRepository;

    @Autowired
    public ProductDAOImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public Product insertProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    @Override
    public Product selectProduct(Long number) {
        Optional<Product> product = productRepository.findById(number);
        Product selectedProduct = product.get();
        return selectedProduct;
    }

    @Override
    public Product updateProductName(Long number, String name) throws Exception {
        Optional<Product> selectedProduct = productRepository.findById(number);

        Product updatedProduct;
        if(selectedProduct.isPresent()){
            Product product = selectedProduct.get();

            product.setName(name);
            product.setUpdatedAt(LocalDateTime.now());

            updatedProduct = productRepository.save(product);
        }else{
            throw new Exception();
        }
        return updatedProduct;
    }

    @Override
    public void deleteProduct(Long number) throws Exception{
        Optional<Product> deletedProduct = productRepository.findById(number);

        if(deletedProduct.isPresent()){
            Product product = deletedProduct.get();
            productRepository.delete(product);
        }else{
            throw new Exception();
        }
    }
}
