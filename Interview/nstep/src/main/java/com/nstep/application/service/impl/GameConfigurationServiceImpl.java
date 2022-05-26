package com.nstep.application.service.impl;

import com.nstep.application.dto.SnakeLadderParameter;
import com.nstep.application.service.GameConfigurationService;
import com.nstep.dao.entity.GameConfiguration;
import com.nstep.utils.Constants;

public class GameConfigurationServiceImpl implements GameConfigurationService {

    @Override
    public SnakeLadderParameter getGameParamers() {
        String path = "application.properties";
        final GameConfiguration gameConfigurationInfo = FactoryService.getGameConfigRepository(Constants.FILE_CONFIG_TYPE)
                                                                      .getGameConfigurationInfo(path);

        final SnakeLadderParameter params = SnakeLadderParameter.builder()
                .boardSquareNum(gameConfigurationInfo.getBoardSquareNum())
                .snakeNum(gameConfigurationInfo.getSnakeNum())
                .ladderNum(gameConfigurationInfo.getLadderNum())
                .build();
        return params;
    }
}
