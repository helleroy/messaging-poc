package no.helleroy.messaging.services;

import no.helleroy.messaging.domain.BattleshipMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

public class BattleshipServiceImpl implements BattleshipService {

    public static final String DESTINATION_BATTLESHIP = "/game/battleship";

    @Autowired
    private SimpMessageSendingOperations messageTemplate;

    @Override
    public void sendMessage(String username, BattleshipMessage battleshipMessage) {
        messageTemplate.convertAndSendToUser(username, DESTINATION_BATTLESHIP, battleshipMessage);
    }
}
