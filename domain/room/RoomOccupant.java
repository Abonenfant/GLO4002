package ca.ulaval.glo4002.inn.domain.room;

import java.util.Objects;

import ca.ulaval.glo4002.inn.domain.ExistenceNumber;

public final class RoomOccupant {
    private final ExistenceNumber existenceNumber;
    private final String travellerName;

    public RoomOccupant(ExistenceNumber existenceNumber, String travellerName) {
        this.existenceNumber = existenceNumber;
        this.travellerName = travellerName;
    }

    public ExistenceNumber getExistenceNumber() {
        return existenceNumber;
    }

    public String getTravellerName() {
        return travellerName;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RoomOccupant occupant)) {
            return false;
        }
        return existenceNumber.equals(occupant.existenceNumber)
                && travellerName.equals(occupant.travellerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(existenceNumber, travellerName);
    }
}

