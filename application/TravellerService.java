package ca.ulaval.glo4002.inn.application;

import java.util.Optional;

import ca.ulaval.glo4002.inn.domain.ExistenceNumber;
import ca.ulaval.glo4002.inn.domain.stay.Traveller;
import ca.ulaval.glo4002.inn.domain.stay.TravellerRepository;
import jakarta.inject.Inject;

public class TravellerService {
    private final TravellerRepository travellerRepository;

    @Inject
    public TravellerService(TravellerRepository travellerRepository) {
        this.travellerRepository = travellerRepository;
    }

    public Optional<Traveller> findTraveller(ExistenceNumber existenceNumber) {
        return travellerRepository.findByExistenceNumber(existenceNumber);
    }
}
