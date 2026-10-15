package ca.ulaval.glo4002.inn.application;

import java.util.Comparator;
import java.util.List;

import ca.ulaval.glo4002.inn.domain.Inn;
import ca.ulaval.glo4002.inn.domain.action.ReconfigureRoomAction;
import ca.ulaval.glo4002.inn.domain.action.SetupRoomAction;
import ca.ulaval.glo4002.inn.domain.room.*;
import jakarta.inject.Inject;

public class RoomService {

    private final Inn inn;
    private final RoomRepository roomRepository;
    private final RoomRejectionRepository roomRejectionRepository;

    @Inject
    public RoomService(Inn inn, RoomRepository roomRepository, RoomRejectionRepository roomRejectionRepository) {
        this.inn = inn;
        this.roomRepository = roomRepository;
        this.roomRejectionRepository = roomRejectionRepository;
    }

    public void requestRoomSetup(int roomNumber,
                                 Atmosphere atmosphere,
                                 Gravity gravity,
                                 Temperature temperature,
                                 int capacity) {

        RoomNumber number = new RoomNumber(roomNumber);
        RoomConfiguration configuration = new RoomConfiguration(atmosphere, gravity, temperature);

        SetupRoomAction action = new SetupRoomAction(
                roomRepository,
                roomRejectionRepository,
                number,
                configuration,
                capacity
        );

        inn.addActionToQueue(action);
    }

    public void requestRoomReconfiguration(
            int roomNumber,
            Atmosphere atmosphere,
            Gravity gravity,
            Temperature temperature,
            int capacity) {
        RoomNumber number = new RoomNumber(roomNumber);
        RoomConfiguration configuration =
                new RoomConfiguration(atmosphere, gravity, temperature);

        ReconfigureRoomAction action =
                new ReconfigureRoomAction(
                        roomRepository,
                        roomRejectionRepository,
                        number,
                        configuration,
                        capacity
                );

        inn.addActionToQueue(action);
    }

    public List<Room> findAllRooms() {
        return roomRepository.findAll().stream()
                .sorted(Comparator.comparingInt(room -> room.getRoomNumber().value()))
                .toList();
    }

    public List<RoomRejection> findAllRejections() {
        return roomRejectionRepository.findAllInExecutionOrder();
    }

    public int getCurrentNight() {
        return inn.getCurrentNight();
    }
}
