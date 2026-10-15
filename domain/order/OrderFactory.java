package ca.ulaval.glo4002.inn.domain.order;

import ca.ulaval.glo4002.inn.domain.food.FoodType;

public class OrderFactory {
    public Order createPlacedOrder(int executionNight, FoodType foodType, int quantity, int deliveryDelay) {
        int deliveryNight = executionNight + deliveryDelay;
        return new Order(foodType, quantity, executionNight, deliveryNight);
    }
}
