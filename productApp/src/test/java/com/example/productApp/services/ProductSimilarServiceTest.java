package com.example.productApp.services;

import com.example.productApp.adapters.ProductDetailAdapter;
import com.example.productApp.adapters.SimilarProductsAdapter;
import com.example.productApp.models.ProductDetailModel;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductSimilarServiceTest {

    @Mock
    private ProductDetailAdapter productDetailAdapter;

    @Mock
    private SimilarProductsAdapter similarProductsAdapter;

    @InjectMocks
    private ProductSimilarService productSimilarService;

    public ProductSimilarServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetSimilarProductsReturnsCorrectDetails() {
        String productId = "P123";
        List<String> similarIds = Arrays.asList("P124", "P125");
        ProductDetailModel product1 = new ProductDetailModel("P124", "Product 124", 10.99, true);
        ProductDetailModel product2 = new ProductDetailModel("P125", "Product 125", 15.49, false);

        when(similarProductsAdapter.fetchSimilarProductIds(productId)).thenReturn(similarIds);
        when(productDetailAdapter.fetchProductDetail("P124")).thenReturn(product1);
        when(productDetailAdapter.fetchProductDetail("P125")).thenReturn(product2);

        List<ProductDetailModel> result = productSimilarService.getSimilarProducts(productId);

        assertEquals(2, result.size());
        assertEquals(product1, result.get(0));
        assertEquals(product2, result.get(1));

        verify(similarProductsAdapter, times(1)).fetchSimilarProductIds(productId);
        verify(productDetailAdapter, times(1)).fetchProductDetail("P124");
        verify(productDetailAdapter, times(1)).fetchProductDetail("P125");
    }

    @Test
    void testGetSimilarProductsWhenNoSimilarIds() {
        String productId = "P123";

        when(similarProductsAdapter.fetchSimilarProductIds(productId)).thenReturn(Collections.emptyList());

        List<ProductDetailModel> result = productSimilarService.getSimilarProducts(productId);

        assertEquals(0, result.size());
        verify(similarProductsAdapter, times(1)).fetchSimilarProductIds(productId);
        verifyNoInteractions(productDetailAdapter);
    }

}