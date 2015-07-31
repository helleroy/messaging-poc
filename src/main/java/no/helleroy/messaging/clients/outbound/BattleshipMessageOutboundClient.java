package no.helleroy.messaging.clients.outbound;

import no.helleroy.messaging.domain.BattleshipMessage;
import no.helleroy.messaging.flux.Publisher;
import no.helleroy.messaging.services.BattleshipService;
import no.helleroy.messaging.stores.BattleshipStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BattleshipMessageOutboundClient extends OutboundClient<BattleshipMessage> {

    private BattleshipService battleshipService;

    @Autowired
    public BattleshipMessageOutboundClient(BattleshipStore battleshipStore, BattleshipService battleshipService) {
        this.battleshipService = battleshipService;
        subscribe(battleshipStore);
    }

    @Override
    public void publish(BattleshipMessage message) {
        battleshipService.sendMessage(message.getReceiver(), message);
    }

    @Override
    public void subscribe(Publisher<BattleshipMessage> publisher) {
        publisher.register(this::publish);
    }
}
