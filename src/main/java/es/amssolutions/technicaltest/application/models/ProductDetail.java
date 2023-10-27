package es.amssolutions.technicaltest.application.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDetail {
    private Long id;
    private String name;
    private Double price;
    private Boolean available;
}
