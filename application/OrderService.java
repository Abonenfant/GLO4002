package ca.ulaval.glo4002.inn.application;

import java.util.Comparator;
import java.util.List;

import ca.ulaval.glo4002.inn.domain.Inn;
import ca.ulaval.glo4002.inn.domain.action.OrderAction;
import ca.ulaval.glo4002.inn.domain.food.FoodType;
import ca.ulaval.glo4002.inn.domain.order.Order;
import ca.ulaval.glo4002.inn.domain.order.OrderFactory;
import ca.ulaval.glo4002.inn.domain.order.OrderRepository;

public class OrderService {
    private final Inn inn;
    private final OrderRepository orderRepository;
    private final OrderFactory orderFactory;

    public OrderService(Inn inn, OrderRepository orderRepository, OrderFactory orderFactory) {
        this.inn = inn;
        this.orderRepository = orderRepository;
        this.orderFactory = orderFactory;
    }

    public List<Order> findOrdersInTransit() {
        int currentNight = inn.getCurrentNight();

        return orderRepository.findAllInExecutionOrder().stream()
                .filter(order -> order.isInTransitOnNight(currentNight))
                .sorted(Comparator.comparing(Order::getDeliveryNight))
                .toList();
    }

    public void placeOrder(FoodType foodType, int quantity, int deliveryDelay) {
        inn.addActionToQueue(new OrderAction(foodType, quantity, deliveryDelay, orderRepository, orderFactory));
    }
}
