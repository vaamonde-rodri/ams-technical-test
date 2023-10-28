package es.amssolutions.technicaltest.infrastructure.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SimilarProductsRP {

    private List<Long> similarProductsIds;
}
