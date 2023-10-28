package es.amssolutions.technicaltest.infrastructure.adapters;

import es.amssolutions.technicaltest.domain.models.Product;
import es.amssolutions.technicaltest.domain.ports.infrastructure.ProductDatasourcePort;
import es.amssolutions.technicaltest.infrastructure.mappers.ProductDatasourceMapper;
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
        return productRestClient.getSimilarProducts(productId).getSimilarProductsIds();
    }

    @Override
    public Optional<Product> findSimilarProduct(Long productId) {
        return Optional.ofNullable(productDatasourceMapper.toProduct(productRestClient.getProduct(productId)));
    }
}
