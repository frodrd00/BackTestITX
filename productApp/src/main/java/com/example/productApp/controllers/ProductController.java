package com.example.productApp.controllers;

import com.example.productApp.facade.ProductFacade;
import com.example.productApp.models.ProductDetailModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductFacade productFacade;

    @Autowired
    public ProductController(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    @GetMapping("/{productId}/similar")
    public ResponseEntity<List<ProductDetailModel>> getSimilarProducts(@PathVariable("productId") String productId) {
        List<ProductDetailModel> similarProducts = productFacade.getSimilarProducts(productId);
        if (similarProducts.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(similarProducts);
        }
        return ResponseEntity.ok(similarProducts);
    }
}

