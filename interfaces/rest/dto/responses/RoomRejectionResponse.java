package ca.ulaval.glo4002.inn.interfaces.rest.dto.responses;

import ca.ulaval.glo4002.inn.domain.room.RoomOperation;
import ca.ulaval.glo4002.inn.domain.room.RoomRejectionReason;

public record RoomRejectionResponse(
        int roomNumber,
        RoomOperation operation,
        int rejectedAtNight,
        RoomRejectionReason reason) {
}
