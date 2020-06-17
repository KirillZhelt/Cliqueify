package dev.kirillzhelt.cliqueify.dto.actions.processors;

import dev.kirillzhelt.cliqueify.repository.RoomRepository;
import dev.kirillzhelt.cliqueify.service.RoomService;

public class BlankActionProcessor implements LiveRoomActionProcessor {

    @Override
    public void process(RoomService roomService, long roomId) {

    }
}
