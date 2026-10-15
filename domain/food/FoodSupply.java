package ca.ulaval.glo4002.inn.domain.food;

public interface FoodSupply {
    FoodType getFoodType();

    int getQuantity();

    boolean isDeliveredOnNight(int night);
}
