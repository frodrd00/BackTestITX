package com.example.productApp.adapters;

import com.example.productApp.exceptions.ProductNotFoundException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExternalSimilarProductsAdapter implements SimilarProductsAdapter {

    private final RestTemplate restTemplate;

    public ExternalSimilarProductsAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private final String BASE_URL = "http://localhost:3001";

    @Override
    public List<String> fetchSimilarProductIds(String productId) {
        String url = BASE_URL + "/product/" + productId + "/similarids";
        ResponseEntity<List<String>> response;
        try {
            response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<String>>() {}
            );
        } catch (HttpClientErrorException ex) {
            throw new ProductNotFoundException("Producto " + productId + " no encuntrado.");
        }
        List<String> similarIds = response.getBody();
        return similarIds != null ? similarIds : new ArrayList<>();
    }
}