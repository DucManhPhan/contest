package com.nstep.application.service.testing;

import com.nstep.application.service.PlayerService;
import com.nstep.domain.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerTestServiceImpl implements PlayerService {
    @Override
    public List<Player> initializePlayers() {
        return new ArrayList<>();
    }

}
