package ca.ulaval.glo4002.inn.domain.room;

import java.util.Comparator;
import java.util.Optional;

import ca.ulaval.glo4002.inn.domain.stay.Species;

public class RoomAssignmentPolicy {
    public RoomConfiguration requiredConfigurationFor(Species species) {
        return species.requiredConfiguration();
    }

    public boolean isCompatibleWith(Room room, Species species) {
        return room.isCompatibleWith(requiredConfigurationFor(species));
    }

    public Optional<Room> findBestAvailableRoom(RoomDirectory roomDirectory, Species species) {
        return roomDirectory.findCompatibleRoomsWithAvailablePlace(requiredConfigurationFor(species)).stream()
                .min(Comparator.comparingInt(Room::getFreePlaces)
                        .thenComparingInt(room -> room.getRoomNumber().value()));
    }
}
