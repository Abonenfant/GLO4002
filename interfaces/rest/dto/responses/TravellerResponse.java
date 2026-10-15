package ca.ulaval.glo4002.inn.interfaces.rest.dto.responses;

import java.util.List;

import ca.ulaval.glo4002.inn.domain.stay.HomeDimension;
import ca.ulaval.glo4002.inn.domain.stay.Species;

public record TravellerResponse(
        String existenceNumber,
        String travellerName,
        Species species,
        int massKg,
        HomeDimension homeDimension,
        List<StayResponse> stays
) {
}
