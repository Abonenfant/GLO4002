package ca.ulaval.glo4002.inn.infrastructure.persistence;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.inn.domain.room.RoomRejection;
import ca.ulaval.glo4002.inn.domain.room.RoomRejectionRepository;

public class InMemoryRoomRejectionRepository implements RoomRejectionRepository {
    private final List<RoomRejection> rejections = new ArrayList<>();

    @Override
    public void save(RoomRejection rejection) {
        rejections.add(rejection);
    }

    @Override
    public List<RoomRejection> findAllInExecutionOrder() {
        return List.copyOf(rejections);
    }
}
