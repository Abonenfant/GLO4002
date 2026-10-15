package ca.ulaval.glo4002.inn.infrastructure.persistence;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import ca.ulaval.glo4002.inn.domain.ExistenceNumber;
import ca.ulaval.glo4002.inn.domain.stay.Traveller;
import ca.ulaval.glo4002.inn.domain.stay.TravellerRepository;

public class InMemoryTravellerRepository implements TravellerRepository {
    private final Map<ExistenceNumber, Traveller> travellers = new LinkedHashMap<>();

    @Override
    public void save(Traveller traveller) {
        travellers.put(traveller.getExistenceNumber(), traveller);
    }

    @Override
    public Optional<Traveller> findByExistenceNumber(ExistenceNumber existenceNumber) {
        return Optional.ofNullable(travellers.get(existenceNumber));
    }

    @Override
    public List<Traveller> findAll() {
        return List.copyOf(travellers.values());
    }
}
