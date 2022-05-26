package com.nstep.dao.repository;

import com.nstep.dao.entity.GameConfiguration;

public interface GameConfigurationRepository {
    GameConfiguration getGameConfigurationInfo(String path);
}
