package ca.ulaval.glo4002.inn.application;

import java.util.List;

import ca.ulaval.glo4002.inn.domain.Inn;
import ca.ulaval.glo4002.inn.domain.action.SubscribeAction;
import ca.ulaval.glo4002.inn.domain.action.UnsubscribeAction;
import ca.ulaval.glo4002.inn.domain.subscription.Subscription;
import ca.ulaval.glo4002.inn.domain.subscription.SubscriptionFactory;
import ca.ulaval.glo4002.inn.domain.subscription.SubscriptionRepository;
import ca.ulaval.glo4002.inn.domain.subscription.SubscriptionTerms;
import jakarta.inject.Inject;

public class SubscriptionService {
    private final Inn inn;
    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionFactory subscriptionFactory;

    @Inject
    public SubscriptionService(Inn inn, SubscriptionRepository subscriptionRepository, SubscriptionFactory subscriptionFactory) {
        this.inn = inn;
        this.subscriptionRepository = subscriptionRepository;
        this.subscriptionFactory = subscriptionFactory;
    }

    public void subscribe(SubscriptionTerms terms) {
        inn.addActionToQueue(new SubscribeAction(terms, subscriptionRepository, subscriptionFactory));
    }

    public void unsubscribe(String subscriptionName) {
        inn.addActionToQueue(new UnsubscribeAction(subscriptionName, subscriptionRepository));
    }

    public List<Subscription> getSubscriptions() {
        return subscriptionRepository.findAllInExecutionOrder();
    }

    public int getCurrentNight() {
        return inn.getCurrentNight();
    }
}
