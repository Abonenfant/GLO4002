package ca.ulaval.glo4002.inn.domain.food;

import java.util.Objects;

public final class FoodBatch {
    private final int quantity;
    private final int stockedAtNight;
    private final Integer discardedAtNight;

    public FoodBatch(int quantity, int stockedAtNight, Integer discardedAtNight) {
        this.quantity = quantity;
        this.stockedAtNight = stockedAtNight;
        this.discardedAtNight = discardedAtNight;
    }

    public int getPortionCount() {
        return quantity;
    }

    public int getStockingNight() {
        return stockedAtNight;
    }

    /**
     * Returns the actual discard night, or null if the batch has not been discarded.
     * Integer is used instead of int to represent this absence.
     */
    public Integer getActualDiscardNight() {
        return discardedAtNight;
    }

    public boolean isExpiredOnNight(int night, int shelfLife) {
        return night >= (long) stockedAtNight + shelfLife;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FoodBatch batch)) {
            return false;
        }
        return quantity == batch.quantity
            && stockedAtNight == batch.stockedAtNight
            && Objects.equals(discardedAtNight, batch.discardedAtNight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity, stockedAtNight, discardedAtNight);
    }

    @Override
    public String toString() {
        return "FoodBatch[quantity=" + quantity + ", stockedAtNight=" + stockedAtNight
            + ", discardedAtNight=" + discardedAtNight + "]";
    }
}
