package com.example.productApp.controllers;

import com.example.productApp.models.ProductDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetSimilarProducts() {

        String productId = "2";
        ResponseEntity<ProductDetail[]> response = restTemplate.getForEntity("/product/" + productId + "/similar", ProductDetail[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        ProductDetail[] similarProducts = response.getBody();
        assertNotNull(similarProducts, "La respuesta de productos similares no debe ser nula");

    }
}