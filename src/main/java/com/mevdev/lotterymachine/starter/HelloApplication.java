package com.mevdev.lotterymachine.starter;

import com.mevdev.lotterymachine.starter.localeloader.LocaleLoader;
import com.mevdev.lotterymachine.starter.meta.MainApplicationConstants;
import com.mevdev.lotterymachine.starter.meta.MainApplicationResourceConstants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(MainApplicationResourceConstants.JAVAFX_FXML_FILE));
        Scene scene = new Scene(fxmlLoader.load(), MainApplicationConstants.DEFAULT_APPLICATION_WIDTH, MainApplicationConstants.DEFAULT_APPLICATION_HEIGHT);
        LocaleLoader localeLoader = new LocaleLoader();
        ResourceBundle bundle = ResourceBundle.getBundle(MainApplicationResourceConstants.MAIN_APPLICATION_RESOURCE, localeLoader.getProjectLocale());
        stage.setTitle(bundle.getString(MainApplicationResourceConstants.MAIN_APPLICATION_TITLE));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}