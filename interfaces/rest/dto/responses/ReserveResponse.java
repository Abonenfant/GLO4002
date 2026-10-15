package ca.ulaval.glo4002.inn.interfaces.rest.dto.responses;

import java.util.List;

import ca.ulaval.glo4002.inn.domain.food.FoodType;

public record ReserveResponse(
        FoodType foodType,
        int maxCapacity,
        int stockedQuantity,
        List<ShipmentResponse> shipments,
        int totalDiscarded,
        int totalOverflowed) {
}
