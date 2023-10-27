package es.amssolutions.technicaltest.application.controllers;

import es.amssolutions.technicaltest.application.contracts.ProductApiContract;
import es.amssolutions.technicaltest.application.models.ProductDTO;
import es.amssolutions.technicaltest.domain.ports.application.ProductPort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController implements ProductApiContract {

    private final ProductPort productPort;

    @Override
    public ProductDTO getSimilarProducts(Long productId) {
        return ProductDTO.builder()
                .id(productId)
                .name("Product 1")
                .price(10.0)
                .available(true)
                .build();
    }
}
