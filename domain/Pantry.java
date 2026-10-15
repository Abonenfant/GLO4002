package ca.ulaval.glo4002.inn.domain;

import java.util.EnumMap;
import java.util.List;

import ca.ulaval.glo4002.inn.domain.food.FoodFactory;
import ca.ulaval.glo4002.inn.domain.food.FoodReserve;
import ca.ulaval.glo4002.inn.domain.food.FoodSupply;
import ca.ulaval.glo4002.inn.domain.food.FoodType;

public class Pantry {
    private final EnumMap<FoodType, FoodReserve> reserves;

    public Pantry(FoodFactory foodFactory) {
        this.reserves = new EnumMap<>(FoodType.class);

        for (FoodType foodType : FoodType.values()) {
            reserves.put(foodType, new FoodReserve(foodType, foodFactory));
        }
    }

    public FoodReserve getReserve(FoodType foodType) {
        return reserves.get(foodType);
    }

    public List<FoodReserve> getReserves() {
        return List.copyOf(reserves.values());
    }

    public void applyGardConsequences(List<? extends FoodSupply> foodSupplies, int night) {
        storeDeliveries(foodSupplies, night);
        discardExpiredAt(night);
    }

    private void storeDeliveries(List<? extends FoodSupply> foodSupplies, int night) {
        for (FoodSupply foodSupply : foodSupplies) {
            if (foodSupply.isDeliveredOnNight(night)) {
                reserves.get(foodSupply.getFoodType()).store(foodSupply.getQuantity(), night);
            }
        }
    }

    private void discardExpiredAt(int night) {
        for (FoodReserve reserve : reserves.values()) {
            reserve.discardExpiredAt(night);
        }
    }
}
