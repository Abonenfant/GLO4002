package ca.ulaval.glo4002.inn.application;

import ca.ulaval.glo4002.inn.domain.ExistenceNumber;
import ca.ulaval.glo4002.inn.domain.Inn;
import ca.ulaval.glo4002.inn.domain.action.BookStayAction;
import ca.ulaval.glo4002.inn.domain.room.RoomAssignmentPolicy;
import ca.ulaval.glo4002.inn.domain.room.RoomDirectory;
import ca.ulaval.glo4002.inn.domain.stay.StayFactory;
import ca.ulaval.glo4002.inn.domain.stay.StayPackage;
import ca.ulaval.glo4002.inn.domain.stay.StayTerms;
import ca.ulaval.glo4002.inn.domain.stay.TravellerProfile;
import ca.ulaval.glo4002.inn.domain.stay.TravellerRepository;
import jakarta.inject.Inject;

public class StayService {
    private final Inn inn;
    private final TravellerRepository travellerRepository;
    private final StayFactory stayFactory;
    private final RoomAssignmentPolicy roomAssignmentPolicy;
    private final RoomDirectory roomDirectory;

    @Inject
    public StayService(Inn inn, TravellerRepository travellerRepository, StayFactory stayFactory,
                       RoomAssignmentPolicy roomAssignmentPolicy, RoomDirectory roomDirectory) {
        this.inn = inn;
        this.travellerRepository = travellerRepository;
        this.stayFactory = stayFactory;
        this.roomAssignmentPolicy = roomAssignmentPolicy;
        this.roomDirectory = roomDirectory;
    }

    public void bookStay(ExistenceNumber existenceNumber, TravellerProfile travellerProfile,
                        StayPackage stayPackage, int nights) {
        inn.addActionToQueue(new BookStayAction(
                existenceNumber,
                travellerProfile,
                stayPackage,
                nights,
                travellerRepository,
                stayFactory,
                roomAssignmentPolicy,
                roomDirectory
        ));
    }

    public void bookStay(ExistenceNumber existenceNumber, StayTerms stayTerms) {
        bookStay(existenceNumber, stayTerms.travellerProfile(), stayTerms.stayPackage(), stayTerms.nights());
    }
}
