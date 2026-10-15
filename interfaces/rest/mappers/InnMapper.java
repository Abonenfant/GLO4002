package ca.ulaval.glo4002.inn.interfaces.rest.mappers;

import ca.ulaval.glo4002.inn.interfaces.rest.dto.responses.NightNumberResponse;

public class InnMapper {

    public NightNumberResponse toDto(int currentNight) {
        return new NightNumberResponse(currentNight);
    }

}
