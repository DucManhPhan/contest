package com.nstep.application.service.testing;

import com.nstep.application.service.LadderService;
import com.nstep.domain.Ladder;

import java.util.ArrayList;
import java.util.List;

public class LadderTestServiceImpl implements LadderService {

    @Override
    public List<Ladder> initializeLadders() {
        return new ArrayList<>();
    }

}
