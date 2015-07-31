package no.helleroy.messaging.services;

import no.helleroy.messaging.domain.BattleshipMessage;

public interface BattleshipService {
    void sendMessage(String username, BattleshipMessage battleshipMessage);
}
