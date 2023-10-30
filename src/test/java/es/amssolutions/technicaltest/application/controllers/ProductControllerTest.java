package es.amssolutions.technicaltest.application.controllers;

import es.amssolutions.technicaltest.application.exceptions.NoSimilarProductsFoundException;
import es.amssolutions.technicaltest.application.mappers.ProductMapper;
import es.amssolutions.technicaltest.application.models.ProductDetail;
import es.amssolutions.technicaltest.domain.models.Product;
import es.amssolutions.technicaltest.domain.ports.application.ProductPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;

public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductPort productPort;

    @Mock
    private ProductMapper productMapper;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetSimilarProducts() {
        Long productId = 1L;
        List<ProductDetail> expectedProductDetails = new ArrayList<>();
        expectedProductDetails.add(ProductDetail.builder()
                .id(1L)
                .name("Product 1")
                .price(10.0)
                .available(true)
                .build());

        List<Product> expectedProducts = new ArrayList<>();
        expectedProducts.add(Product.builder()
                .id(1L)
                .name("Product 1")
                .price(10.0)
                .available(true)
                .build());

        // Simula el comportamiento del productPort y productMapper
        Mockito.when(productPort.getSimilarProducts(productId)).thenReturn(expectedProducts);
        Mockito.when(productMapper.toProductDetail(Mockito.any())).thenReturn(expectedProductDetails.get(0));

        List<ProductDetail> result = productController.getSimilarProducts(productId);

        assertEquals(expectedProductDetails.size(), result.size());
        assertThat(expectedProductDetails).usingRecursiveComparison().isEqualTo(result);
        assertEquals(1L, result.get(0).getId());
        assertEquals("Product 1", result.get(0).getName());
        assertEquals(10.0, result.get(0).getPrice());
        assertEquals(true, result.get(0).getAvailable());
    }

    @Test
    void testGetSimilarProductsNoProductsFound() {
        Long productId = 2L;

        Mockito.when(productPort.getSimilarProducts(productId)).thenReturn(new ArrayList<>());

        assertThrows(NoSimilarProductsFoundException.class, () -> productController.getSimilarProducts(productId));
    }
}
