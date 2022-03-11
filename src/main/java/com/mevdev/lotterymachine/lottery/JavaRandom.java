package com.mevdev.lotterymachine.lottery;

import java.util.Random;

public class JavaRandom implements com.mevdev.lotterymachine.lottery.Random {
    private Random random;

    public JavaRandom() {
        this.random = new Random();
    }

    @Override
    public int getRandomNumber(int bound) {
        return random.nextInt(bound);
    }
}
