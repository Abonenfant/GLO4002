package ca.ulaval.glo4002.inn.domain.room;

import java.util.Objects;

public final class RoomRejection {
    private final RoomNumber roomNumber;
    private final RoomOperation operation;
    private final int rejectedAtNight;
    private final RoomRejectionReason reason;

    public RoomRejection(
            RoomNumber roomNumber,
            RoomOperation operation,
            int rejectedAtNight,
            RoomRejectionReason reason) {
        this.roomNumber = roomNumber;
        this.operation = operation;
        this.rejectedAtNight = rejectedAtNight;
        this.reason = reason;
    }

    public RoomNumber getRoomNumber() {
        return roomNumber;
    }

    public RoomOperation getOperation() {
        return operation;
    }

    public int getRejectedAtNight() {
        return rejectedAtNight;
    }

    public RoomRejectionReason getReason() {
        return reason;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RoomRejection rejection)) {
            return false;
        }
        return roomNumber.equals(rejection.roomNumber)
                && operation == rejection.operation
                && rejectedAtNight == rejection.rejectedAtNight
                && reason == rejection.reason;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber, operation, rejectedAtNight, reason);
    }
}
