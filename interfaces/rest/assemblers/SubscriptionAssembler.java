package ca.ulaval.glo4002.inn.interfaces.rest.assemblers;

import ca.ulaval.glo4002.inn.domain.subscription.Subscription;
import ca.ulaval.glo4002.inn.domain.subscription.SubscriptionTerms;
import ca.ulaval.glo4002.inn.interfaces.rest.dto.requests.SubscriptionRequest;
import ca.ulaval.glo4002.inn.interfaces.rest.dto.responses.SubscriptionResponse;

public class SubscriptionAssembler {
    public static SubscriptionTerms toSubscriptionTerms(SubscriptionRequest request) {
        return new SubscriptionTerms(request.subscriptionName(), request.foodType(), request.quantity(), request.period());
    }

    public static SubscriptionResponse toResponse(Subscription subscription, int currentNight) {
        return new SubscriptionResponse(
                subscription.getName(),
                subscription.getFoodType().name(),
                subscription.getQuantity(),
                subscription.getPeriod(),
                subscription.getStatus().name(),
                subscription.getRefusalReason().map(Enum::name).orElse(null),
                subscription.nextDeliveryNightAfter(currentNight).orElse(null)
        );
    }
}
