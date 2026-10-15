package ca.ulaval.glo4002.inn.interfaces.rest.dto.responses;

import java.util.List;

public record ConstraintValidationResponse(String message, List<String> validations) {
}
