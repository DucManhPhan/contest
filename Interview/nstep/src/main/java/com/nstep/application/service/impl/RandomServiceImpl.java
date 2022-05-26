package com.nstep.application.service.impl;

import com.nstep.application.service.RandomService;

import java.util.Random;

public class RandomServiceImpl implements RandomService {
    @Override
    public int randomValueBetween(int low, int high) {
        Random random = new Random();
        return random.nextInt(high - low) + low;
    }
}
