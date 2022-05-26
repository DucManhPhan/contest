package com.nstep.dao.repository.impl;

import com.nstep.dao.entity.GameConfiguration;
import com.nstep.dao.repository.GameConfigurationRepository;
import com.nstep.domain.SnakeLadderGameException;
import com.nstep.utils.Constants;

import java.io.*;
import java.util.Properties;

public class GameConfigurationFileRepositoryImpl implements GameConfigurationRepository {

    @Override
    public GameConfiguration getGameConfigurationInfo(String path) {
        GameConfiguration gameConfiguration = new GameConfiguration();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        try (InputStream inputStream = loader.getResourceAsStream(path)) {
            Properties prop = new Properties();
            prop.load(inputStream);

            int boardSquareNum = Integer.parseInt(prop.getProperty("board-square-num"));
            int snakeNum = Integer.parseInt(prop.getProperty("snake-num"));
            int ladderNum = Integer.parseInt(prop.getProperty("ladder-num"));

            gameConfiguration.setBoardSquareNum(boardSquareNum);
            gameConfiguration.setSnakeNum(snakeNum);
            gameConfiguration.setLadderNum(ladderNum);
        } catch (IOException ex) {
            System.out.println("Exception: " + ex);
            throw new SnakeLadderGameException(Constants.NOT_GET_GAME_CONFIGURATION);
        }

        return gameConfiguration;
    }

}
