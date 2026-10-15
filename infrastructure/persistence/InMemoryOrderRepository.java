package ca.ulaval.glo4002.inn.infrastructure.persistence;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.inn.domain.order.Order;
import ca.ulaval.glo4002.inn.domain.order.OrderRepository;

public class InMemoryOrderRepository implements OrderRepository {
    private final List<Order> orders = new ArrayList<>();

    @Override
    public void save(Order order) {
        orders.add(order);
    }

    @Override
    public List<Order> findAllInExecutionOrder() {
        return List.copyOf(orders);
    }
}
