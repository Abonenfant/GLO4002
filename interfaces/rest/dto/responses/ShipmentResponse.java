package ca.ulaval.glo4002.inn.interfaces.rest.dto.responses;

public record ShipmentResponse(int quantity, int stockedAtNight, Integer discardedAtNight) {
}
