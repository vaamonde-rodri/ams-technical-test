package es.amssolutions.technicaltest.infrastructure.adapters;

import es.amssolutions.technicaltest.domain.models.Product;
import es.amssolutions.technicaltest.infrastructure.mappers.ProductDatasourceMapper;
import es.amssolutions.technicaltest.infrastructure.models.ProductRP;
import es.amssolutions.technicaltest.infrastructure.models.SimilarProductsRP;
import es.amssolutions.technicaltest.infrastructure.services.ProductRestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class ProductAdapterTest {
    @InjectMocks
    private ProductAdapter productAdapter;

    @Mock
    private ProductRestClient productRestClient;

    @Mock
    private ProductDatasourceMapper productDatasourceMapper;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetSimilarProductsIds() {
        Long productId = 1L;
        SimilarProductsRP similarProductsRP = SimilarProductsRP.builder()
                .similarProductsIds(List.of(2L, 3L, 4L))
                .build();

        when(productRestClient.getSimilarProducts(productId)).thenReturn(similarProductsRP);

        List<Long> result = productAdapter.getSimilarProductsIds(productId);

        assertEquals(3, result.size());
        assertTrue(result.contains(2L));
        assertTrue(result.contains(3L));
        assertTrue(result.contains(4L));
    }

    @Test
    void testGetSimilarProductsIdsNoData() {
        Long productId = 2L;

        when(productRestClient.getSimilarProducts(productId)).thenReturn(null);

        List<Long> result = productAdapter.getSimilarProductsIds(productId);

        assertEquals(0, result.size());
    }

    @Test
    void testFindSimilarProduct() {
        Long productId = 1L;
        ProductRP productRP = ProductRP.builder().build();
        Product product = Product.builder().build();

        when(productRestClient.getProduct(productId)).thenReturn(productRP);
        when(productDatasourceMapper.toProduct(productRP)).thenReturn(product);

        Optional<Product> result = productAdapter.findSimilarProduct(productId);

        assertTrue(result.isPresent());
        assertEquals(product, result.get());
    }

    @Test
    void testFindSimilarProductNoData() {
        Long productId = 2L;

        when(productRestClient.getProduct(productId)).thenReturn(null);

        Optional<Product> result = productAdapter.findSimilarProduct(productId);

        assertFalse(result.isPresent());
    }
}
