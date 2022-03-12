package com.mevdev.lotterymachine.starter;

import com.mevdev.lotterymachine.lottery.LotteryConfig;
import com.mevdev.lotterymachine.lottery.SubListHolder;
import com.mevdev.lotterymachine.starter.localeloader.LocaleLoader;
import com.mevdev.lotterymachine.starter.meta.MainApplicationResourceConstants;
import com.mevdev.lotterymachine.subscribers.FileSubReader;
import com.mevdev.lotterymachine.subscribers.SubReader;
import com.mevdev.lotterymachine.subscribers.Subscriber;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.mevdev.lotterymachine.starter.LotteryApplication.ICON_APPLICATION_NAME;

public class MainViewController {

    @FXML
    public Button confirmationButton;
    @FXML
    public Label fileChosen;
    public TextField subTier2Field;
    public TextField subTier3Field;
    public TextField subTier1Field;
    public TextField subTierGift3Field;
    public TextField subTierGift2Field;
    public TextField subTierGift1Field;
    public TextField primeField;
    LocaleLoader localeLoader = new LocaleLoader();
    private List<Subscriber> subList = new ArrayList<>();
    private ResourceBundle resourceBundle;

    @FXML
    protected void onConfirmationButtonClick() throws IOException, URISyntaxException {
        SubListHolder.setLotteryConfig(getLotteryConfig());
        //Start LotteryView
        startLottery();
    }

    private LotteryConfig getLotteryConfig() {
        return new LotteryConfig(Integer.parseInt(subTierGift1Field.getText()),
                Integer.parseInt(subTierGift2Field.getText()), Integer.parseInt(subTierGift3Field.getText()),
                Integer.parseInt(subTier1Field.getText()),
                Integer.parseInt(subTier2Field.getText()), Integer.parseInt(subTier3Field.getText()),
                Integer.parseInt(primeField.getText()));
    }

    public void initialize() {
        LocaleLoader localeLoader = new LocaleLoader();
        resourceBundle = ResourceBundle.getBundle(MainApplicationResourceConstants.MAIN_APPLICATION_RESOURCE,
                localeLoader.getProjectLocale());
        confirmationButton.setDisable(true);
        setTextReplacer(subTier2Field);
        setTextReplacer(subTier1Field);
        setTextReplacer(subTier3Field);
        setTextReplacer(subTierGift3Field);
        setTextReplacer(subTierGift2Field);
        setTextReplacer(subTierGift1Field);
        setTextReplacer(primeField);
    }

    private void setTextReplacer(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    public void onFileChooserClick() throws IOException {
        SubReader subReader = new FileSubReader();
        subList = subReader.getSubList();
        if (!subList.isEmpty()) {
            SubListHolder.setSubscriberList(subList);
            fileChosen.setText(resourceBundle.getString(MainApplicationResourceConstants.FILE_CHOSEN));
        } else {
            SubListHolder.setSubscriberList(subList);
            fileChosen.setText("");
        }
        confirmationButton.setDisable(subList.isEmpty());
    }

    private void startLottery() throws IOException, URISyntaxException {
        ResourceBundle bundle = ResourceBundle.getBundle(MainApplicationResourceConstants.LOTTERY_VIEW_RESOURCE,
                localeLoader.getProjectLocale());
        FXMLLoader fxmlLoader =
                new FXMLLoader(getClass().getResource(MainApplicationResourceConstants.LOTTERY_VIEW_FILE),
                        bundle);
        Scene scene = new Scene(fxmlLoader.load());
        setStage(new Stage(), bundle, scene);
    }

    private void setStage(Stage stage, ResourceBundle bundle, Scene scene) throws URISyntaxException {
        stage.setTitle(bundle.getString(MainApplicationResourceConstants.VIEW_TITLE));
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource(ICON_APPLICATION_NAME)).toURI().toString()));
        stage.setScene(scene);
        stage.show();
    }

}