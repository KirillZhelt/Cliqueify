package dev.kirillzhelt.cliqueify.service;

import dev.kirillzhelt.cliqueify.model.Room;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public interface RoomService {

    Room createRoom(Room room);
    Room updateRoom(Room room);
    Optional<Room> getRoomById(long id);
    Optional<Room> getRoomByToken(String token);
    Set<Room> getPublicRooms();
    void deleteExpiredRoomsForToday();

}
