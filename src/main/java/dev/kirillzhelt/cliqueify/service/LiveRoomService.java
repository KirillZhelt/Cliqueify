package dev.kirillzhelt.cliqueify.service;

import dev.kirillzhelt.cliqueify.dto.actions.LiveRoomAction;

public interface LiveRoomService {

    void processAction(LiveRoomAction action, long roomId);

}
