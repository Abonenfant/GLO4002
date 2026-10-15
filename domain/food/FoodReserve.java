package ca.ulaval.glo4002.inn.domain.food;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public final class FoodReserve {
    private final FoodType type;
    private final FoodFactory foodFactory;
    private final List<FoodBatch> stockedBatches = new ArrayList<>();
    private final List<FoodBatch> discardedBatches = new ArrayList<>();
    private int totalOverflowed;
    private int totalDiscarded;

    public FoodReserve(FoodType type, FoodFactory foodFactory) {
        this.type = type;
        this.foodFactory = foodFactory;
    }

    public FoodType type() {
        return type;
    }

    public int maxCapacity() {
        return type.maxCapacity();
    }

    public int shelfLife() {
        return type.shelfLife();
    }

    public int stock() {
        return stockedBatches.stream().mapToInt(FoodBatch::getPortionCount).sum();
    }

    public List<FoodBatch> stockedBatches() {
        return List.copyOf(stockedBatches);
    }

    public List<FoodBatch> discardedBatches() {
        return List.copyOf(discardedBatches);
    }

    public List<FoodBatch> shipments() {
        return Stream.concat(discardedBatches.stream(), stockedBatches.stream())
                .sorted(Comparator.comparingInt(FoodBatch::getStockingNight))
                .toList();
    }

    public int totalOverflowed() {
        return totalOverflowed;
    }

    public int totalDiscarded() {
        return totalDiscarded;
    }

    public void store(int quantity, int stockedAtNight) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Delivered quantity must not be negative");
        }

        int storedQuantity = Math.min(quantity, maxCapacity() - stock());
        totalOverflowed += quantity - storedQuantity;
        if (storedQuantity > 0) {
            stockedBatches.add(foodFactory.createStockedBatch(storedQuantity, stockedAtNight));
        }
    }

    public void discardExpiredAt(int night) {
        Iterator<FoodBatch> batches = stockedBatches.iterator();
        while (batches.hasNext()) {
            FoodBatch batch = batches.next();
            if (batch.isExpiredOnNight(night, shelfLife())) {
                discardedBatches.add(foodFactory.createDiscardedBatch(batch, night));
                totalDiscarded += batch.getPortionCount();
                batches.remove();
            }
        }
    }
}
