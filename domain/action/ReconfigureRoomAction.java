package ca.ulaval.glo4002.inn.domain.action;

import java.util.Optional;

import ca.ulaval.glo4002.inn.domain.room.Room;
import ca.ulaval.glo4002.inn.domain.room.RoomConfiguration;
import ca.ulaval.glo4002.inn.domain.room.RoomNumber;
import ca.ulaval.glo4002.inn.domain.room.RoomOperation;
import ca.ulaval.glo4002.inn.domain.room.RoomRejection;
import ca.ulaval.glo4002.inn.domain.room.RoomRejectionReason;
import ca.ulaval.glo4002.inn.domain.room.RoomRejectionRepository;
import ca.ulaval.glo4002.inn.domain.room.RoomRepository;

public class ReconfigureRoomAction extends Action {
    private final RoomRepository roomRepository;
    private final RoomRejectionRepository roomRejectionRepository;
    private final RoomNumber roomNumber;
    private final RoomConfiguration configuration;
    private final int capacity;

    public ReconfigureRoomAction(
            RoomRepository roomRepository,
            RoomRejectionRepository roomRejectionRepository,
            RoomNumber roomNumber,
            RoomConfiguration configuration,
            int capacity) {
        this.roomRepository = roomRepository;
        this.roomRejectionRepository = roomRejectionRepository;
        this.roomNumber = roomNumber;
        this.configuration = configuration;
        this.capacity = capacity;
    }

    @Override
    public void execute(int night) {
        Optional<Room> room = roomRepository.findByNumber(roomNumber);

        if (room.isEmpty()) {
            saveRejection(night, RoomRejectionReason.ROOM_NOT_FOUND);
            return;
        }

        Room existingRoom = room.get();
        if (!existingRoom.isEmpty()) {
            saveRejection(night, RoomRejectionReason.ROOM_NOT_EMPTY);
            return;
        }

        existingRoom.reconfigure(configuration, capacity);
        roomRepository.save(existingRoom);
    }

    private void saveRejection(int night, RoomRejectionReason reason) {
        roomRejectionRepository.save(
                new RoomRejection(
                        roomNumber,
                        RoomOperation.RECONFIGURE,
                        night,
                        reason
                )
        );
    }
}
