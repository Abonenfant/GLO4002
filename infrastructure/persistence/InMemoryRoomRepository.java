package ca.ulaval.glo4002.inn.infrastructure.persistence;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import ca.ulaval.glo4002.inn.domain.room.Room;
import ca.ulaval.glo4002.inn.domain.room.RoomNumber;
import ca.ulaval.glo4002.inn.domain.room.RoomRepository;

public class InMemoryRoomRepository implements RoomRepository {
    private final Map<RoomNumber, Room> rooms = new LinkedHashMap<>();

    @Override
    public void save(Room room) {
        rooms.put(room.getRoomNumber(), room);
    }

    @Override
    public Optional<Room> findByNumber(RoomNumber roomNumber) {
        return Optional.ofNullable(rooms.get(roomNumber));
    }

    @Override
    public List<Room> findAll() {
        return List.copyOf(rooms.values());
    }
}
