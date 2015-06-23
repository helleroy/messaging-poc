package no.helleroy.messaging.components;

import no.helleroy.messaging.domain.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserRegistry {
    public final Map<String, User> users = new HashMap<>();
}
