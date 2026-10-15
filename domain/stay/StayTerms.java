package ca.ulaval.glo4002.inn.domain.stay;

public record StayTerms(TravellerProfile travellerProfile, StayPackage stayPackage, int nights) {
    public StayTerms {
        if (nights <= 0) {
            throw new IllegalArgumentException("nights must be greater than zero");
        }
    }
}
