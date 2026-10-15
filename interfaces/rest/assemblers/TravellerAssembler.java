package ca.ulaval.glo4002.inn.interfaces.rest.assemblers;

import ca.ulaval.glo4002.inn.domain.room.RoomNumber;
import ca.ulaval.glo4002.inn.domain.stay.Stay;
import ca.ulaval.glo4002.inn.domain.stay.Traveller;
import ca.ulaval.glo4002.inn.domain.stay.TravellerProfile;
import ca.ulaval.glo4002.inn.interfaces.rest.dto.responses.ErrorResponse;
import ca.ulaval.glo4002.inn.interfaces.rest.dto.responses.StayResponse;
import ca.ulaval.glo4002.inn.interfaces.rest.dto.responses.TravellerResponse;

public class TravellerAssembler {
    private static final String TRAVELLER_NOT_FOUND = "TRAVELLER_NOT_FOUND";

    public static TravellerResponse toResponse(Traveller traveller) {
        TravellerProfile profile = traveller.getProfile();
        return new TravellerResponse(
                traveller.getExistenceNumber().value(),
                profile.name(),
                profile.species(),
                profile.massKg(),
                profile.homeDimension(),
                traveller.getStays().stream().map(TravellerAssembler::toStayResponse).toList());
    }

    public static ErrorResponse toNotFoundResponse(String existenceNumber) {
        return new ErrorResponse(TRAVELLER_NOT_FOUND, "traveller with existence number " + existenceNumber + " not found");
    }

    private static StayResponse toStayResponse(Stay stay) {
        return new StayResponse(
                stay.getStatus(),
                stay.getReservedAtNight(),
                stay.getStayPackage(),
                stay.getNights(),
                stay.getRoomNumber().map(RoomNumber::value).orElse(null),
                stay.getFirstNight().orElse(null),
                stay.getLastNight().orElse(null),
                stay.getRefusalReason().orElse(null),
                stay.getTotalBilledAmount().getAmount());
    }
}
