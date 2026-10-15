package ca.ulaval.glo4002.inn.interfaces.rest.dto.responses;

import java.util.List;

import ca.ulaval.glo4002.inn.domain.room.Atmosphere;
import ca.ulaval.glo4002.inn.domain.room.Gravity;
import ca.ulaval.glo4002.inn.domain.room.Temperature;

public record RoomResponse(
        int roomNumber,
        Atmosphere atmosphere,
        Gravity gravity,
        Temperature temperature,
        int capacity,
        int freePlaces,
        List<OccupantResponse> occupants) {
}
