package com.nstep.application.service.impl;

import com.nstep.application.dto.SnakeLadderParameter;
import com.nstep.application.service.SnakeService;
import com.nstep.domain.Snake;
import com.nstep.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class SnakeServiceImpl implements SnakeService {

    @Override
    public List<Snake> initializeSnakes() {
        SnakeLadderParameter params = FactoryService.getGameConfigService()
                .getGameParamers();
        int snakeNum = params.getSnakeNum();
        List<Snake> snakes = new ArrayList<>();

        for (int i = 1; i <= snakeNum; ++i) {
            int start = FactoryService.getRandomService()
                    .randomValueBetween(Constants.MIN_NUMBER_IN_BOARD, Constants.MAX_NUMBER_IN_BOARD);
            int end = FactoryService.getRandomService()
                    .randomValueBetween(Constants.MIN_NUMBER_IN_BOARD, Constants.MAX_NUMBER_IN_BOARD);

            Snake snake = new Snake(start, end);
            snakes.add(snake);
        }

        return snakes;
    }
}
