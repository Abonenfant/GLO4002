package ca.ulaval.glo4002.inn.domain.action;

import ca.ulaval.glo4002.inn.domain.food.FoodType;
import ca.ulaval.glo4002.inn.domain.order.OrderFactory;
import ca.ulaval.glo4002.inn.domain.order.OrderRepository;

public class OrderAction extends Action {
    private final FoodType foodType;
    private final int quantity;
    private final int deliveryDelay;
    private final OrderRepository orderRepository;
    private final OrderFactory orderFactory;

    public OrderAction(FoodType foodType, int quantity, int deliveryDelay, OrderRepository orderRepository, OrderFactory orderFactory) {
        this.foodType = foodType;
        this.quantity = quantity;
        this.deliveryDelay = deliveryDelay;
        this.orderRepository = orderRepository;
        this.orderFactory = orderFactory;
    }

    @Override
    public void execute(int night) {
        orderRepository.save(orderFactory.createPlacedOrder(night, foodType, quantity, deliveryDelay));
    }

}
