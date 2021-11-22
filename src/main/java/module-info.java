module com.mevdev.lotterymachine {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.mevdev.lotterymachine.starter to javafx.fxml;
    exports com.mevdev.lotterymachine.starter.meta;
    exports com.mevdev.lotterymachine.starter;
    opens com.mevdev.lotterymachine.starter.meta to javafx.fxml;
}