package es.amssolutions.technicaltest.application.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private double price;
    private boolean available;
}
