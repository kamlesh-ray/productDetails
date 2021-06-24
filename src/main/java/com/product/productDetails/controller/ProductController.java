package com.product.productDetails.controller;

import com.product.productDetails.controller.dto.ProductDto;
import com.product.productDetails.controller.dto.ResponseData;
import com.product.productDetails.controller.dto.UpdateProductDto;
import com.product.productDetails.model.Product;
import com.product.productDetails.repo.ProductRepo;
import com.product.productDetails.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
@Validated
public class ProductController {

    @Autowired
    private ProductService prodsrv;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseData post(@Valid @RequestBody ProductDto prod) {
        return prodsrv.addProduct(prod);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseData getListOfProduct() {
        return prodsrv.getAll();
    }

    @GetMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseData getProductById(@PathVariable String productId) {
        return prodsrv.getProductById(productId);
    }

    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseData delete(@PathVariable String productId) {
        return prodsrv.removeProduct(productId);
    }

    @PutMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseData put(@PathVariable String productId, @RequestBody UpdateProductDto productDto) {
        return prodsrv.updateProduct(productId, productDto);
    }

}
