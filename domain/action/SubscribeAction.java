package ca.ulaval.glo4002.inn.domain.action;

import ca.ulaval.glo4002.inn.domain.subscription.Subscription;
import ca.ulaval.glo4002.inn.domain.subscription.SubscriptionFactory;
import ca.ulaval.glo4002.inn.domain.subscription.SubscriptionRefusalReason;
import ca.ulaval.glo4002.inn.domain.subscription.SubscriptionRepository;
import ca.ulaval.glo4002.inn.domain.subscription.SubscriptionTerms;

public class SubscribeAction extends Action {
    private final SubscriptionTerms terms;
    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionFactory subscriptionFactory;

    public SubscribeAction(SubscriptionTerms terms, SubscriptionRepository subscriptionRepository,
                           SubscriptionFactory subscriptionFactory) {
        this.terms = terms;
        this.subscriptionRepository = subscriptionRepository;
        this.subscriptionFactory = subscriptionFactory;
    }

    @Override
    public void execute(int night) {
        subscriptionRepository.save(createSubscription(night));
    }

    private Subscription createSubscription(int night) {
        if (isNameUsedByActiveSubscription()) {
            return subscriptionFactory.createRefusedSubscription(night, terms, SubscriptionRefusalReason.SUBSCRIPTION_NAME_ALREADY_USED);
        }
        return subscriptionFactory.createActiveSubscription(night, terms);
    }

    private boolean isNameUsedByActiveSubscription() {
        return subscriptionRepository.findActiveByName(terms.name()).isPresent();
    }
}
