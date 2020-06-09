package dev.kirillzhelt.cliqueify.service;

import dev.kirillzhelt.cliqueify.model.PublicityType;
import dev.kirillzhelt.cliqueify.model.Room;
import dev.kirillzhelt.cliqueify.repository.RoomRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Key;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository repository;
    private final Environment environment;

    public RoomServiceImpl(RoomRepository roomRepository, Environment environment) {
        this.repository = roomRepository;
        this.environment = environment;
    }

    @Override
    public Room createRoom(Room room) {
        Room roomFromDb = this.repository.save(room);
        this.generateLinkToken(roomFromDb);
        return this.repository.save(roomFromDb);
    }

    @Override
    public Optional<Room> getRoomById(long id) {
        return this.repository.findById(id);
    }

    @Override
    public Optional<Room> getRoomByToken(String token) {
        Key key = Keys.hmacShaKeyFor(environment.getProperty("secret.jwt-key").getBytes());
        String idStr = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
        return this.getRoomById(Integer.parseInt(idStr));
    }

    @Override
    public Set<Room> getPublicRooms() {
        return this.repository.findAllByPublicityType(PublicityType.PUBLIC);
    }

    @Override
    @Transactional
    public void deleteExpiredRoomsForToday() {
        this.repository.deleteAllByExpiryDateEquals(LocalDate.now());
    }

    private void generateLinkToken(Room room) {
        Key key = Keys.hmacShaKeyFor(environment.getProperty("secret.jwt-key").getBytes());
        String token = Jwts.builder().setSubject(room.getId().toString()).signWith(key).compact();
        room.setLinkToken(token);
    }

}
