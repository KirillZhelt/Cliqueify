package dev.kirillzhelt.cliqueify.service;

import dev.kirillzhelt.cliqueify.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService extends UserDetailsService {

    boolean saveUser(User user);
    Optional<User> findUserByUsername(String username);

}
