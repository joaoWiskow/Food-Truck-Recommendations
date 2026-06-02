package wiskow.example.FoodTrucks.infra.als;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.services.geoplaces.GeoPlacesClient;
import software.amazon.awssdk.services.geoplaces.model.*;
import software.amazon.awssdk.services.location.LocationClient;
import software.amazon.awssdk.services.location.model.SearchPlaceIndexForTextRequest;
import software.amazon.awssdk.services.location.model.SearchPlaceIndexForTextResponse;
import wiskow.example.FoodTrucks.adapters.NearestFoodTrucksGateway;
import wiskow.example.FoodTrucks.core.NearestFoodTruckException;
import software.amazon.awssdk.services.geoplaces.model.SearchTextResponse;
import wiskow.example.FoodTrucks.core.RestaurantResponse;

import java.util.ArrayList;
import java.util.List;

@Service
public class GpcNearestFoodTrucks implements NearestFoodTrucksGateway {
    private final GeoPlacesClient geoPlacesClient;

    @Autowired
    public GpcNearestFoodTrucks(GeoPlacesClient geoPlacesClient) {
        this.geoPlacesClient = geoPlacesClient;
    }

    @Override
    public List<RestaurantResponse> nearestFoodTruck(String cep) {
        GeocodeRequest geocodeRequest= GeocodeRequest.builder()
                .queryText(cep)
                .filter(GeocodeFilter.builder().includeCountries(List.of("BRA")).build())
                .build();

        GeocodeResponse geocodeResponse = geoPlacesClient.geocode(geocodeRequest);
        List<Double> coor=geocodeResponse.resultItems().get(0).position();

        SearchTextRequest searchTextRequest= SearchTextRequest.builder().queryText("Restaurante").maxResults(5).biasPosition(coor).build();

        SearchTextResponse searchTextResponse =geoPlacesClient.searchText(searchTextRequest);

        List<RestaurantResponse> sugg=new ArrayList<>();

         for(int i=0;i<searchTextResponse.resultItems().size();i++) {
             sugg.add(new RestaurantResponse(searchTextResponse.resultItems().get(i).title(),searchTextResponse.resultItems().get(i).address().label()));
         }
         return sugg;
    }
}
