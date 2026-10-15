package ca.ulaval.glo4002.inn.domain;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ca.ulaval.glo4002.inn.domain.action.Action;
import ca.ulaval.glo4002.inn.domain.food.FoodSupply;
import ca.ulaval.glo4002.inn.domain.order.OrderRepository;
import ca.ulaval.glo4002.inn.domain.stay.StayLifecycle;
import ca.ulaval.glo4002.inn.domain.subscription.SubscriptionRepository;

public class Inn {
    private final Pantry pantry;
    private final LinkedList<Action> pendingActions;
    private final OrderRepository orderRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final StayLifecycle stayLifecycle;
    private int currentNight;

    public Inn(
            OrderRepository orderRepository,
            SubscriptionRepository subscriptionRepository,
            Pantry pantry,
            StayLifecycle stayLifecycle) {
        this.currentNight = 0;
        this.pantry = pantry;
        this.pendingActions = new LinkedList<>();
        this.orderRepository = orderRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.stayLifecycle = stayLifecycle;
    }

    public int getCurrentNight() {
        return currentNight;
    }

    public Pantry getPantry() {
        return pantry;
    }

    public void addActionToQueue(Action action) {
        pendingActions.add(action);
    }

    public int getPendingActionQueueSize() {
        return this.pendingActions.size();
    }

    public Action getFirstInPendingActionQueue() {
        return this.pendingActions.getFirst();
    }

    public void executeNight() {
        while (!pendingActions.isEmpty()) {
            pendingActions.removeFirst().execute(currentNight);
        }
    }

    public int getPendingActionsQueueSize() {
        return this.pendingActions.size();
    }

    public void passNight() {
        this.currentNight++;
        executeNight();
        stayLifecycle.arriveTravellers(currentNight);
        pantry.applyGardConsequences(findAllFoodSupplies(), currentNight);
        stayLifecycle.departTravellers(currentNight);
        stayLifecycle.billTravellers(currentNight);
    }

    private List<FoodSupply> findAllFoodSupplies() {
        List<FoodSupply> foodSupplies =
                new ArrayList<>(orderRepository.findAllInExecutionOrder());
        foodSupplies.addAll(subscriptionRepository.findAllInExecutionOrder());
        return foodSupplies;
    }

}
