package ca.ulaval.glo4002.inn.domain.subscription;

import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository {
    void save(Subscription subscription);

    List<Subscription> findAllInExecutionOrder();

    Optional<Subscription> findActiveByName(String name);
}
