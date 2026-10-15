package ca.ulaval.glo4002.inn.domain.room;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import ca.ulaval.glo4002.inn.domain.ExistenceNumber;

public class Room {
    private final RoomNumber roomNumber;
    private RoomConfiguration configuration;
    private int capacity;
    private final Set<ExistenceNumber> reservedExistenceNumbers = new LinkedHashSet<>();
    private final Map<ExistenceNumber, RoomOccupant> occupantsByExistenceNumber =
            new TreeMap<>(Comparator.comparing(ExistenceNumber::value));

    public Room(RoomNumber roomNumber, RoomConfiguration configuration, int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("room capacity must be greater than zero");
        }
        this.roomNumber = roomNumber;
        this.configuration = configuration;
        this.capacity = capacity;
    }

    public RoomNumber getRoomNumber() {
        return roomNumber;
    }

    public RoomConfiguration getConfiguration() {
        return configuration;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getFreePlaces() {
        return capacity - reservedExistenceNumbers.size() - occupantsByExistenceNumber.size();
    }

    public boolean isCompatibleWith(RoomConfiguration expectedConfiguration) {
        return configuration.equals(expectedConfiguration);
    }

    public boolean hasAvailablePlace() {
        return getFreePlaces() > 0;
    }

    public boolean isEmpty() {
        return reservedExistenceNumbers.isEmpty() && occupantsByExistenceNumber.isEmpty();
    }

    public void reconfigure(RoomConfiguration configuration, int capacity) {
        if (!isEmpty()) {
            throw new IllegalStateException("room must be empty to be reconfigured");
        }
        if (capacity <= 0) {
            throw new IllegalArgumentException("room capacity must be greater than zero");
        }

        this.configuration = configuration;
        this.capacity = capacity;
    }

    public void reservePlace(ExistenceNumber existenceNumber) {
        if (!hasAvailablePlace()) {
            throw new IllegalStateException("room has no available place");
        }
        if (hasPlaceFor(existenceNumber)) {
            throw new IllegalStateException("traveller already has a place in room");
        }
        reservedExistenceNumbers.add(existenceNumber);
    }

    public void occupyReservedPlace(RoomOccupant occupant) {
        ExistenceNumber existenceNumber = occupant.getExistenceNumber();
        if (!reservedExistenceNumbers.remove(existenceNumber)) {
            throw new IllegalStateException("traveller has no reserved place in room");
        }
        occupantsByExistenceNumber.put(existenceNumber, occupant);
    }

    public void releasePlace(ExistenceNumber existenceNumber) {
        if (!hasPlaceFor(existenceNumber)) {
            throw new IllegalStateException("traveller has no place in room");
        }
        reservedExistenceNumbers.remove(existenceNumber);
        occupantsByExistenceNumber.remove(existenceNumber);
    }

    public List<ExistenceNumber> getReservedExistenceNumbers() {
        return List.copyOf(reservedExistenceNumbers);
    }

    public List<RoomOccupant> getOccupants() {
        return List.copyOf(occupantsByExistenceNumber.values());
    }

    private boolean hasPlaceFor(ExistenceNumber existenceNumber) {
        return reservedExistenceNumbers.contains(existenceNumber)
                || occupantsByExistenceNumber.containsKey(existenceNumber);
    }
}
