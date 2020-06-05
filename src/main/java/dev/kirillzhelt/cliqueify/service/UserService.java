package dev.kirillzhelt.cliqueify.service;

import dev.kirillzhelt.cliqueify.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService {

    boolean saveUser(User user);

}
