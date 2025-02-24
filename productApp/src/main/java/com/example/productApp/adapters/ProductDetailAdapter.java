package com.example.productApp.adapters;
import com.example.productApp.models.ProductDetailModel;

public interface ProductDetailAdapter {
    ProductDetailModel fetchProductDetail(String productId);
}