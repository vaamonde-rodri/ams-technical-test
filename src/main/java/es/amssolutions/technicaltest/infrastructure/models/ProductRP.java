package es.amssolutions.technicaltest.infrastructure.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductRP {
    private String id;
    private String name;
    private Double price;
    private Boolean available;
}
