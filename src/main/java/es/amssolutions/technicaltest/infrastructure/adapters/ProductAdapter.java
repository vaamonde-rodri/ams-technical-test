package es.amssolutions.technicaltest.infrastructure.adapters;

import es.amssolutions.technicaltest.domain.models.Product;
import es.amssolutions.technicaltest.domain.ports.infrastructure.ProductDatasourcePort;
import es.amssolutions.technicaltest.infrastructure.mappers.ProductDatasourceMapper;
import es.amssolutions.technicaltest.infrastructure.models.ProductRP;
import es.amssolutions.technicaltest.infrastructure.models.SimilarProductsRP;
import es.amssolutions.technicaltest.infrastructure.services.ProductRestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductAdapter implements ProductDatasourcePort {

    private final ProductRestClient productRestClient;
    private final ProductDatasourceMapper productDatasourceMapper;

    @Override
    public List<Long> getSimilarProductsIds(Long productId) {
        SimilarProductsRP similarProductsRP = productRestClient.getSimilarProducts(productId);
        if (similarProductsRP == null) {
            return List.of();
        } else {
            return similarProductsRP.getSimilarProductsIds();
        }
    }

    @Override
    public Optional<Product> findSimilarProduct(Long productId) {
        ProductRP productRP = productRestClient.getProduct(productId);

        if (productRP == null) {
            return Optional.empty();
        } else {
            return Optional.ofNullable(productDatasourceMapper.toProduct(productRP));
        }
    }
}
