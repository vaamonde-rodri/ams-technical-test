package es.amssolutions.technicaltest.domain.services;

import es.amssolutions.technicaltest.domain.models.Product;
import es.amssolutions.technicaltest.domain.ports.application.ProductPort;
import es.amssolutions.technicaltest.domain.ports.infrastructure.ProductDatasourcePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductUseCase implements ProductPort {

    private final ProductDatasourcePort productDatasourcePort;

    @Override
    public List<Product> getSimilarProducts(Long productId) {
        //Obtenemos las ids de productos similares
        List<Long> similarProductsIds = productDatasourcePort.getSimilarProductsIds(productId);

        //Consultamos cada producto similar y lo devolvemos en un listado
        return similarProductsIds.stream()
                .map(productDatasourcePort::findSimilarProduct)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }
}
