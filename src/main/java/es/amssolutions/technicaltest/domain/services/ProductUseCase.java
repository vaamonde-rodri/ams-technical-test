package es.amssolutions.technicaltest.domain.services;

import es.amssolutions.technicaltest.domain.models.Product;
import es.amssolutions.technicaltest.domain.ports.application.ProductPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductUseCase implements ProductPort {
    @Override
    public List<Product> getSimilarProducts(Long productId) {
        return List.of(
                Product.builder()
                        .id(1L)
                        .name("Product 1")
                        .price(10.0)
                        .available(true)
                        .build()
        );
    }
}
