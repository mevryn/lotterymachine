package com.mevdev.lotterymachine.lottery;

import com.mevdev.lotterymachine.subscribers.SubType;
import com.mevdev.lotterymachine.subscribers.Subscriber;
import com.mevdev.lotterymachine.subscribers.Tier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Answers;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LotteryMachineScenarioTests {

    @ParameterizedTest
    @MethodSource("testProvider")
    public void testScenario(LotteryMachineTestScenario scenario) {
        Random mock = Mockito.mock(Random.class);

        Mockito.when(mock.getRandomNumber(scenario.expectedNumberOfAllTokens)).thenReturn(scenario.selectedIndex);
        LotteryMachine lotteryMachine = new LotteryMachine(scenario.subscribers, scenario.lotteryConfig, mock);

        Subscriber actualSubscriber = lotteryMachine.selectSubscriber();
        assertEquals(scenario.expectedSubscriber, actualSubscriber);

        Mockito.verify(mock).getRandomNumber(scenario.expectedNumberOfAllTokens);
    }

    private static Stream<LotteryMachineTestScenario> testProvider() {
        return Stream.of(
                getBasicScenarioWithOneSubscriber(),
                getBasicScenarioWithSixSubscribers(), // no different amount of tokens
                getScenarioWithThreeSubscribersSecondNoTokens(),
                getScenarioMultipleTiersAndDifferentSubscribersTypes()
        );
    }

    private static LotteryMachineTestScenario getScenarioMultipleTiersAndDifferentSubscribersTypes() {
        return new LotteryMachineTestScenario(23,
                List.of(
                        getSubscriber("Adam", Tier.FIRST, SubType.NORMAL), // 2 tokens
                        getSubscriber("Ziutek", Tier.SECOND, SubType.NORMAL), // 4 tokens
                        getSubscriber("Kazik", Tier.THIRD, SubType.NORMAL), // 6 tokens
                        getSubscriber("Wiewior", Tier.FIRST, SubType.GIFTED), // 1 token
                        getSubscriber("Babcia", Tier.SECOND, SubType.GIFTED), // 3 tokens
                        getSubscriber("Meff", Tier.THIRD, SubType.GIFTED), // 5 tokens
                        getSubscriber("Kamila", Tier.FIRST, SubType.PRIME) // 2 tokens
                ),
                new LotteryConfig(1, 3, 5, 2, 4, 6, 2),21, getSubscriber("Kamila", Tier.FIRST, SubType.PRIME));
    }

    private static LotteryMachineTestScenario getBasicScenarioWithSixSubscribers() {
        return new LotteryMachineTestScenario(6,
                List.of(
                        getSubscriber("Adam"),
                        getSubscriber("Ziutek"),
                        getSubscriber("Kazik"),
                        getSubscriber("Wiewior"),
                        getSubscriber("Babcia"),
                        getSubscriber("Meff")
                ),
                getLotteryConfig(),3, getSubscriber("Wiewior"));
    }

    private static LotteryMachineTestScenario getScenarioWithThreeSubscribersSecondNoTokens() {
        return new LotteryMachineTestScenario(2,
                List.of(
                        getSubscriber("Adam"),
                        getSubscriber("Meff", Tier.SECOND),
                        getSubscriber("Kamila")
                ),
                new LotteryConfig(1, 0, 0, 1, 0, 0, 0),
                1,
                getSubscriber("Kamila"));
    }

    private static LotteryMachineTestScenario getBasicScenarioWithOneSubscriber() {
        return new LotteryMachineTestScenario(1, List.of(getSubscriber()), getLotteryConfig(),0, getSubscriber());
    }

    private static Subscriber getSubscriber() {
        return getSubscriber("Adam");
    }

    private static Subscriber getSubscriber(String name) {
        return getSubscriber(name, Tier.FIRST);
    }

    private static Subscriber getSubscriber(String name, Tier tier) {
        return getSubscriber(name, tier, SubType.NORMAL);
    }

    private static Subscriber getSubscriber(String name, Tier tier, SubType type) {
        return new Subscriber(name, tier, type);
    }

    private static LotteryConfig getLotteryConfig() {
        return new LotteryConfig(1, 0, 0, 1, 0, 0, 0);
    }


    private static class LotteryMachineTestScenario {
        private final int expectedNumberOfAllTokens;
        private final List<Subscriber> subscribers;
        private final LotteryConfig lotteryConfig;
        private final int selectedIndex;
        private final Subscriber expectedSubscriber;

        public LotteryMachineTestScenario(int expectedNumberOfAllTokens, List<Subscriber> subscribers, LotteryConfig lotteryConfig, int selectedIndex, Subscriber expectedSubscriber) {
            this.expectedNumberOfAllTokens = expectedNumberOfAllTokens;
            this.subscribers = subscribers;
            this.lotteryConfig = lotteryConfig;
            this.selectedIndex = selectedIndex;
            this.expectedSubscriber = expectedSubscriber;
        }


    }
}
