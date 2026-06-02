package wiskow.example.FoodTrucks.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wiskow.example.FoodTrucks.adapters.NearestFoodTrucksGateway;
import wiskow.example.FoodTrucks.core.NearestFoodTruckUseCase;
import wiskow.example.FoodTrucks.core.RestaurantResponse;

import java.util.List;

@Service
public class NearestFoodTrucksService implements NearestFoodTruckUseCase {

    public final NearestFoodTrucksGateway nearestFoodTrucksGateway;

    @Autowired
    public NearestFoodTrucksService(NearestFoodTrucksGateway nearestGateway){
        this.nearestFoodTrucksGateway = nearestGateway;
    }

    @Override
    public List<RestaurantResponse> nearestFoodTruck(String cep) {
        return this.nearestFoodTrucksGateway.nearestFoodTruck(cep);
    }
}
