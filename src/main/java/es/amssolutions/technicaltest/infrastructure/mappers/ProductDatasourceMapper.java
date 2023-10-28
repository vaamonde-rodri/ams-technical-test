package es.amssolutions.technicaltest.infrastructure.mappers;

import es.amssolutions.technicaltest.domain.models.Product;
import es.amssolutions.technicaltest.infrastructure.models.ProductRP;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProductDatasourceMapper {

    default Product toProduct(ProductRP productRP) {
        return Product.builder()
                .id(Long.valueOf(productRP.getId()))
                .name(productRP.getName())
                .price(productRP.getPrice())
                .available(productRP.getAvailable())
                .build();
    }
}
