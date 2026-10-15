package ca.ulaval.glo4002.inn.domain.subscription;

public class SubscriptionFactory {
    public Subscription createActiveSubscription(int subscribedAtNight, SubscriptionTerms terms) {
        return new Subscription(subscribedAtNight, terms, SubscriptionStatus.ACTIVE, null);
    }

    public Subscription createRefusedSubscription(int executionNight, SubscriptionTerms terms, SubscriptionRefusalReason refusalReason) {
        return new Subscription(executionNight, terms, SubscriptionStatus.REFUSED, refusalReason);
    }
}
