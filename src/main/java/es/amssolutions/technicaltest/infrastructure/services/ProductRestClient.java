package es.amssolutions.technicaltest.infrastructure.services;

import es.amssolutions.technicaltest.infrastructure.models.ProductRP;
import es.amssolutions.technicaltest.infrastructure.models.SimilarProductsRP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriUtils;

import javax.sound.midi.Soundbank;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProductRestClient {

    @Value("${api.url}")
    private String apiUrl;

    @Autowired
    RestTemplate restTemplate;

    public SimilarProductsRP getSimilarProducts(Long productId) {
        UriComponentsBuilder componentsBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .path("/product/{productId}/similarids");
        URI uri = componentsBuilder.buildAndExpand(Map.of("productId", productId)).toUri();

        try {
            ResponseEntity<List<Long>> response = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
            });
            if (response.getStatusCode().is2xxSuccessful()) {
                return SimilarProductsRP.builder()
                        .similarProductsIds(response.getBody())
                        .build();
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public ProductRP getProduct(Long productId) {
        UriComponentsBuilder componentsBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .path("/product/{productId}");
        URI uri = componentsBuilder.buildAndExpand(Map.of("productId", productId)).toUri();

        try {
            ResponseEntity<ProductRP> response = restTemplate.getForEntity(uri, ProductRP.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }


    }
}
