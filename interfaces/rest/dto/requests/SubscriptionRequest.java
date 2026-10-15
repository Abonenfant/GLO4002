package ca.ulaval.glo4002.inn.interfaces.rest.dto.requests;

import ca.ulaval.glo4002.inn.domain.food.FoodType;

public record SubscriptionRequest(String subscriptionName, FoodType foodType, int quantity, int period) {
}
