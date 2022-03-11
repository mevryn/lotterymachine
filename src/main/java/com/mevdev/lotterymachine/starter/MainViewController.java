package com.mevdev.lotterymachine.starter;

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
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

import static com.mevdev.lotterymachine.starter.HelloApplication.ICON_APPLICATION_NAME;

public class MainViewController {

    @FXML
    public Button drawButton;
    @FXML
    public Label fileChosen;
    LocaleLoader localeLoader = new LocaleLoader();
    @FXML
    private Label welcomeText;
    private List<Subscriber> subList = new ArrayList<>();
    private ResourceBundle resourceBundle;

    @FXML
    protected void onHelloButtonClick() {
        if (!subList.isEmpty()) {
            int i = new Random().nextInt(subList.size() - 1);
            welcomeText.setText(subList.get(i).toString());
        } else {
            welcomeText.setText(resourceBundle.getString(MainApplicationResourceConstants.CHOSE_FILE));
        }
    }

    public void initialize() {
        LocaleLoader localeLoader = new LocaleLoader();
        resourceBundle = ResourceBundle.getBundle(MainApplicationResourceConstants.MAIN_APPLICATION_RESOURCE,
                localeLoader.getProjectLocale());
    }

    public void onFileChooserClick() throws IOException, URISyntaxException {
        SubReader subReader = new FileSubReader();
        subList = subReader.getSubList();
        fileChosen.setText(subList.stream().map(Subscriber::getName).collect(Collectors.toList()).toString());
        //Start SubList
        ResourceBundle bundle = ResourceBundle.getBundle(MainApplicationResourceConstants.SUB_LIST_VIEW_RESOURCE,
                localeLoader.getProjectLocale());
        FXMLLoader fxmlLoader =
                new FXMLLoader(getClass().getResource(MainApplicationResourceConstants.SUB_VIEW_FILE),
                        bundle);
        Scene scene = new Scene(fxmlLoader.load());
        setStage(new Stage(), bundle, scene);
        //Start LotteryView
    }

    private void setStage(Stage stage, ResourceBundle bundle, Scene scene) throws URISyntaxException {
        stage.setTitle(bundle.getString(MainApplicationResourceConstants.VIEW_TITLE));
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource(ICON_APPLICATION_NAME)).toURI().toString()));
        stage.setScene(scene);
        stage.show();
    }

}