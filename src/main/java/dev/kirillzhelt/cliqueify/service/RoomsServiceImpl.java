package dev.kirillzhelt.cliqueify.service;

import dev.kirillzhelt.cliqueify.model.Room;
import dev.kirillzhelt.cliqueify.repository.RoomsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomsServiceImpl implements RoomsService {

    private final RoomsRepository repository;

    public RoomsServiceImpl(RoomsRepository roomsRepository) {
        this.repository = roomsRepository;
    }

    public Room createRoom(Room room) {
        return this.repository.save(room);
    }

    public Optional<Room> getRoomById(long id) {
        return this.repository.findById(id);
    }

}
