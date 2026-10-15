package ca.ulaval.glo4002.inn.interfaces.rest.dto.responses;

public record SubscriptionResponse(
        String subscriptionName,
        String foodType,
        int quantity,
        int period,
        String status,
        String refusalReason,
        Integer nextDeliveryNight
) {
}
