package dev.kirillzhelt.cliqueify.scheduled;

import dev.kirillzhelt.cliqueify.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private final RoomService roomService;

    public ScheduledTasks(RoomService roomService) {
        this.roomService = roomService;
    }

    @Scheduled(cron = "0 46 13 * * ?")
    public void reportCurrentTime() {
        log.info("Starting deleting expired days...");
        this.roomService.deleteExpiredRoomsForToday();
        log.info("Deleted all expired days for today");
    }
}
