package org.springboot.jpa.controller;

import org.springboot.jpa.data.dto.ChangeProductDto;
import org.springboot.jpa.data.dto.ProductDto;
import org.springboot.jpa.data.dto.ProductResponseDto;
import org.springboot.jpa.data.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<ProductResponseDto> getProduct(Long number){
        ProductResponseDto productResponseDto = productService.getProduct(number);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productResponseDto);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductDto productDto) {
        ProductResponseDto productResponseDto = productService.saveProduct(productDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productResponseDto);
    }

    @PutMapping
    public ResponseEntity<ProductResponseDto> changeProductName(@RequestBody ChangeProductDto changeProductDto) throws Exception{
        ProductResponseDto productResponseDto = productService.changeProductName(changeProductDto.getNumber(), changeProductDto.getName());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productResponseDto);

    }

    @DeleteMapping
    public ResponseEntity<String> deleteProduct(@RequestParam("number") Long number) throws Exception{
        productService.deleteProduct(number);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Delete success");
    }

}
