package ca.ulaval.glo4002.inn.interfaces.rest.assemblers;

import ca.ulaval.glo4002.inn.domain.order.Order;
import ca.ulaval.glo4002.inn.interfaces.rest.dto.responses.OrderResponse;

public class OrderAssembler {
    public static OrderResponse toResponse(Order order) {
        return new OrderResponse(
                order.getFoodType(),
                order.getQuantity(),
                order.getOrderedAtNight(),
                order.getDeliveryNight());
    }
}
