package ca.ulaval.glo4002.inn.domain;

public record ExistenceNumber(String value) {
    public ExistenceNumber {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("existence number must not be blank");
        }
    }
}
