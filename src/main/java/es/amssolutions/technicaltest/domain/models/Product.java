package es.amssolutions.technicaltest.domain.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
    private Long id;
    private String name;
    private String description;
    private double price;
    private boolean available;
}
