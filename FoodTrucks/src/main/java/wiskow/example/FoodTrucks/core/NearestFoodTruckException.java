package wiskow.example.FoodTrucks.core;

public class NearestFoodTruckException extends RuntimeException {
    public NearestFoodTruckException(String message) {
        super(message);
    }
    public NearestFoodTruckException(String message, Throwable cause) {
        super(message, cause);
    }
}
