package ca.ulaval.glo4002.inn.domain.action;

import ca.ulaval.glo4002.inn.domain.room.Room;
import ca.ulaval.glo4002.inn.domain.room.RoomConfiguration;
import ca.ulaval.glo4002.inn.domain.room.RoomNumber;
import ca.ulaval.glo4002.inn.domain.room.RoomOperation;
import ca.ulaval.glo4002.inn.domain.room.RoomRejection;
import ca.ulaval.glo4002.inn.domain.room.RoomRejectionReason;
import ca.ulaval.glo4002.inn.domain.room.RoomRejectionRepository;
import ca.ulaval.glo4002.inn.domain.room.RoomRepository;

public class SetupRoomAction extends Action {

    private final RoomRepository roomRepository;
    private final RoomRejectionRepository roomRejectionRepository;
    private final RoomNumber roomNumber;
    private final RoomConfiguration configuration;
    private final int capacity;

    public SetupRoomAction(RoomRepository roomRepository,
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
        if (roomRepository.findByNumber(roomNumber).isPresent()) {
            roomRejectionRepository.save(new RoomRejection(
                    roomNumber,
                    RoomOperation.SETUP,
                    night,
                    RoomRejectionReason.ROOM_NUMBER_ALREADY_USED
            ));
            return;
        }

        roomRepository.save(new Room(roomNumber, configuration, capacity));
    }
}
