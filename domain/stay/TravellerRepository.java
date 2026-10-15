package ca.ulaval.glo4002.inn.domain.stay;

import java.util.List;
import java.util.Optional;

import ca.ulaval.glo4002.inn.domain.ExistenceNumber;

public interface TravellerRepository {
    void save(Traveller traveller);

    Optional<Traveller> findByExistenceNumber(ExistenceNumber existenceNumber);

    List<Traveller> findAll();
}
