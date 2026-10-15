package ca.ulaval.glo4002.inn.domain.food;

public enum FoodType {
    PLANT(40, 3),
    MEAT(30, 4),
    MINERAL(100, 20);

    private final int maxCapacity;
    private final int shelfLife;

    FoodType(int maxCapacity, int shelfLife) {
        this.maxCapacity = maxCapacity;
        this.shelfLife = shelfLife;
    }

    public int maxCapacity() {
        return maxCapacity;
    }

    public int shelfLife() {
        return shelfLife;
    }
}
