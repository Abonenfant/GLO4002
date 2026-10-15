package ca.ulaval.glo4002.inn.domain.subscription;

import ca.ulaval.glo4002.inn.domain.food.FoodType;

public record SubscriptionTerms(String name, FoodType foodType, int quantity, int period) {
}
