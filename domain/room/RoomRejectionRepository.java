package ca.ulaval.glo4002.inn.domain.room;

import java.util.List;

public interface RoomRejectionRepository {
    void save(RoomRejection rejection);

    List<RoomRejection> findAllInExecutionOrder();
}
