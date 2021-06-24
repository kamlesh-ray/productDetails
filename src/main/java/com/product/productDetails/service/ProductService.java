package com.product.productDetails.service;

import com.product.productDetails.controller.dto.ProductDto;
import com.product.productDetails.controller.dto.ResponseData;
import com.product.productDetails.controller.dto.UpdateProductDto;
import com.product.productDetails.exception.AppException;
import com.product.productDetails.exception.InternalStandardError;
import com.product.productDetails.model.Product;
import com.product.productDetails.repo.ProductRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class ProductService {
    @Autowired
    private ProductRepo repo;

    public ResponseData addProduct(ProductDto prod) {
        log.debug("add product called");
        Product product = new Product();
        BeanUtils.copyProperties(prod, product);
        product.setProductId(UUID.randomUUID().toString());
        Product savedProduct = repo.save(product);
        String id = savedProduct.getProductId();
        log.info("product saved successfully {}", id);
        return ResponseData
                .builder()
                .message("product saved successfully")
                .data(id)
                .build();
    }

    public ResponseData getAll() {
        return ResponseData.builder()
                .data(repo.findAll())
                .build();
    }

    public ResponseData getProductById(String productId) {
        Optional<Product> optValue = repo.findById(productId);
        if (!optValue.isPresent()) {
            throw new AppException(InternalStandardError.PRODUCT_NOT_EXIST);
        }
        return ResponseData
                .builder()
                .data(optValue.get())
                .build();
    }

    public ResponseData updateProduct(String productId, UpdateProductDto productDto) {
        Optional<Product> optionalProduct = repo.findById(productId);
        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            // up opt
            if (productDto.getProductName() != null) {
                existingProduct.setProductName(productDto.getProductName());
            }
            if (productDto.getPrice() != null) {
                existingProduct.setPrice(productDto.getPrice());
            }
            if (productDto.getQuantity() != 0) {
                existingProduct.setQuantity(productDto.getQuantity());
            }
            return ResponseData
                    .builder()
                    .message("Product Updated Successfully")
                    .data(repo.save(existingProduct))
                    .build();
        }
        throw new AppException(InternalStandardError.PRODUCT_NOT_EXIST);

    }

    public ResponseData removeProduct(String productId) {
        if (!repo.existsById(productId)){
            throw new AppException(InternalStandardError.PRODUCT_NOT_EXIST);
        }
        repo.deleteById(productId);

        return ResponseData
                .builder()
                .message("The Product has been removed with id : " + productId)
                .build();

    }
}
