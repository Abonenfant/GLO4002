package ca.ulaval.glo4002.inn.domain.room;

import java.util.List;
import java.util.Optional;

public interface RoomRepository {
    void save(Room room);

    Optional<Room> findByNumber(RoomNumber roomNumber);

    List<Room> findAll();
}
