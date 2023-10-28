package es.amssolutions.technicaltest.infrastructure.services;

import es.amssolutions.technicaltest.infrastructure.models.ProductRP;
import es.amssolutions.technicaltest.infrastructure.models.SimilarProductsRP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ProductRestClient {

    @Value("${api.url}")
    private String apiUrl;

    @Autowired
    RestTemplate restTemplate;

    public SimilarProductsRP getSimilarProducts(Long productId) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .path("/products/{productId}/similar")
                .queryParam("productId", productId);

        ResponseEntity<SimilarProductsRP> response = restTemplate.getForEntity(builder.toUriString(), SimilarProductsRP.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new RuntimeException("Error getting similar products");
        }
    }

    public ProductRP getProduct(Long productId) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .path("/products/{productId}")
                .queryParam("productId", productId);

        ResponseEntity<ProductRP> response = restTemplate.getForEntity(builder.toUriString(), ProductRP.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new RuntimeException("Error getting product");
        }
    }
}
