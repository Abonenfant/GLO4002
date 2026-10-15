package ca.ulaval.glo4002.inn.interfaces.rest.assemblers;

import ca.ulaval.glo4002.inn.domain.stay.StayPackage;
import ca.ulaval.glo4002.inn.domain.stay.StayTerms;
import ca.ulaval.glo4002.inn.domain.stay.TravellerProfile;
import ca.ulaval.glo4002.inn.interfaces.rest.dto.requests.StayRequest;

public class StayAssembler {
    public static TravellerProfile toTravellerProfile(StayRequest request) {
        return new TravellerProfile(
                request.travellerName(),
                request.species(),
                request.massKg(),
                request.homeDimension()
        );
    }

    public static StayTerms toStayTerms(StayRequest request) {
        return new StayTerms(toTravellerProfile(request), request.stayPackage(), request.nights());
    }

    public static StayTerms toStayTerms(TravellerProfile travellerProfile, StayPackage stayPackage, int nights) {
        return new StayTerms(travellerProfile, stayPackage, nights);
    }
}
