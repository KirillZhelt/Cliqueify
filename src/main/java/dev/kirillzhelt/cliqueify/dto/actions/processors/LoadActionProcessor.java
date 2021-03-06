package dev.kirillzhelt.cliqueify.dto.actions.processors;

import dev.kirillzhelt.cliqueify.dto.actions.LoadLiveRoom;
import dev.kirillzhelt.cliqueify.model.CurrentVideo;
import dev.kirillzhelt.cliqueify.model.PlayingState;
import dev.kirillzhelt.cliqueify.model.Room;
import dev.kirillzhelt.cliqueify.model.VideoState;
import dev.kirillzhelt.cliqueify.repository.RoomRepository;
import dev.kirillzhelt.cliqueify.service.RoomService;

import java.util.Optional;

public class LoadActionProcessor implements LiveRoomActionProcessor {

    private final LoadLiveRoom loadAction;

    public LoadActionProcessor(LoadLiveRoom loadAction) {
        this.loadAction = loadAction;
    }

    @Override
    public void process(RoomService roomService, long roomId) {
        Optional<Room> roomOptional = roomService.getRoomById(roomId);
        if (roomOptional.isPresent()) {
            Room room = roomOptional.get();

            VideoState state = new PlayingState(this.loadAction.getStartedToPlayTime(), 0);
            room.setCurrentVideo(new CurrentVideo(this.loadAction.getVideoId(), state));

            roomService.updateRoom(room);
        }
    }
}
