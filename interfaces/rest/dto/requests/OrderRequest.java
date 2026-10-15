package ca.ulaval.glo4002.inn.interfaces.rest.dto.requests;

import ca.ulaval.glo4002.inn.domain.food.FoodType;

public record OrderRequest(FoodType foodType, int quantity, int deliveryDelay) {
}
