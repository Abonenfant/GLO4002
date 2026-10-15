package ca.ulaval.glo4002.inn.interfaces.rest.dto.responses;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import ca.ulaval.glo4002.inn.domain.stay.StayPackage;
import ca.ulaval.glo4002.inn.domain.stay.StayRefusalReason;
import ca.ulaval.glo4002.inn.domain.stay.StayStatus;

public record StayResponse(
        StayStatus status,
        int reservedAtNight,
        @JsonProperty("package") StayPackage stayPackage,
        int nights,
        Integer roomNumber,
        Integer firstNight,
        Integer lastNight,
        StayRefusalReason refusalReason,
        BigDecimal totalBilledAmount
) {
}
