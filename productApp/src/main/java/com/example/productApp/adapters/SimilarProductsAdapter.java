package com.example.productApp.adapters;

import java.util.List;

public interface SimilarProductsAdapter {
    List<String> fetchSimilarProductIds(String productId);
}