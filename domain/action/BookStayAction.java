package ca.ulaval.glo4002.inn.domain.action;

import ca.ulaval.glo4002.inn.domain.ExistenceNumber;
import ca.ulaval.glo4002.inn.domain.room.RoomAssignmentPolicy;
import ca.ulaval.glo4002.inn.domain.room.RoomDirectory;
import ca.ulaval.glo4002.inn.domain.stay.*;
import ca.ulaval.glo4002.inn.domain.stay.StayFactory;
import ca.ulaval.glo4002.inn.domain.stay.StayPackage;
import ca.ulaval.glo4002.inn.domain.stay.TravellerProfile;
import ca.ulaval.glo4002.inn.domain.stay.TravellerRepository;

public class BookStayAction extends Action {
    private final ExistenceNumber existenceNumber;
    private final TravellerProfile travellerProfile;
    private final StayPackage stayPackage;
    private final int nights;
    private final TravellerRepository travellerRepository;
    private final StayFactory stayFactory;
    private final RoomAssignmentPolicy roomAssignmentPolicy;
    private final RoomDirectory roomDirectory;

    public BookStayAction(ExistenceNumber existenceNumber, TravellerProfile travellerProfile,
                          StayPackage stayPackage, int nights, TravellerRepository travellerRepository,
                          StayFactory stayFactory, RoomAssignmentPolicy roomAssignmentPolicy, RoomDirectory roomDirectory) {
        this.existenceNumber = existenceNumber;
        this.travellerProfile = travellerProfile;
        this.stayPackage = stayPackage;
        this.nights = nights;
        this.travellerRepository = travellerRepository;
        this.stayFactory = stayFactory;
        this.roomAssignmentPolicy = roomAssignmentPolicy;
        this.roomDirectory = roomDirectory;
    }

    @Override
    public void execute(int night) {
        StayBooking stayBooking = new StayBooking(night, existenceNumber, travellerProfile, stayPackage, nights,
                travellerRepository, stayFactory, roomAssignmentPolicy, roomDirectory);
        stayBooking.bookStay();
    }
}
