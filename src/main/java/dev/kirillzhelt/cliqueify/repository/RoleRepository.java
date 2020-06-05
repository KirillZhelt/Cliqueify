package dev.kirillzhelt.cliqueify.repository;

import dev.kirillzhelt.cliqueify.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
