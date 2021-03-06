package no.helleroy.messaging.actionconsumers;

import no.helleroy.messaging.clients.inbound.BattleshipController;
import no.helleroy.messaging.domain.BattleshipMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BattleshipActionConsumer extends ActionConsumer<BattleshipMessage> {

    @Autowired
    public BattleshipActionConsumer(BattleshipController battleshipController) {
        subscribe(battleshipController);
    }
}
