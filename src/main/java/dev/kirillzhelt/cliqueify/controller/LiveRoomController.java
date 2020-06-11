package dev.kirillzhelt.cliqueify.controller;

import dev.kirillzhelt.cliqueify.dto.LiveRoomMessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class LiveRoomController {

    private static final Logger log = LoggerFactory.getLogger(LiveRoomController.class);

    @MessageMapping("/live-room/{roomId}")
    @SendTo("/topic/{roomId}")
    public LiveRoomMessageDTO sendAction(@DestinationVariable("roomId") int roomId, LiveRoomMessageDTO message) {
        log.info("room: {}; username: {}; action: {};", roomId, message.getUsername(), message.getAction());
        return message;
    }

}
