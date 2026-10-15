package ca.ulaval.glo4002.inn.domain.stay;

import ca.ulaval.glo4002.inn.domain.room.RoomNumber;

public class StayFactory {
    public Stay createBookedStay(int executionNight, StayTerms terms, RoomNumber roomNumber) {
        return new Stay(executionNight, terms, StayStatus.BOOKED, roomNumber, null);
    }

    public Stay createRefusedStay(int executionNight, StayTerms terms, StayRefusalReason refusalReason) {
        return new Stay(executionNight, terms, StayStatus.REFUSED, null, refusalReason);
    }
}
