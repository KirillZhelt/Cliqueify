package dev.kirillzhelt.cliqueify.dto.actions.processors;

import dev.kirillzhelt.cliqueify.dto.actions.PlayLiveRoom;
import dev.kirillzhelt.cliqueify.model.CurrentVideo;
import dev.kirillzhelt.cliqueify.model.PlayingState;
import dev.kirillzhelt.cliqueify.model.Room;
import dev.kirillzhelt.cliqueify.model.VideoState;
import dev.kirillzhelt.cliqueify.repository.RoomRepository;
import dev.kirillzhelt.cliqueify.service.RoomService;

import java.util.Optional;

public class PlayActionProcessor implements LiveRoomActionProcessor {

    private final PlayLiveRoom playAction;

    public PlayActionProcessor(PlayLiveRoom playAction) {
        this.playAction = playAction;
    }

    @Override
    public void process(RoomService roomService, long roomId) {
        Optional<Room> roomOptional = roomService.getRoomById(roomId);

        roomOptional.ifPresent(room -> {
            VideoState state = new PlayingState(this.playAction.getStartedToPlayTime(), this.playAction.getElapsedTime());
            room.getCurrentVideo().setState(state);

            roomService.updateRoom(room);
        });
    }
}
