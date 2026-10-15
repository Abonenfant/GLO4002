package ca.ulaval.glo4002.inn.domain.order;

import java.util.List;

public interface OrderRepository {
    void save(Order order);

    List<Order> findAllInExecutionOrder();
}
