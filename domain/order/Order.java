package ca.ulaval.glo4002.inn.domain.order;

import java.util.Objects;

import ca.ulaval.glo4002.inn.domain.food.FoodSupply;
import ca.ulaval.glo4002.inn.domain.food.FoodType;

public final class Order implements FoodSupply {
    private final FoodType foodType;
    private final int quantity;
    private final int orderedAtNight;
    private final int deliveryNight;

    public Order(FoodType foodType, int quantity, int orderedAtNight, int deliveryNight) {
        this.foodType = foodType;
        this.quantity = quantity;
        this.orderedAtNight = orderedAtNight;
        this.deliveryNight = deliveryNight;
    }

    @Override
    public FoodType getFoodType() {
        return foodType;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    public int getOrderedAtNight() {
        return orderedAtNight;
    }

    public int getDeliveryNight() {
        return deliveryNight;
    }

    public boolean isInTransitOnNight(int night) {
        return deliveryNight > night;
    }

    @Override
    public boolean isDeliveredOnNight(int night) {
        return deliveryNight == night;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Order order)) {
            return false;
        }
        return foodType == order.foodType
                && quantity == order.quantity
                && orderedAtNight == order.orderedAtNight
                && deliveryNight == order.deliveryNight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(foodType, quantity, orderedAtNight, deliveryNight);
    }

    @Override
    public String toString() {
        return "Order[foodType=" + foodType + ", quantity=" + quantity
                + ", orderedAtNight=" + orderedAtNight + ", deliveryNight=" + deliveryNight + "]";
    }
}
