package wiskow.example.FoodTrucks.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import wiskow.example.FoodTrucks.application.NearestFoodTrucksService;
import wiskow.example.FoodTrucks.core.NearestFoodTruckException;
import wiskow.example.FoodTrucks.core.NearestFoodTruckRequest;
import wiskow.example.FoodTrucks.core.RestaurantResponse;

import java.util.List;
@Tag(name = "Suggester",description = "This classe is made to use aws to suggest top 5 nearest restaurants to the client")
@RestController
@RequestMapping("/api/nearFF")
public class NearestFoodTruckController {

    private final NearestFoodTrucksService nearestFoodTrucksService;
    @Autowired
    public NearestFoodTruckController(NearestFoodTrucksService nearestFoodTrucksService) {
        this.nearestFoodTrucksService = nearestFoodTrucksService;
    }

    @Operation(summary = "Suggestion",description = "Post method to get and returns top 5 restaurants near to the client",
    responses = {@ApiResponse(responseCode = "200", description = "Suggestions were offered",
            content = @Content(mediaType = "applicattion/json", schema=@Schema(implementation = RestaurantResponse.class))),
            @ApiResponse(responseCode = "500", description = "Problems with the requisition", content = @Content)})
    @PostMapping
    public ResponseEntity<List<RestaurantResponse>> getNearestFoodTrucks(@RequestBody NearestFoodTruckRequest request) {
        try {
            List<RestaurantResponse> sugg=this.nearestFoodTrucksService.nearestFoodTruck(request.cep());
            return ResponseEntity.ok(sugg);
        }catch (NearestFoodTruckException ex){
            return   ResponseEntity.internalServerError().build();
        }

    }

}
