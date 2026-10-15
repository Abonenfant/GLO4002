package ca.ulaval.glo4002.inn.domain.stay;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import ca.ulaval.glo4002.inn.domain.room.RoomNumber;

public final class Stay {
    private static final int NIGHTS_TO_REACH_INN = 1;

    private final int reservedAtNight;
    private final StayTerms terms;
    private final RoomNumber roomNumber;
    private final StayRefusalReason refusalReason;
    private final Set<Integer> billedNights = new HashSet<>();
    private StayStatus status;
    private Money totalBilledAmount;

    Stay(
            int reservedAtNight,
            StayTerms terms,
            StayStatus status,
            RoomNumber roomNumber,
            StayRefusalReason refusalReason) {
        this.reservedAtNight = reservedAtNight;
        this.terms = terms;
        this.status = status;
        this.roomNumber = roomNumber;
        this.refusalReason = refusalReason;
        this.totalBilledAmount = Money.ZERO;
    }

    public TravellerProfile getTravellerProfile() {
        return terms.travellerProfile();
    }

    public StayPackage getStayPackage() {
        return terms.stayPackage();
    }

    public int getNights() {
        return terms.nights();
    }

    public int getReservedAtNight() {
        return reservedAtNight;
    }

    public StayStatus getStatus() {
        return status;
    }

    public Optional<RoomNumber> getRoomNumber() {
        return Optional.ofNullable(roomNumber);
    }

    public Optional<StayRefusalReason> getRefusalReason() {
        return Optional.ofNullable(refusalReason);
    }

    public Optional<Integer> getFirstNight() {
        return unlessRefused(firstNight());
    }

    public Optional<Integer> getLastNight() {
        return unlessRefused(lastNight());
    }

    public Money getTotalBilledAmount() {
        return totalBilledAmount;
    }

    boolean isBookedOrActive() {
        return status == StayStatus.BOOKED || status == StayStatus.ACTIVE;
    }

    boolean startsOn(int night) {
        return status == StayStatus.BOOKED && night == firstNight();
    }

    void activate() {
        status = StayStatus.ACTIVE;
    }

    boolean endsOn(int night) {
        return status == StayStatus.ACTIVE && night == lastNight();
    }

    void end() {
        status = StayStatus.ENDED;
    }

    boolean hasSpentNight(int night) {
        return hasArrived() && includesNight(night);
    }

    boolean isBillableOn(int night) {
        return hasSpentNight(night) && !billedNights.contains(night);
    }

    void billNight(int night) {
        billedNights.add(night);
        totalBilledAmount = totalBilledAmount.add(terms.stayPackage().nightlyRate());
    }

    boolean includesNight(int night) {
        if (isRefused()) {
            return false;
        }
        return firstNight() <= night && night <= lastNight();
    }

    private boolean hasArrived() {
        return status == StayStatus.ACTIVE || status == StayStatus.ENDED;
    }

    private Optional<Integer> unlessRefused(int night) {
        if (isRefused()) {
            return Optional.empty();
        }
        return Optional.of(night);
    }

    private boolean isRefused() {
        return status == StayStatus.REFUSED;
    }

    private int firstNight() {
        return reservedAtNight + NIGHTS_TO_REACH_INN;
    }

    private int lastNight() {
        int nightsAfterFirstNight = terms.nights() - 1;
        return firstNight() + nightsAfterFirstNight;
    }
}
