package es.amssolutions.technicaltest.application.contracts;

import es.amssolutions.technicaltest.application.constants.ApiPath;
import es.amssolutions.technicaltest.application.models.ProductDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(ApiPath.PRODUCT)
public interface ProductApiContract {

    @Operation(summary = "Get product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product found", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductDTO.class)
                    )
            }),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @GetMapping("{productId}/similar")
    ProductDTO getSimilarProducts(Long productId);
}