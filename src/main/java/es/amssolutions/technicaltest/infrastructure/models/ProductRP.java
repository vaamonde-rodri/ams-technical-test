package es.amssolutions.technicaltest.infrastructure.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRP {
    private String id;
    private String name;
    private Double price;
    private Boolean availability;
}
