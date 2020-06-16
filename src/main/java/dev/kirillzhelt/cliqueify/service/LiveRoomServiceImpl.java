package dev.kirillzhelt.cliqueify.service;

import dev.kirillzhelt.cliqueify.dto.actions.LiveRoomAction;
import dev.kirillzhelt.cliqueify.dto.actions.processors.LiveRoomActionProcessor;
import org.springframework.stereotype.Service;

@Service
public class LiveRoomServiceImpl implements LiveRoomService {

    private final RoomService roomService;

    public LiveRoomServiceImpl(RoomService roomService) {
        this.roomService = roomService;
    }

    @Override
    public void processAction(LiveRoomAction action, long roomId) {
        LiveRoomActionProcessor processor = action.buildProcessor();
        processor.process(this.roomService, roomId);
    }
}
