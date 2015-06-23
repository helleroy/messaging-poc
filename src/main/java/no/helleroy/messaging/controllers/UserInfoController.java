package no.helleroy.messaging.controllers;

import no.helleroy.messaging.components.UserRegistry;
import no.helleroy.messaging.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserInfoController {

    private UserRegistry userRegistry;

    @Autowired
    public UserInfoController(UserRegistry userRegistry) {
        this.userRegistry = userRegistry;
    }

    @RequestMapping
    public Map<String, User> users() {
        return userRegistry.users;
    }
}

