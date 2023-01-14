package com.example.tresenrayaivanmm;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerTutorial {
    private static Stage s;
    public void showTutorial () throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("tutorial.fxml"));
        AnchorPane tutorial = new AnchorPane();
        s = new Stage();
            tutorial = (AnchorPane) loader.load();
        Scene scene = new Scene(tutorial,320,240);
        s.setScene(scene);
        s.setTitle("Tutorial");
        s.centerOnScreen();
        s.show();
    }
    public void closeTutorial(){
         s.close();
    }
}
