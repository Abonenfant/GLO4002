package ca.ulaval.glo4002.inn.interfaces.rest.dto.responses;

import ca.ulaval.glo4002.inn.domain.food.FoodType;

public record OrderResponse(FoodType foodType, int quantity, int orderedAtNight, int deliveryNight) {
}
