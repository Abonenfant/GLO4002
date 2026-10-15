package ca.ulaval.glo4002.inn.interfaces.rest.dto.requests;

import ca.ulaval.glo4002.inn.domain.room.Atmosphere;
import ca.ulaval.glo4002.inn.domain.room.Gravity;
import ca.ulaval.glo4002.inn.domain.room.Temperature;

public record ReconfigureRoomRequest(
        Atmosphere atmosphere,
        Gravity gravity,
        Temperature temperature,
        int capacity
) {
}
