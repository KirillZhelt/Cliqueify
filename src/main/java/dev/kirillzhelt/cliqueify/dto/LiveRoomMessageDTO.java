package dev.kirillzhelt.cliqueify.dto;

import dev.kirillzhelt.cliqueify.dto.actions.LiveRoomAction;

public class LiveRoomMessageDTO {

    private String username;
    private LiveRoomAction action;

    public LiveRoomMessageDTO(String username, LiveRoomAction action) {
        this.username = username;
        this.action = action;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LiveRoomAction getAction() {
        return action;
    }

    public void setAction(LiveRoomAction action) {
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
