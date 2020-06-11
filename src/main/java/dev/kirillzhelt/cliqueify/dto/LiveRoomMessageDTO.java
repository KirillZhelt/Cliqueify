package dev.kirillzhelt.cliqueify.dto;

public class LiveRoomMessageDTO {

    private String username;
    private LiveRoomActionType action;

    public LiveRoomMessageDTO(String username, LiveRoomActionType action) {
        this.username = username;
        this.action = action;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LiveRoomActionType getAction() {
        return action;
    }

    public void setAction(LiveRoomActionType action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "LiveRoomMessage{" +
                "username='" + username + '\'' +
                ", action=" + action +
                '}';
    }
}
