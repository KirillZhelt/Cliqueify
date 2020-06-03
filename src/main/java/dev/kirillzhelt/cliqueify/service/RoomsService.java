package dev.kirillzhelt.cliqueify.service;

import dev.kirillzhelt.cliqueify.model.Room;
import dev.kirillzhelt.cliqueify.repository.RoomsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public interface RoomsService {

    Room createRoom(Room room);
    Optional<Room> getRoomById(long id);

}
