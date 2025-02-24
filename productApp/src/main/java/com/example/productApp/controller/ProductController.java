package com.example.productApp.controller;

import com.example.productApp.model.ProductDetail;
import com.example.productApp.service.ProductDetailService;
import com.example.productApp.service.ProductSimilarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductSimilarService productSimilarService;

    @Autowired
    public ProductController(ProductSimilarService productSimilarService) {
        this.productSimilarService = productSimilarService;
    }

    @GetMapping("/{productId}/similar")
    public ResponseEntity<List<ProductDetail>> getProductSimilar(@PathVariable("productId") String productId) {
        List<ProductDetail> similarProducts = productSimilarService.getSimilarProducts(productId);
        return ResponseEntity.ok(similarProducts);
    }
}
