package dev.kirillzhelt.cliqueify.dto.actions;

import dev.kirillzhelt.cliqueify.dto.actions.processors.LiveRoomActionProcessor;

public class EnterLiveRoom implements LiveRoomAction {
    @Override
    public LiveRoomActionProcessor buildProcessor() {
        return LiveRoomActionProcessor.BLANK;
    }
}
