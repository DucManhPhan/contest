package com.nstep.application.service.impl;

import com.nstep.application.service.RollDiceService;

import java.util.Random;

public class RandomRollDiceServiceImpl implements RollDiceService {

    private final static int MAX_NUM_ROLL_DICE = 7;

    private final static int MIN_NUM_ROLL_DICE = 1;

    @Override
    public int rollDice() {
        Random random = new Random();
        int n = random.nextInt(MAX_NUM_ROLL_DICE);

        return n == 0 ? MIN_NUM_ROLL_DICE : n;
    }
}
