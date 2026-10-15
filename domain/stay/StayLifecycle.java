package ca.ulaval.glo4002.inn.domain.stay;

import ca.ulaval.glo4002.inn.domain.room.RoomDirectory;
import ca.ulaval.glo4002.inn.domain.room.RoomOccupant;

public class StayLifecycle {
    private final TravellerRepository travellerRepository;
    private final RoomDirectory roomDirectory;

    public StayLifecycle(TravellerRepository travellerRepository, RoomDirectory roomDirectory) {
        this.travellerRepository = travellerRepository;
        this.roomDirectory = roomDirectory;
    }

    public void arriveTravellers(int night) {
        travellerRepository.findAll().stream()
                .filter(traveller -> traveller.isArrivingOn(night))
                .forEach(traveller -> arrive(traveller, night));
    }

    private void arrive(Traveller traveller, int night) {
        traveller.arrive(night);
        RoomOccupant occupant =
                new RoomOccupant(traveller.getExistenceNumber(), traveller.getProfileOn(night).name());
        roomDirectory.occupyReservedPlace(traveller.getRoomNumberOn(night), occupant);
        travellerRepository.save(traveller);
    }

    public void departTravellers(int night) {
        travellerRepository.findAll().stream()
                .filter(traveller -> traveller.isDepartingOn(night))
                .forEach(traveller -> depart(traveller, night));
    }

    private void depart(Traveller traveller, int night) {
        traveller.depart(night);
        roomDirectory.releasePlace(traveller.getRoomNumberOn(night), traveller.getExistenceNumber());
        travellerRepository.save(traveller);
    }

    public void billTravellers(int night) {
        travellerRepository.findAll().stream()
                .filter(traveller -> traveller.hasSpentNight(night))
                .forEach(traveller -> bill(traveller, night));
    }

    private void bill(Traveller traveller, int night) {
        traveller.billNight(night);
        travellerRepository.save(traveller);
    }
}
