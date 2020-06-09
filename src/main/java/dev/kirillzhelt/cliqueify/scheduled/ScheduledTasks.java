package dev.kirillzhelt.cliqueify.scheduled;

import dev.kirillzhelt.cliqueify.service.RoomsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private final RoomsService roomsService;

    public ScheduledTasks(RoomsService roomsService) {
        this.roomsService = roomsService;
    }

    @Scheduled(cron = "0 46 13 * * ?")
    public void reportCurrentTime() {
        log.info("Starting deleting expired days...");
        this.roomsService.deleteExpiredRoomsForToday();
        log.info("Deleted all expired days for today");
    }
}
