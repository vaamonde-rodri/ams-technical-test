package es.amssolutions.technicaltest.domain.ports.application;

import es.amssolutions.technicaltest.domain.models.Product;

import java.util.List;

public interface ProductPort {
    List<Product> getSimilarProducts(Long productId);
}
