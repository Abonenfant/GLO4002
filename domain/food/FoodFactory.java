package ca.ulaval.glo4002.inn.domain.food;

public class FoodFactory {
    public FoodBatch createStockedBatch(int quantity, int stockedAtNight) {
        return new FoodBatch(quantity, stockedAtNight, null);
    }

    public FoodBatch createDiscardedBatch(FoodBatch batch, int discardedAtNight) {
        return new FoodBatch(batch.getPortionCount(), batch.getStockingNight(), discardedAtNight);
    }
}
