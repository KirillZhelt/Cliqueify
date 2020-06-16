package dev.kirillzhelt.cliqueify.dto.actions.processors;

import dev.kirillzhelt.cliqueify.dto.actions.PauseLiveRoom;
import dev.kirillzhelt.cliqueify.model.*;
import dev.kirillzhelt.cliqueify.repository.RoomRepository;
import dev.kirillzhelt.cliqueify.service.RoomService;

import java.util.Optional;

public class PauseActionProcessor implements LiveRoomActionProcessor {

    private final PauseLiveRoom pauseAction;

    public PauseActionProcessor(PauseLiveRoom pauseAction) {
        this.pauseAction = pauseAction;
    }

    @Override
    public void process(RoomService roomService, long roomId) {
        Optional<Room> roomOptional = roomService.getRoomById(roomId);

        roomOptional.ifPresent(room -> {
            VideoState state = new PausedState(this.pauseAction.getElapsedTimeWhenPaused());
            room.getCurrentVideo().setState(state);

            roomService.updateRoom(room);
        });
    }
}
