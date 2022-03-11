package com.mevdev.lotterymachine.lottery;

import com.mevdev.lotterymachine.subscribers.Subscriber;

import java.util.List;

public class LotteryMachine {
    private final List<Subscriber> subscriber;
    private final LotteryConfig lotteryConfig;
    private Random random;

    public LotteryMachine(List<Subscriber> subscriber, LotteryConfig lotteryConfig) {
        this(subscriber, lotteryConfig, new JavaRandom());
    }
    public LotteryMachine(List<Subscriber> subscriber, LotteryConfig lotteryConfig, Random random) {
        this.subscriber = subscriber;
        this.lotteryConfig = lotteryConfig;
        this.random = random == null ? new JavaRandom() : random;

        if (subscriber == null || subscriber.isEmpty()) {
            throw new IllegalArgumentException("Provider subscriber list is not valid: " + subscriber);
        }

        if (lotteryConfig == null) {
            throw new NullPointerException("Provided lotteryConfig is null");
        }
    }



    public Subscriber selectSubscriber() {
        return subscriber.get(random.getRandomNumber(subscriber.size()));
    }

}
