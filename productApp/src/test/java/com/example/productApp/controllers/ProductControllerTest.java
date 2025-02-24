package com.example.productApp.controllers;

import com.example.productApp.facade.ProductFacade;
import com.example.productApp.models.ProductDetail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ProductControllerTest {

    @Mock
    private ProductFacade productFacade;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetSimilarProductsReturnsOk() {
        String productId = "123";
        ProductDetail productDetail1 = new ProductDetail("456", "Product B", 100.0, true);
        ProductDetail productDetail2 = new ProductDetail("789", "Product C", 150.0, true);

        when(productFacade.getSimilarProducts(productId)).thenReturn(List.of(productDetail1, productDetail2));

        ResponseEntity<List<ProductDetail>> response = productController.getSimilarProducts(productId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        assertEquals(productDetail1, response.getBody().get(0));
        assertEquals(productDetail2, response.getBody().get(1));
    }

    @Test
    void testGetSimilarProductsReturnsNotFound() {
        String productId = "123";

        when(productFacade.getSimilarProducts(productId)).thenReturn(Collections.emptyList());

        ResponseEntity<List<ProductDetail>> response = productController.getSimilarProducts(productId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(0, response.getBody().size());
    }
}