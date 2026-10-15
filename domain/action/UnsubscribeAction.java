package ca.ulaval.glo4002.inn.domain.action;

import ca.ulaval.glo4002.inn.domain.subscription.Subscription;
import ca.ulaval.glo4002.inn.domain.subscription.SubscriptionRepository;

public class UnsubscribeAction extends Action {
    private final String subscriptionName;
    private final SubscriptionRepository subscriptionRepository;

    public UnsubscribeAction(String subscriptionName, SubscriptionRepository subscriptionRepository) {
        this.subscriptionName = subscriptionName;
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public void execute(int night) {
        subscriptionRepository.findActiveByName(subscriptionName).ifPresent(Subscription::cancel);
    }
}
