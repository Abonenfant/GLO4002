package ca.ulaval.glo4002.inn.interfaces.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CurrentNight(@JsonProperty(value = "currentNight", required = true) int currentNight) {

}
