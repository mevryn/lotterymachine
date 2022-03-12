package com.mevdev.lotterymachine.lottery;

import com.mevdev.lotterymachine.subscribers.SubType;
import com.mevdev.lotterymachine.subscribers.Subscriber;
import com.mevdev.lotterymachine.subscribers.Tier;

import java.util.ArrayList;
import java.util.List;

public class LotteryMachine {
    private final List<Subscriber> subscribers;
    private final LotteryConfig lotteryConfig;
    private final Random random;

    private List<Integer> peopleTokens;

    public LotteryMachine(List<Subscriber> subscriber, LotteryConfig lotteryConfig) {
        this(subscriber, lotteryConfig, new JavaRandom());
    }
    public LotteryMachine(List<Subscriber> subscribers, LotteryConfig lotteryConfig, Random random) {
        this.subscribers = subscribers;
        this.lotteryConfig = lotteryConfig;
        this.random = random == null ? new JavaRandom() : random;

        if (subscribers == null || subscribers.isEmpty()) {
            throw new IllegalArgumentException("Provider subscriber list is not valid: " + subscribers);
        }

        if (lotteryConfig == null) {
            throw new NullPointerException("Provided lotteryConfig is null");
        }

        prepareTokens();
    }

    private void prepareTokens() {
        peopleTokens = new ArrayList<>();

        for (int i = 0; i < subscribers.size(); i++) {
            Subscriber subscriber = subscribers.get(i);

            int numberOfTokenForSubscriber = getNumberOfTokensForSubscriber(subscriber);

            for (int j = 0; j < numberOfTokenForSubscriber; j++) {
                peopleTokens.add(i);
            }
        }

        if (peopleTokens.isEmpty()) {
            throw new IllegalStateException("Provided subscribers and config can't generate any tokens [subscribers=" + subscribers + ",lotteryConfig=" + lotteryConfig);
        }
    }

    private int getNumberOfTokensForSubscriber(Subscriber subscriber) {
        if (subscriber.getTier() == Tier.FIRST) {
            if (subscriber.getType() == SubType.GIFTED) {
                return lotteryConfig.getGiftedTier1Tokens();
            }
            else if (subscriber.getType() == SubType.PRIME) {
                return lotteryConfig.getPrimeTokens();
            }
            else if (subscriber.getType() == SubType.NORMAL){
                return lotteryConfig.getTier1Tokens();
            }
        }
        else if (subscriber.getTier() == Tier.SECOND) {
            if (subscriber.getType() == SubType.GIFTED) {
                return lotteryConfig.getGiftedTier2Tokens();
            }
            else if (subscriber.getType() == SubType.NORMAL) {
                return lotteryConfig.getTier2Tokens();
            }
        }
        else if (subscriber.getTier() == Tier.THIRD) {
            if (subscriber.getType() == SubType.GIFTED) {
                return lotteryConfig.getGiftedTier3Tokens();
            }
            else if (subscriber.getType() == SubType.NORMAL) {
                return lotteryConfig.getTier3Tokens();
            }

        }

        throw new IllegalArgumentException("Can't calculate number of tokens for subscriber! " + subscriber);
    }

    public Subscriber selectSubscriber() {
        return subscribers.get(peopleTokens.get(random.getRandomNumber(peopleTokens.size())));
    }

}
