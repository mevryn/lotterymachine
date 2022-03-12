package com.mevdev.lotterymachine.starter;

import com.mevdev.lotterymachine.lottery.JavaRandom;
import com.mevdev.lotterymachine.lottery.LotteryMachine;
import com.mevdev.lotterymachine.lottery.SubListHolder;
import com.mevdev.lotterymachine.subscribers.Subscriber;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.util.List;
import java.util.stream.Collectors;

public class LotteryViewController {

    public Label subLabel;
    private Timeline timeline;

    public void initialize() {
        timeline = new Timeline(
                new KeyFrame(Duration.millis(250), e -> {
                    List<Subscriber> subList = SubListHolder.getSubscriberList();
                    subLabel.setText(subList.stream().map(Subscriber::getName).collect(Collectors.toList()).get(new JavaRandom().getRandomNumber(subList.size())));
                })
        );
        timeline.setCycleCount(-1);
        timeline.play();
    }

    public void draw() {
        timeline.stop();
        LotteryMachine lotteryMachine = new LotteryMachine(SubListHolder.getSubscriberList(),
                SubListHolder.getLotteryConfig());
        Subscriber subscriber = lotteryMachine.selectSubscriber();
        subLabel.setText(subscriber.getName());
    }
}
