package ca.ulaval.glo4002.inn.domain.subscription;

import java.util.Optional;

import ca.ulaval.glo4002.inn.domain.food.FoodSupply;
import ca.ulaval.glo4002.inn.domain.food.FoodType;

public final class Subscription implements FoodSupply {
    private final int subscribedAtNight;
    private final SubscriptionTerms terms;
    private SubscriptionStatus status;
    private final SubscriptionRefusalReason refusalReason;

    Subscription(int subscribedAtNight, SubscriptionTerms terms, SubscriptionStatus status, SubscriptionRefusalReason refusalReason) {
        this.subscribedAtNight = subscribedAtNight;
        this.terms = terms;
        this.status = status;
        this.refusalReason = refusalReason;
    }

    public String getName() {
        return terms.name();
    }

    @Override
    public FoodType getFoodType() {
        return terms.foodType();
    }

    @Override
    public int getQuantity() {
        return terms.quantity();
    }

    public int getPeriod() {
        return terms.period();
    }

    public SubscriptionStatus getStatus() {
        return status;
    }

    public Optional<SubscriptionRefusalReason> getRefusalReason() {
        return Optional.ofNullable(refusalReason);
    }

    public boolean isActive() {
        return status == SubscriptionStatus.ACTIVE;
    }

    public void cancel() {
        status = SubscriptionStatus.CANCELLED;
    }

    @Override
    public boolean isDeliveredOnNight(int night) {
        return isActive() && isScheduledDeliveryNight(night);
    }

    private boolean isScheduledDeliveryNight(int night) {
        int nightsSinceSubscription = nightsSinceSubscriptionAt(night);
        return nightsSinceSubscription > 0 && nightsSinceSubscription % getPeriod() == 0;
    }

    public Optional<Integer> nextDeliveryNightAfter(int night) {
        if (isActive()) {
            return Optional.of(nextScheduledDeliveryNightAfter(night));
        }
        return Optional.empty();
    }

    private int nextScheduledDeliveryNightAfter(int night) {
        int completedPeriods = nightsSinceSubscriptionAt(night) / getPeriod();
        return subscribedAtNight + (completedPeriods + 1) * getPeriod();
    }

    private int nightsSinceSubscriptionAt(int night) {
        return night - subscribedAtNight;
    }

    public boolean isRefused() {
        return status == SubscriptionStatus.REFUSED;
    }
}
