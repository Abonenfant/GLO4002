package ca.ulaval.glo4002.inn.application;

import java.util.List;

import ca.ulaval.glo4002.inn.domain.Pantry;
import ca.ulaval.glo4002.inn.domain.food.FoodReserve;
import jakarta.inject.Inject;

public class PantryService {
    private final Pantry pantry;

    @Inject
    public PantryService(Pantry pantry) {
        this.pantry = pantry;
    }

    public List<FoodReserve> findReserves() {
        return pantry.getReserves();
    }
}
