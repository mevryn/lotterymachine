package com.mevdev.lotterymachine.starter;

import com.mevdev.lotterymachine.starter.localeloader.LocaleLoader;
import com.mevdev.lotterymachine.starter.meta.MainApplicationResourceConstants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.ResourceBundle;

public class HelloApplication extends Application {

    public static final String ICON_APPLICATION_NAME = "icon.png";

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException, URISyntaxException {

        LocaleLoader localeLoader = new LocaleLoader();
        ResourceBundle bundle = ResourceBundle.getBundle(MainApplicationResourceConstants.MAIN_APPLICATION_RESOURCE,
                localeLoader.getProjectLocale());
        FXMLLoader fxmlLoader =
                new FXMLLoader(HelloApplication.class.getResource(MainApplicationResourceConstants.JAVAFX_FXML_FILE),
                        bundle);
        Scene scene = new Scene(fxmlLoader.load());
        setStage(stage, bundle, scene);
    }

    private void setStage(Stage stage, ResourceBundle bundle, Scene scene) throws URISyntaxException {
        stage.setTitle(bundle.getString(MainApplicationResourceConstants.VIEW_TITLE));
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource(ICON_APPLICATION_NAME)).toURI().toString()));
        stage.setScene(scene);
        stage.show();
    }
}