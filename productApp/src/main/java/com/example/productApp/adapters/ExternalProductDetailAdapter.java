package com.example.productApp.adapters;

import com.example.productApp.exceptions.ProductNotFoundException;
import com.example.productApp.models.ProductDetailModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class ExternalProductDetailAdapter implements ProductDetailAdapter {

    private final RestTemplate restTemplate;
    private final String baseUrl;
    Logger logger = LoggerFactory.getLogger(ExternalProductDetailAdapter.class);

    public ExternalProductDetailAdapter(RestTemplate restTemplate, @Value("${external.api.base-url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    @Override
    public ProductDetailModel fetchProductDetail(String productId) {
        String url = baseUrl + "/product/" + productId; // Se usa la propiedad inyectada
        try {
            logger.info("[APP] Obteniendo detalle del producto {}", productId);
            return restTemplate.getForObject(url, ProductDetailModel.class);
        } catch (HttpClientErrorException.NotFound ex) {
            logger.error("[APP] Error al obtener detalle del producto {}", productId);
            throw new ProductNotFoundException("Detalle del producto " + productId + " no encontrado.");
        }
    }
}