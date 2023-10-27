package es.amssolutions.technicaltest.application.mappers;

import es.amssolutions.technicaltest.application.models.ProductDetail;
import es.amssolutions.technicaltest.domain.models.Product;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProductMapper {

    default ProductDetail toProductDetail(Product product) {
        return ProductDetail.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .available(product.getAvailable())
                .build();
    }
}
