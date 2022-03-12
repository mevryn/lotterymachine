package com.mevdev.lotterymachine.lottery;

import com.mevdev.lotterymachine.subscribers.Subscriber;

import java.util.List;

public class SubListHolder {

    private static List<Subscriber> subscriberList;
    private static LotteryConfig lotteryConfig;

    public static List<Subscriber> getSubscriberList() {
        return subscriberList;
    }

    public static void setSubscriberList(List<Subscriber> subscriberList) {
        SubListHolder.subscriberList = subscriberList;
    }

    public static LotteryConfig getLotteryConfig() {
        return lotteryConfig;
    }

    public static void setLotteryConfig(LotteryConfig lotteryConfig) {
        SubListHolder.lotteryConfig = lotteryConfig;
    }


}
