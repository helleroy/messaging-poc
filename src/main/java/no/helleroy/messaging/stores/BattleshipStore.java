package no.helleroy.messaging.stores;

import no.helleroy.messaging.actionconsumers.BattleshipActionConsumer;
import no.helleroy.messaging.domain.BattleshipMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BattleshipStore extends Store<BattleshipMessage> {

    @Autowired
    public BattleshipStore(BattleshipActionConsumer battleshipActionConsumer) {
        subscribe(battleshipActionConsumer);
    }
}
