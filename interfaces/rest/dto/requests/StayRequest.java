package ca.ulaval.glo4002.inn.interfaces.rest.dto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import ca.ulaval.glo4002.inn.domain.stay.HomeDimension;
import ca.ulaval.glo4002.inn.domain.stay.Species;
import ca.ulaval.glo4002.inn.domain.stay.StayPackage;

public record StayRequest(
        String existenceNumber,
        String travellerName,
        Species species,
        int massKg,
        HomeDimension homeDimension,
        @JsonProperty("package") StayPackage stayPackage,
        int nights
) {
}
