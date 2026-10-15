package ca.ulaval.glo4002.inn.interfaces.rest.assemblers;

import ca.ulaval.glo4002.inn.domain.room.RoomRejection;
import ca.ulaval.glo4002.inn.interfaces.rest.dto.responses.RoomRejectionResponse;

public class RoomRejectionAssembler {
    public static RoomRejectionResponse toResponse(RoomRejection rejection) {
        return new RoomRejectionResponse(
                rejection.getRoomNumber().value(),
                rejection.getOperation(),
                rejection.getRejectedAtNight(),
                rejection.getReason());
    }
}
