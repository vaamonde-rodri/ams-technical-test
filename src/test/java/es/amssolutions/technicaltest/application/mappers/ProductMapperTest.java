package es.amssolutions.technicaltest.application.mappers;

import es.amssolutions.technicaltest.application.models.ProductDetail;
import es.amssolutions.technicaltest.domain.models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductMapperTest {

    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testToProductDetail() {

        Product product = Product.builder()
                .id(1L)
                .name("Producto 1")
                .price(100.0)
                .available(true)
                .build();

        ProductDetail productDetail = productMapper.toProductDetail(product);

        assertEquals(1L, productDetail.getId());
        assertEquals("Producto 1", productDetail.getName());
        assertEquals(100.0, productDetail.getPrice());
        assertEquals(true, productDetail.getAvailable());
    }
}
