package com.product.productDetails.repo;

import com.product.productDetails.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepo extends CrudRepository<Product, String> {
}
