package ca.ulaval.glo4002.inn.infrastructure.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import ca.ulaval.glo4002.inn.domain.subscription.Subscription;
import ca.ulaval.glo4002.inn.domain.subscription.SubscriptionRepository;

public class InMemorySubscriptionRepository implements SubscriptionRepository {
    private final List<Subscription> subscriptions = new ArrayList<>();

    @Override
    public void save(Subscription subscription) {
        subscriptions.add(subscription);
    }

    @Override
    public List<Subscription> findAllInExecutionOrder() {
        return List.copyOf(subscriptions);
    }

    @Override
    public Optional<Subscription> findActiveByName(String name) {
        return subscriptions.stream()
                .filter(Subscription::isActive)
                .filter(subscription -> subscription.getName().equals(name))
                .findFirst();
    }
}
