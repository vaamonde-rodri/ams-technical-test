package es.amssolutions.technicaltest.infrastructure.mappers;

import es.amssolutions.technicaltest.domain.models.Product;
import es.amssolutions.technicaltest.infrastructure.models.ProductRP;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductDatasourceMapperTest {

    private final ProductDatasourceMapper productDatasourceMapper = Mappers.getMapper(ProductDatasourceMapper.class);

    @Test
    public void toProduct_shouldConvertProductRPToProduct() {
        ProductRP productRP = ProductRP.builder()
                .id("1")
                .name("Producto de prueba")
                .price(10.00)
                .availability(true)
                .build();

        Product product = productDatasourceMapper.toProduct(productRP);

        assertEquals(1L, product.getId());
        assertEquals("Producto de prueba", product.getName());
        assertEquals(10.00, product.getPrice());
        assertTrue(product.getAvailable());
    }
}
