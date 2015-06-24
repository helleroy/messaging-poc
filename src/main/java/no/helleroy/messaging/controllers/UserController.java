package no.helleroy.messaging.controllers;

import no.helleroy.messaging.domain.User;
import no.helleroy.messaging.registries.UserRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserRegistry userRegistry;

    @Autowired
    public UserController(UserRegistry userRegistry) {
        this.userRegistry = userRegistry;
    }

    @RequestMapping
    public Map<String, User> users() {
        return userRegistry.users;
    }
}

