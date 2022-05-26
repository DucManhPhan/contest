package com.nstep;

import com.nstep.application.service.SnakeAndLadderGameService;
import com.nstep.application.service.impl.FactoryService;
import com.nstep.utils.Constants;

public class Application {
    public static void main(String[] args) {
        SnakeAndLadderGameService snakeAndLadderGameService = FactoryService.getSnakeLadderGameService(Constants.PROD_ENV,
                                                                                Constants.DEFAULT_BOARD_SIZE);
        snakeAndLadderGameService.startGame();
    }
}
