package ca.ulaval.glo4002.inn.interfaces.rest.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public record NightNumberResponse(@JsonProperty(value = "number", required = true) int nightNumber) {
}
