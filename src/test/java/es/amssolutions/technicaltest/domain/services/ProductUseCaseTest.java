package es.amssolutions.technicaltest.domain.services;

import es.amssolutions.technicaltest.domain.models.Product;
import es.amssolutions.technicaltest.domain.ports.infrastructure.ProductDatasourcePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class ProductUseCaseTest {

    @InjectMocks
    private ProductUseCase productUseCase;

    @Mock
    private ProductDatasourcePort productDatasourcePort;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetSimilarProducts() {
        Long productId = 1L;
        List<Long> similarProductsIds = List.of(2L, 3L, 4L);

        when(productDatasourcePort.getSimilarProductsIds(productId)).thenReturn(similarProductsIds);

        Product product1 = buildProduct(1);
        Product product2 = buildProduct(2);
        Product product3 = buildProduct(3);
        when(productDatasourcePort.findSimilarProduct(2L)).thenReturn(Optional.of(product1));
        when(productDatasourcePort.findSimilarProduct(3L)).thenReturn(Optional.of(product2));
        when(productDatasourcePort.findSimilarProduct(4L)).thenReturn(Optional.of(product3));

        List<Product> result = productUseCase.getSimilarProducts(productId);

        assertEquals(3, result.size());
        assertTrue(result.contains(product1));
        assertTrue(result.contains(product2));
        assertTrue(result.contains(product3));
    }

    private Product buildProduct(int i) {
        return Product.builder()
                .id((long) i)
                .name("Producto " + i)
                .price(100.0 + i)
                .available(true)
                .build();
    }

    @Test
    void testGetSimilarProductsNoSimilarProductsFound() {
        Long productId = 5L;
        List<Long> similarProductsIds = new ArrayList<>();

        when(productDatasourcePort.getSimilarProductsIds(productId)).thenReturn(similarProductsIds);

        List<Product> result = productUseCase.getSimilarProducts(productId);

        assertEquals(0, result.size());
    }

    @Test
    void testGetSimilarProductsSomeProductsNotFound() {
        Long productId = 6L;
        List<Long> similarProductsIds = List.of(7L, 8L, 9L);

        when(productDatasourcePort.getSimilarProductsIds(productId)).thenReturn(similarProductsIds);

        Product product7 = buildProduct(7);
        Product product8 = buildProduct(8);
        Product product9 = buildProduct(9);
        when(productDatasourcePort.findSimilarProduct(7L)).thenReturn(Optional.of(product7));
        when(productDatasourcePort.findSimilarProduct(8L)).thenReturn(Optional.empty());
        when(productDatasourcePort.findSimilarProduct(9L)).thenReturn(Optional.of(product9));

        List<Product> result = productUseCase.getSimilarProducts(productId);

        assertEquals(2, result.size());
        assertTrue(result.contains(product7));
        assertFalse(result.contains(product8));
        assertTrue(result.contains(product9));
    }
}
