package com.example.productApp.services;

import com.example.productApp.adapters.ProductDetailAdapter;
import com.example.productApp.adapters.SimilarProductsAdapter;
import com.example.productApp.models.ProductDetailModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductSimilarService {

    private final ProductDetailAdapter productDetailAdapter;
    private final SimilarProductsAdapter similarProductsAdapter;

    @Autowired
    public ProductSimilarService(ProductDetailAdapter productDetailAdapter,
                          SimilarProductsAdapter similarProductsAdapter) {
        this.productDetailAdapter = productDetailAdapter;
        this.similarProductsAdapter = similarProductsAdapter;
    }

    public List<ProductDetailModel> getSimilarProducts(String productId) {
        List<String> similarIds = similarProductsAdapter.fetchSimilarProductIds(productId);
        List<ProductDetailModel> similarProducts = new ArrayList<>();
        for (String id : similarIds) {
            similarProducts.add(productDetailAdapter.fetchProductDetail(id));
        }
        return similarProducts;
    }
}
