package com.example.tresenrayaivanmm;

import com.example.tresenrayaivanmm.controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
     private Scene scene ;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("views/main-panel.fxml"));
        scene = new Scene(fxmlLoader.load(), 600, 400);
        Controller controller= (Controller) fxmlLoader.getController();
        controller.setMain(this);
        scene.getStylesheets().add(MainApp.class.getResource("styles/applicationVs.css").toExternalForm());
        stage.setTitle("TresEnRaya");
        stage.setScene(scene);
        stage.show();
    }
    public void setUpLightMode(){
        scene.getStylesheets().remove(MainApp.class.getResource("styles/applicationVs.css").toExternalForm());
        scene.getStylesheets().add(MainApp.class.getResource("styles/applicationLight.css").toExternalForm());

    }
    public void setUpNightMode(){
        scene.getStylesheets().remove(MainApp.class.getResource("styles/applicationLight.css").toExternalForm());
        scene.getStylesheets().add(MainApp.class.getResource("styles/applicationVs.css").toExternalForm());
    }
}
