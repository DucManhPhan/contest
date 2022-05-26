package com.nstep.application.service.impl;

import com.nstep.application.dto.SnakeLadderParameter;
import com.nstep.application.service.LadderService;
import com.nstep.domain.Ladder;
import com.nstep.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class LadderServiceImpl implements LadderService {
    @Override
    public List<Ladder> initializeLadders() {
        SnakeLadderParameter params = FactoryService.getGameConfigService()
                                                    .getGameParamers();
        int ladderNum = params.getLadderNum();
        List<Ladder> ladders = new ArrayList<>();

        for (int i = 1; i <= ladderNum; ++i) {
            int start = FactoryService.getRandomService()
                    .randomValueBetween(Constants.MIN_NUMBER_IN_BOARD, Constants.MAX_NUMBER_IN_BOARD);
            int end = FactoryService.getRandomService()
                    .randomValueBetween(Constants.MIN_NUMBER_IN_BOARD, Constants.MAX_NUMBER_IN_BOARD);

            Ladder ladder = new Ladder(start, end);
            ladders.add(ladder);
        }

        return ladders;
    }

}
