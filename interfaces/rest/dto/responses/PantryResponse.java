package ca.ulaval.glo4002.inn.interfaces.rest.dto.responses;

import java.util.List;

public record PantryResponse(List<ReserveResponse> reserves) {
}
