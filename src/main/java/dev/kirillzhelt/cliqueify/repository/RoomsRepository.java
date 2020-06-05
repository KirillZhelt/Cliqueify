package dev.kirillzhelt.cliqueify.repository;

import dev.kirillzhelt.cliqueify.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomsRepository extends JpaRepository<Room, Long> {
}
