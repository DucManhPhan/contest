package com.nstep.application.service.impl;

import com.nstep.application.service.*;
import com.nstep.application.service.testing.LadderTestServiceImpl;
import com.nstep.application.service.testing.PlayerTestServiceImpl;
import com.nstep.application.service.testing.SnakeTestServiceImpl;
import com.nstep.dao.repository.GameConfigurationRepository;
import com.nstep.dao.repository.impl.GameConfigurationFileRepositoryImpl;
import com.nstep.utils.Constants;

public class FactoryService {

    private static final PlayerService playerService = new PlayerServiceImpl();

    private static final PlayerService playerTestService = new PlayerTestServiceImpl();

    private static final SnakeService snakeService = new SnakeServiceImpl();

    private static final SnakeService snakeTestService = new SnakeTestServiceImpl();

    private static final LadderService ladderService = new LadderServiceImpl();

    private static final LadderService ladderTestService = new LadderTestServiceImpl();

    private static final RollDiceService rollDiceService = new RandomRollDiceServiceImpl();

    private static final GameConfigurationRepository gameConfigRepository = new GameConfigurationFileRepositoryImpl();

    private static final GameConfigurationService gameConfigService = new GameConfigurationServiceImpl();

    private static final RandomService randomService = new RandomServiceImpl();

    private static SnakeAndLadderGameServiceImpl snakeLadderGameService;

    public static PlayerService getPlayerService(String envType) {
        switch (envType) {
            case Constants.PROD_ENV:
                return playerService;

            case Constants.UAT_ENV:
                return playerTestService;

            default:
                throw new IllegalArgumentException();
        }
    }

    public static SnakeService getSnakeService(String envType) {
        switch (envType) {
            case Constants.PROD_ENV:
                return snakeService;

            case Constants.UAT_ENV:
                return snakeTestService;

            default:
                throw new IllegalArgumentException();
        }
    }

    public static LadderService getLadderService(String envType) {
        switch (envType) {
            case Constants.PROD_ENV:
                return ladderService;

            case Constants.UAT_ENV:
                return ladderTestService;

            default:
                throw new IllegalArgumentException();
        }
    }

    public static RandomService getRandomService() {
        return randomService;
    }

    public static RollDiceService getRollDiceService() {
        return rollDiceService;
    }

    public static GameConfigurationRepository getGameConfigRepository(String configType) {
        switch (configType) {
            case Constants.FILE_CONFIG_TYPE:
                return gameConfigRepository;

            default:
                throw new IllegalArgumentException();
        }
    }

    public static GameConfigurationService getGameConfigService() {
        return gameConfigService;
    }

    public static SnakeAndLadderGameServiceImpl getSnakeLadderGameService(String envType, int boardSize) {
        if (snakeLadderGameService == null) {
            snakeLadderGameService = new SnakeAndLadderGameServiceImpl(envType, Constants.DEFAULT_BOARD_SIZE);
        }

        return snakeLadderGameService;
    }

}
