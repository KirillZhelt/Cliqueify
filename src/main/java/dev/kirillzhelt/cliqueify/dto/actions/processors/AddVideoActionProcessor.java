package dev.kirillzhelt.cliqueify.dto.actions.processors;

import dev.kirillzhelt.cliqueify.dto.actions.AddVideoLiveRoom;
import dev.kirillzhelt.cliqueify.model.Room;
import dev.kirillzhelt.cliqueify.service.RoomService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class AddVideoActionProcessor implements LiveRoomActionProcessor {

    private final AddVideoLiveRoom addVideoAction;

    public AddVideoActionProcessor(AddVideoLiveRoom addVideoAction) {
        this.addVideoAction = addVideoAction;
    }

    @Override
    public void process(RoomService roomService, long roomId) {
        roomService.addVideoToPlaylist(roomId, this.addVideoAction.getVideoId());
    }
}
