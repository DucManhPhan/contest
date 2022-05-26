package com.nstep.application.service.testing;

import com.nstep.application.service.SnakeService;
import com.nstep.domain.Snake;

import java.util.ArrayList;
import java.util.List;

public class SnakeTestServiceImpl implements SnakeService {
    @Override
    public List<Snake> initializeSnakes() {
        return new ArrayList<>();
    }

}
