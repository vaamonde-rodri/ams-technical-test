package es.amssolutions.technicaltest.domain.ports.infrastructure;

import es.amssolutions.technicaltest.domain.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDatasourcePort {
    List<Long> getSimilarProductsIds(Long productId);

    Optional<Product> findSimilarProduct(Long productId);
}
