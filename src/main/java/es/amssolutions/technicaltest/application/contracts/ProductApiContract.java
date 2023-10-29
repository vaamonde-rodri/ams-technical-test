package es.amssolutions.technicaltest.application.contracts;

import es.amssolutions.technicaltest.application.constants.ApiPath;
import es.amssolutions.technicaltest.application.models.ProductDetail;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(ApiPath.PRODUCT)
public interface ProductApiContract {

    @Operation(summary = "Similar Products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product found", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    type = "array",
                                    implementation = ProductDetail.class,
                                    description = "List of similar products to a given one ordered by similarity",
                                    minimum = "0"
                            )
                    )
            }),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @GetMapping("{productId}/similar")
    List<ProductDetail> getSimilarProducts(@PathVariable Long productId);
}
