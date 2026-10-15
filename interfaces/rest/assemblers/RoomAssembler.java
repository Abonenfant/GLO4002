package ca.ulaval.glo4002.inn.interfaces.rest.assemblers;

import ca.ulaval.glo4002.inn.domain.room.Room;
import ca.ulaval.glo4002.inn.domain.room.RoomConfiguration;
import ca.ulaval.glo4002.inn.domain.room.RoomOccupant;
import ca.ulaval.glo4002.inn.interfaces.rest.dto.responses.OccupantResponse;
import ca.ulaval.glo4002.inn.interfaces.rest.dto.responses.RoomResponse;

public class RoomAssembler {
    public static RoomResponse toResponse(Room room) {
        RoomConfiguration configuration = room.getConfiguration();
        return new RoomResponse(
                room.getRoomNumber().value(),
                configuration.getAtmosphere(),
                configuration.getGravity(),
                configuration.getTemperature(),
                room.getCapacity(),
                room.getFreePlaces(),
                room.getOccupants().stream().map(RoomAssembler::toOccupantResponse).toList());
    }

    private static OccupantResponse toOccupantResponse(RoomOccupant occupant) {
        return new OccupantResponse(occupant.getExistenceNumber().value(), occupant.getTravellerName());
    }
}
