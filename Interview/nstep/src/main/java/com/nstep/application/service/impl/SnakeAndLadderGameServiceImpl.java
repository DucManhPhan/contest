package com.nstep.application.service.impl;

import com.nstep.application.service.SnakeAndLadderGameService;
import com.nstep.domain.Ladder;
import com.nstep.domain.Player;
import com.nstep.domain.Snake;
import com.nstep.domain.SnakeLadderGameException;
import com.nstep.utils.Constants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SnakeAndLadderGameServiceImpl implements SnakeAndLadderGameService {

    private final List<Player> players;

    private final List<Snake> snakes;

    private final List<Ladder> ladders;

    private Map<String, Integer> oldPositionPerPlayer;

    private final int boardSize;

    public SnakeAndLadderGameServiceImpl(String envType, int boardSize) {
        this.boardSize = boardSize;
        this.players = FactoryService.getPlayerService(envType).initializePlayers();
        this.snakes = FactoryService.getSnakeService(envType).initializeSnakes();
        this.ladders = FactoryService.getLadderService(envType).initializeLadders();

        this.initializePositionForPlayer();
    }

    public SnakeAndLadderGameServiceImpl() {
        this(Constants.PROD_ENV, Constants.DEFAULT_BOARD_SIZE);
    }

    public void startGame() {
        validateSnakeLadderGame();

        int diceValue = 0;
        while (true){
            for (Player currentPlayer : this.players) {
                diceValue = FactoryService.getRollDiceService().rollDice();

                int oldPosition = this.oldPositionPerPlayer.get(currentPlayer.getUuid());
                int newPosition = this.calculateNewPosition(currentPlayer, oldPosition, diceValue);

                if (this.canWin(newPosition)) {
                    System.out.println(currentPlayer.toString() + " won this game");
                    return;
                }
            }
        }
    }

    private int calculateNewPosition(Player currentPlayer, int oldPosition, int diceValue) {
        int newPosition = oldPosition + diceValue;

        if (newPosition > this.boardSize) {
            newPosition = newPosition - diceValue;
            return newPosition;
        }

        // check whether the player can be swallowed by Snake
        for (Snake snake : this.snakes) {
            if (snake.getHeadPosition() == newPosition) {
                System.out.println(currentPlayer.toString() + " was swallowed by snake, then go to " + snake.getTailPosition());
                newPosition = snake.getTailPosition();
            }
        }

        // check whether the player can use ladder to go to the end of ladder
        for (Ladder ladder : ladders) {
            if (ladder.getStartPosition() == newPosition) {
                System.out.println(currentPlayer.toString() + " can use ladder to go to " + ladder.getEndPosition());
                newPosition = ladder.getEndPosition();
            }
        }

        this.oldPositionPerPlayer.put(currentPlayer.getUuid(), newPosition);
        return newPosition;
    }

    private void validateSnakeLadderGame() {
        if (this.boardSize == 0) {
            throw new SnakeLadderGameException("Setting board size wrong");
        }

        if (this.players == null || this.players.isEmpty()) {
            throw new SnakeLadderGameException(Constants.NONE_NUMBER_PLAYERS);
        }

        if (this.snakes == null || this.snakes.isEmpty()) {
            throw new SnakeLadderGameException(Constants.NONE_NUMBER_SNAKE);
        }

        if (this.ladders == null || this.ladders.isEmpty()) {
            throw new SnakeLadderGameException(Constants.NONE_NUMBER_LADDER);
        }
    }

    private boolean canWin(int newPosition) {
        return this.boardSize == newPosition;
    }

    private void initializePositionForPlayer() {
        this.oldPositionPerPlayer = new HashMap<>();

        for (Player player : this.players) {
            this.oldPositionPerPlayer.put(player.getUuid(), 0);
        }
    }

}
