package dev.kirillzhelt.cliqueify.dto.actions;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import dev.kirillzhelt.cliqueify.dto.actions.processors.LiveRoomActionProcessor;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = EnterLiveRoom.class, name = "ENTER"),
        @JsonSubTypes.Type(value = LeaveLiveRoom.class, name = "LEAVE"),
        @JsonSubTypes.Type(value = PlayLiveRoom.class, name = "PLAY"),
        @JsonSubTypes.Type(value = PauseLiveRoom.class, name = "PAUSE"),
        @JsonSubTypes.Type(value = LoadLiveRoom.class, name = "LOAD"),
})
public interface LiveRoomAction {

    LiveRoomActionProcessor buildProcessor();

}
