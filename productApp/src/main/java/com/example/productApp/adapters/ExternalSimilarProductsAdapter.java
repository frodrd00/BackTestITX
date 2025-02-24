package com.example.productApp.adapters;

import com.example.productApp.exceptions.ProductNotFoundException;
import jakarta.servlet.MultipartConfigElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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
    private final String BASE_URL;
    Logger logger = LoggerFactory.getLogger(ExternalSimilarProductsAdapter.class);

    public ExternalSimilarProductsAdapter(RestTemplate restTemplate, @Value("${external.api.base-url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.BASE_URL = baseUrl;
    }

    @Override
    public List<String> fetchSimilarProductIds(String productId) {
        String url = BASE_URL + "/product/" + productId + "/similarids";
        ResponseEntity<List<String>> response;
        try {
            response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>() {}
            );
        } catch (HttpClientErrorException ex) {
            logger.error("[APP] Error al obtener productos similares al id de producto {}", productId);
            throw new ProductNotFoundException("Producto " + productId + " no encontrado.");
        }
        List<String> similarIds = response.getBody();
        if(similarIds !=null) {
            logger.info("[APP] Encontrado {} productos similares para el id de producto {}", similarIds.size(), productId);
            return similarIds;
        }
        return new ArrayList<>();
    }
}