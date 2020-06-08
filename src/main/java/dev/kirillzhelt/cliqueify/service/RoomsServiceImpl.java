package dev.kirillzhelt.cliqueify.service;

import dev.kirillzhelt.cliqueify.model.PublicityType;
import dev.kirillzhelt.cliqueify.model.Room;
import dev.kirillzhelt.cliqueify.repository.RoomsRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Service
public class RoomsServiceImpl implements RoomsService {

    private final RoomsRepository repository;
    private final Environment environment;

    public RoomsServiceImpl(RoomsRepository roomsRepository, Environment environment) {
        this.repository = roomsRepository;
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

    private void generateLinkToken(Room room) {
        Key key = Keys.hmacShaKeyFor(environment.getProperty("secret.jwt-key").getBytes());
        String token = Jwts.builder().setSubject(room.getId().toString()).signWith(key).compact();
        room.setLinkToken(token);
    }

}
