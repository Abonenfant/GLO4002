package ca.ulaval.glo4002.inn.domain.stay;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import ca.ulaval.glo4002.inn.domain.ExistenceNumber;
import ca.ulaval.glo4002.inn.domain.room.RoomNumber;

public final class Traveller {
    private final ExistenceNumber existenceNumber;
    private final List<Stay> stays = new ArrayList<>();

    public Traveller(ExistenceNumber existenceNumber, Stay firstStay) {
        this.existenceNumber = existenceNumber;
        stays.add(firstStay);
    }

    public ExistenceNumber getExistenceNumber() {
        return existenceNumber;
    }

    public TravellerProfile getProfile() {
        return stays.get(stays.size() - 1).getTravellerProfile();
    }

    public TravellerProfile getProfileOn(int night) {
        return findStayMatching(stay -> stay.includesNight(night), night)
                .getTravellerProfile();
    }

    public List<Stay> getStays() {
        return List.copyOf(stays);
    }

    public boolean isAlreadyStaying() {
        return hasStay(Stay::isBookedOrActive);
    }

    public void addStay(Stay stay) {
        boolean wouldHaveTwoStaysAtOnce = stay.isBookedOrActive() && isAlreadyStaying();
        if (wouldHaveTwoStaysAtOnce) {
            throw new IllegalStateException("traveller is already staying");
        }
        stays.add(stay);
    }

    public boolean isArrivingOn(int night) {
        return hasStay(stay -> stay.startsOn(night));
    }

    public void arrive(int night) {
        findStayMatching(stay -> stay.startsOn(night), night).activate();
    }

    public boolean isDepartingOn(int night) {
        return hasStay(stay -> stay.endsOn(night));
    }

    public void depart(int night) {
        findStayMatching(stay -> stay.endsOn(night), night).end();
    }

    public boolean hasSpentNight(int night) {
        return hasStay(stay -> stay.hasSpentNight(night));
    }

    public void billNight(int night) {
        findStayMatching(stay -> stay.isBillableOn(night), night).billNight(night);
    }

    public RoomNumber getRoomNumberOn(int night) {
        return findStayMatching(stay -> stay.includesNight(night), night).getRoomNumber().orElseThrow();
    }

    private boolean hasStay(Predicate<Stay> condition) {
        return stays.stream().anyMatch(condition);
    }

    private Stay findStayMatching(Predicate<Stay> condition, int night) {
        return stays.stream()
                .filter(condition)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("no stay of traveller matches night " + night));
    }

    public Stay getLatestStay() {
        return stays.get(stays.size() - 1);
    }
}
