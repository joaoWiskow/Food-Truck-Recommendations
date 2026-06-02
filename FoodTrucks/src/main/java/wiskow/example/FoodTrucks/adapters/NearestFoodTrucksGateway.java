package wiskow.example.FoodTrucks.adapters;

import wiskow.example.FoodTrucks.core.RestaurantResponse;

import java.util.List;

public interface NearestFoodTrucksGateway {
    List<RestaurantResponse> nearestFoodTruck(String cep);
}
