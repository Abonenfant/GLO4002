package ca.ulaval.glo4002.inn.domain.room;

public record RoomNumber(int value) {
    public RoomNumber {
        if (value <= 0) {
            throw new IllegalArgumentException("room number must be greater than zero");
        }
    }
}
