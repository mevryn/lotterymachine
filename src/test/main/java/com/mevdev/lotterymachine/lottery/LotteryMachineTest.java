package com.mevdev.lotterymachine.lottery;

import com.mevdev.lotterymachine.subscribers.SubType;
import com.mevdev.lotterymachine.subscribers.Subscriber;
import com.mevdev.lotterymachine.subscribers.Tier;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LotteryMachineTest {

    @Test
    public void shouldRejectNullSubscribers() {
        assertThrows(IllegalArgumentException.class, () -> new LotteryMachine(null,
                getLotteryConfig()));
    }

    private LotteryConfig getLotteryConfig() {
        return new LotteryConfig(0,0,0,0, 0, 0, 0);
    }

    @Test
    public void shouldRejectEmptySubscribers() {
        assertThrows(IllegalArgumentException.class, () -> new LotteryMachine(new ArrayList<>(),
                getLotteryConfig()));
    }

    @Test
    public void shouldRejectNullConfig() {
        List<Subscriber> subscribers = List.of(new Subscriber("Adam", Tier.FIRST, SubType.NORMAL));
        assertThrows(NullPointerException.class, () -> new LotteryMachine(subscribers, null));
    }

    @Test
    public void shouldSelectSubscriberWhenOnlyOneIsAvailable() {
        List<Subscriber> subscribers = List.of(new Subscriber("Adam", Tier.FIRST, SubType.NORMAL));
        LotteryMachine lotteryMachine = new LotteryMachine(subscribers, null);

        Subscriber subscriber = lotteryMachine.selectSubscriber();
        assertEquals(subscribers.get(0), subscriber);
    }
}
