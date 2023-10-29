package es.amssolutions.technicaltest.application.controllers;

import es.amssolutions.technicaltest.application.contracts.ProductApiContract;
import es.amssolutions.technicaltest.application.exceptions.NoSimilarProductsFoundException;
import es.amssolutions.technicaltest.application.mappers.ProductMapper;
import es.amssolutions.technicaltest.application.models.ProductDetail;
import es.amssolutions.technicaltest.domain.ports.application.ProductPort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController implements ProductApiContract {

    private final ProductPort productPort;
    private final ProductMapper productMapper;

    @Override
    public List<ProductDetail> getSimilarProducts(Long productId) {
        List<ProductDetail> productDetails = productPort.getSimilarProducts(productId).stream()
                .map(productMapper::toProductDetail)
                .toList();

        if (productDetails.isEmpty()) {
            throw new NoSimilarProductsFoundException("No similar products found for productId: " + productId);
        } else {
            return productDetails;
        }
    }
}
