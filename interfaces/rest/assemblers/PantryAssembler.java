package ca.ulaval.glo4002.inn.interfaces.rest.assemblers;

import java.util.List;

import ca.ulaval.glo4002.inn.domain.food.FoodBatch;
import ca.ulaval.glo4002.inn.domain.food.FoodReserve;
import ca.ulaval.glo4002.inn.interfaces.rest.dto.responses.PantryResponse;
import ca.ulaval.glo4002.inn.interfaces.rest.dto.responses.ReserveResponse;
import ca.ulaval.glo4002.inn.interfaces.rest.dto.responses.ShipmentResponse;

public class PantryAssembler {
    public static PantryResponse toResponse(List<FoodReserve> reserves) {
        return new PantryResponse(reserves.stream()
                .map(PantryAssembler::toReserveResponse)
                .toList());
    }

    private static ReserveResponse toReserveResponse(FoodReserve reserve) {
        return new ReserveResponse(
                reserve.type(),
                reserve.maxCapacity(),
                reserve.stock(),
                reserve.shipments().stream()
                        .map(PantryAssembler::toShipmentResponse)
                        .toList(),
                reserve.totalDiscarded(),
                reserve.totalOverflowed());
    }

    private static ShipmentResponse toShipmentResponse(FoodBatch batch) {
        return new ShipmentResponse(
                batch.getPortionCount(),
                batch.getStockingNight(),
                batch.getActualDiscardNight());
    }
}
