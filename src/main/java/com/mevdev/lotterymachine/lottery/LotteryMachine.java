package com.mevdev.lotterymachine.lottery;

import com.mevdev.lotterymachine.subscribers.Subscriber;

import java.util.List;

public class LotteryMachine {
    private final List<Subscriber> subscriber;
    private final LotteryConfig lotteryConfig;

    public LotteryMachine(List<Subscriber> subscriber, LotteryConfig lotteryConfig) {
        this.subscriber = subscriber;
        this.lotteryConfig = lotteryConfig;

        if (subscriber == null || subscriber.isEmpty()) {
//        if (subscriber != null && subscriber.isEmpty()) {
            throw new IllegalArgumentException("Provider subscriber list is not valid: " + subscriber);
        }

        if (lotteryConfig == null) {
            throw new NullPointerException("Provided lotteryConfig is null");
        }
    }

    public Subscriber selectSubscriber() {
        return subscriber.get(0);
    }

}
