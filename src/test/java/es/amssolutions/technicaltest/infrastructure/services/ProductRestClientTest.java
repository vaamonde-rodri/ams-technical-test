package es.amssolutions.technicaltest.infrastructure.services;

import es.amssolutions.technicaltest.infrastructure.models.ProductRP;
import es.amssolutions.technicaltest.infrastructure.models.SimilarProductsRP;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.ErrorResponse;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@SpringBootTest(properties = {"api.url=http://localhost:3001"})
public class ProductRestClientTest {

    @Value("${api.url}")
    private String apiUrl;

    @Mock
    private RestTemplate restTemplate;

    @Autowired
    private ProductRestClient productRestClient;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        productRestClient.restTemplate = restTemplate;
    }

    @Test
    public void testGetSimilarProducts_Success() {
        Long productId = 1L;
        List<Long> similarProductsIds = List.of(2L, 3L);


        ResponseEntity<List<Long>> response = new ResponseEntity<>(similarProductsIds, HttpStatus.OK);
        when(restTemplate.exchange(any(URI.class), any(HttpMethod.class), any(), any(ParameterizedTypeReference.class))).thenReturn(response);

        SimilarProductsRP similarProductsRP = productRestClient.getSimilarProducts(productId);

        assertNotNull(similarProductsRP);
        assertEquals(similarProductsIds, similarProductsRP.getSimilarProductsIds());
    }

    @Test
    public void testGetSimilarProducts_Failure() {
        Long productId = 1L;

        when(restTemplate.exchange(any(URI.class), any(HttpMethod.class), any(), any(ParameterizedTypeReference.class))).thenReturn(null);

        SimilarProductsRP similarProductsRP = productRestClient.getSimilarProducts(productId);

        assertNull(similarProductsRP);
    }

    @Test
    public void testGetProduct_Success() {
        Long productId = 1L;
        ProductRP productRP = ProductRP.builder()
                .id("1")
                .name("Product 1")
                .price(100.0)
                .availability(true)
                .build();
        URI uri = URI.create(apiUrl + "/product/1");

        ResponseEntity<ProductRP> response = new ResponseEntity<>(productRP, HttpStatus.OK);
        when(restTemplate.getForEntity(uri, ProductRP.class)).thenReturn(response);

        ProductRP product = productRestClient.getProduct(productId);

        assertNotNull(product);
        assertEquals(productRP, product);
    }

    @Test
    public void testGetProduct_Failure() {
        Long productId = 1L;
        URI uri = URI.create(apiUrl + "/product/1");

        ResponseEntity<ProductRP> response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        when(restTemplate.getForEntity(uri, ProductRP.class)).thenReturn(response);

        ProductRP getProduct = productRestClient.getProduct(productId);

        assertNull(getProduct);


        ResponseEntity<ProductRP> responseError = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        when(restTemplate.getForEntity(uri, ProductRP.class)).thenReturn(responseError);

        ProductRP getProductError = productRestClient.getProduct(productId);

        assertNull(getProductError);
    }
}
