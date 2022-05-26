package com.nstep.application.service.impl;

import com.nstep.application.service.PlayerService;
import com.nstep.domain.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PlayerServiceImpl implements PlayerService {
    @Override
    public List<Player> initializePlayers() {
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Type the number of user: ");
            int numPlayers = Integer.parseInt(bf.readLine().trim());

            if (numPlayers <= 0) {
                throw new IllegalArgumentException();
            }

            List<Player> players = new ArrayList<>();
            for (int i = 0; i < numPlayers; ++i) {
                System.out.println("The name of player: ");
                String name = bf.readLine();
                Player player = new Player(name.trim());

                players.add(player);
            }

            return players;
        } catch (IOException ex) {
            System.out.println("Exception: " + ex);
        }

        throw new RuntimeException("User input the wrong data");
    }

}
