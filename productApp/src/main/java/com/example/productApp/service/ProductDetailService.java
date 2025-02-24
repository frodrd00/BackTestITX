package com.example.productApp.service;

import com.example.productApp.model.ProductDetail;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductDetailService {

    private final RestTemplate restTemplate;

    @Autowired
    public ProductDetailService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private final String BASE_URL = "http://localhost:3001";

    public ProductDetail getProductDetail(String productId) {
        String productDetailUrl = BASE_URL + "/product/" + productId;
        return restTemplate.getForObject(productDetailUrl, ProductDetail.class);
    }
}
