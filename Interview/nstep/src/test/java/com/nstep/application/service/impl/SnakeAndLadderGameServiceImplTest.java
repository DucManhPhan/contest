package com.nstep.application.service.impl;

import com.nstep.application.service.SnakeAndLadderGameService;
import com.nstep.domain.SnakeLadderGameException;
import com.nstep.utils.Constants;
import org.junit.Test;

public class SnakeAndLadderGameServiceImplTest {

    private SnakeAndLadderGameService snakeAndLadderGameService;

    @Test(expected = SnakeLadderGameException.class)
    public void givenEmptyBoardSize() {
        this.snakeAndLadderGameService = FactoryService.getSnakeLadderGameService(Constants.UAT_ENV, Constants.DEFAULT_BOARD_SIZE);
        this.snakeAndLadderGameService.startGame();
    }

    @Test(expected = SnakeLadderGameException.class)
    public void givenEmptyPlayers() {
        this.snakeAndLadderGameService = FactoryService.getSnakeLadderGameService(Constants.UAT_ENV, Constants.DEFAULT_BOARD_SIZE);
        this.snakeAndLadderGameService.startGame();
    }

    @Test(expected = SnakeLadderGameException.class)
    public void givenEmptySnakes() {
        this.snakeAndLadderGameService = FactoryService.getSnakeLadderGameService(Constants.UAT_ENV, Constants.DEFAULT_BOARD_SIZE);
        this.snakeAndLadderGameService.startGame();
    }

    @Test(expected = SnakeLadderGameException.class)
    public void givenEmptyLadders() {
        this.snakeAndLadderGameService = FactoryService.getSnakeLadderGameService(Constants.UAT_ENV, Constants.DEFAULT_BOARD_SIZE);
        this.snakeAndLadderGameService.startGame();
    }

}