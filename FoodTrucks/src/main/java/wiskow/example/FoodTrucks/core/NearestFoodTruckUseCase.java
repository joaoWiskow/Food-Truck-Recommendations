package wiskow.example.FoodTrucks.core;

import java.util.List;

public interface NearestFoodTruckUseCase {
    List<RestaurantResponse> nearestFoodTruck(String cep);
}
