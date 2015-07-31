package no.helleroy.messaging.clients.inbound;

import no.helleroy.messaging.domain.BattleshipMessage;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class BattleshipController extends InboundClient<BattleshipMessage> {

    @MessageMapping("/game/battleship/{username}")
    public void message(@DestinationVariable String username, BattleshipMessage battleshipMessage, Principal principal) {
        publish((BattleshipMessage) battleshipMessage.setSender(principal.getName()).setReceiver(username));
    }

}

