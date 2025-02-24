package com.example.productApp.adapters;

import com.example.productApp.exceptions.ProductNotFoundException;
import com.example.productApp.models.ProductDetailModel;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class ExternalProductDetailAdapter implements ProductDetailAdapter {

    private final RestTemplate restTemplate;

    public ExternalProductDetailAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Base URL de la API externa (mocks)
    private final String BASE_URL = "http://localhost:3001";

    @Override
    public ProductDetailModel fetchProductDetail(String productId) {
        String url = BASE_URL + "/product/" + productId;
        try {
            return restTemplate.getForObject(url, ProductDetailModel.class);
        } catch (HttpClientErrorException.NotFound ex) {
            throw new ProductNotFoundException("Detalle del producto" + productId + " no encontrado.");
        }
    }
}