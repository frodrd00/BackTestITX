package com.example.productApp.service;

import com.example.productApp.model.ProductDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductSimilarService {

    private final RestTemplate restTemplate;
    private final ProductDetailService productDetailService;

    @Autowired
    public ProductSimilarService( RestTemplate restTemplate, ProductDetailService productDetailService) {
        this.restTemplate = restTemplate;
        this.productDetailService = productDetailService;
    }

    private final String BASE_URL = "http://localhost:3001";

    public List<ProductDetail> getSimilarProducts(String productId) {

        String similarIdsUrl = BASE_URL + "/product/" + productId + "/similarids";
        ResponseEntity<List<String>> responseSimilarIds;

        responseSimilarIds = restTemplate.exchange(
                similarIdsUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<String>>() {}
        );

        List<String> similarIds = responseSimilarIds.getBody();
        if (similarIds == null) {
            similarIds = new ArrayList<>();
        }

        List<ProductDetail> similarProducts = new ArrayList<>();
        for (String similarId : similarIds) {
            ProductDetail detail = productDetailService.getProductDetail(similarId);
            similarProducts.add(detail);
        }
        return similarProducts;
    }
}
