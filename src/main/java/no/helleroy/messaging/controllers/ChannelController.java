package no.helleroy.messaging.controllers;

import no.helleroy.messaging.domain.Channel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/channels")
public class ChannelController {

    @RequestMapping
    public List<Channel> channels() {
        ArrayList<Channel> channels = new ArrayList<>();
        channels.add(new Channel().setName("general").setIsPersonal(false));
        channels.add(new Channel().setName("random").setIsPersonal(false));
        return channels;
    }
}
