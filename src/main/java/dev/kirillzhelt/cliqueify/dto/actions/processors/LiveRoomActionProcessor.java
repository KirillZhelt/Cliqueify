package dev.kirillzhelt.cliqueify.dto.actions.processors;

import dev.kirillzhelt.cliqueify.repository.RoomRepository;
import dev.kirillzhelt.cliqueify.service.RoomService;

public interface LiveRoomActionProcessor {

    LiveRoomActionProcessor BLANK = new BlankActionProcessor();

    void process(RoomService roomService, long roomId);

}
