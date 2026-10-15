package ca.ulaval.glo4002.inn.domain.stay;

import java.util.Optional;

import ca.ulaval.glo4002.inn.domain.ExistenceNumber;
import ca.ulaval.glo4002.inn.domain.room.Room;
import ca.ulaval.glo4002.inn.domain.room.RoomAssignmentPolicy;
import ca.ulaval.glo4002.inn.domain.room.RoomDirectory;
import ca.ulaval.glo4002.inn.domain.room.RoomNumber;

public class StayBooking {
    private final int executionNight;
    private final ExistenceNumber existenceNumber;
    private final TravellerProfile travellerProfile;
    private final StayPackage stayPackage;
    private final int nights;
    private final TravellerRepository travellerRepository;
    private final StayFactory stayFactory;
    private final RoomAssignmentPolicy roomAssignmentPolicy;
    private final RoomDirectory roomDirectory;

    public StayBooking(int executionNight, ExistenceNumber existenceNumber, TravellerProfile travellerProfile, StayPackage stayPackage,
                       int nights, TravellerRepository travellerRepository, StayFactory stayFactory,
                       RoomAssignmentPolicy roomAssignmentPolicy,
                       RoomDirectory roomDirectory) {
        this.executionNight = executionNight;
        this.existenceNumber = existenceNumber;
        this.travellerProfile = travellerProfile;
        this.stayPackage = stayPackage;
        this.nights = nights;
        this.travellerRepository = travellerRepository;
        this.stayFactory = stayFactory;
        this.roomAssignmentPolicy = roomAssignmentPolicy;
        this.roomDirectory = roomDirectory;
    }

    public void bookStay() {
        Traveller existingTraveller = travellerRepository.findByExistenceNumber(existenceNumber).orElse(null);
        StayTerms terms = new StayTerms(travellerProfile, stayPackage, nights);

        Stay stay = evaluateBooking(existingTraveller, terms);
        persistStay(existingTraveller, stay);
    }

    private Stay evaluateBooking(Traveller traveller, StayTerms terms) {
        if (traveller != null && traveller.isAlreadyStaying()) {
            return stayFactory.createRefusedStay(executionNight, terms, StayRefusalReason.ALREADY_STAYING);
        } else {
            Optional<Room> candidate = roomAssignmentPolicy.findBestAvailableRoom(roomDirectory, travellerProfile.species());
            if (candidate.isPresent()) {
                RoomNumber compatibleRoomNumber = candidate.get().getRoomNumber();
                roomDirectory.reservePlace(compatibleRoomNumber, existenceNumber);
                return stayFactory.createBookedStay(executionNight, terms, compatibleRoomNumber);
            } else {
                return stayFactory.createRefusedStay(executionNight, terms, StayRefusalReason.NO_COMPATIBLE_ROOM);
            }
        }
    }

    private void persistStay(Traveller traveller, Stay stay) {
        if (traveller == null) {
            travellerRepository.save(new Traveller(existenceNumber, stay));
        } else {
            traveller.addStay(stay);
            travellerRepository.save(traveller);
        }
    }

}
