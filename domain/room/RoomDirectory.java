package ca.ulaval.glo4002.inn.domain.room;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import ca.ulaval.glo4002.inn.domain.ExistenceNumber;

public class RoomDirectory {
    private final RoomRepository roomRepository;

    public RoomDirectory(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> findCompatibleRoomsWithAvailablePlace(RoomConfiguration configuration) {
        return roomRepository.findAll().stream()
                .filter(room -> room.isCompatibleWith(configuration))
                .filter(Room::hasAvailablePlace)
                .toList();
    }

    public Optional<Room> findByNumber(RoomNumber roomNumber) {
        return roomRepository.findByNumber(roomNumber);
    }

    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    public void reservePlace(RoomNumber roomNumber, ExistenceNumber existenceNumber) {
        findExistingRoom(roomNumber).reservePlace(existenceNumber);
    }

    public void occupyReservedPlace(RoomNumber roomNumber, RoomOccupant occupant) {
        findExistingRoom(roomNumber).occupyReservedPlace(occupant);
    }

    public void releasePlace(RoomNumber roomNumber, ExistenceNumber existenceNumber) {
        findExistingRoom(roomNumber).releasePlace(existenceNumber);
    }

    public Room findExistingRoom(RoomNumber roomNumber) {
        return roomRepository.findByNumber(roomNumber)
                .orElseThrow(() -> new NoSuchElementException("room not found: " + roomNumber.value()));
    }
}
