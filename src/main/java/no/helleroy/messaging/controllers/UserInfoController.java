package no.helleroy.messaging.controllers;

import no.helleroy.messaging.domain.UserLoginMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
public class UserInfoController {

    @Autowired
    private SessionRegistry sessionRegistry;

    @RequestMapping("/users")
    public List<UserLoginMessage> users() {
        return sessionRegistry.getAllPrincipals()
                .stream()
                .map(User.class::cast)
                .map(u -> new UserLoginMessage(u.getUsername(), true))
                .collect(toList());
    }
}

