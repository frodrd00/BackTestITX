package com.example.productApp.facade;

import com.example.productApp.models.ProductDetailModel;
import com.example.productApp.services.ProductSimilarService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductFacade {

    private final ProductSimilarService productSimilarService;

    public ProductFacade(ProductSimilarService productSimilarService) {
        this .productSimilarService = productSimilarService;
    }

    public List<ProductDetailModel> getSimilarProducts(String productId) {
        return productSimilarService.getSimilarProducts(productId);
    }
}
