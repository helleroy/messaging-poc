package no.helleroy.messaging.controllers;

import no.helleroy.messaging.domain.ChatMessage;
import no.helleroy.messaging.stores.ChatStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private ChatStore chatStore;

    @Autowired
    public MessageController(ChatStore chatStore) {
        this.chatStore = chatStore;
    }

    @RequestMapping
    public List<ChatMessage> messages(Principal principal) {
        return chatStore.getMessages()
                .stream()
                .filter(m -> m.getReceiver() == null ||
                        m.getReceiver().equals(principal.getName()) ||
                        m.getSender().equals(principal.getName()))
                .collect(toList());
    }
}
