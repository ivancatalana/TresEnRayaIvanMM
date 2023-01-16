package com.example.tresenrayaivanmm.controller;

import com.example.tresenrayaivanmm.MainApp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerTutorial {
    private static Stage s;
    public void showTutorial () throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("views/tutorial.fxml"));
        AnchorPane tutorial = new AnchorPane();
        s = new Stage();
            tutorial = (AnchorPane) loader.load();
        Scene scene = new Scene(tutorial,320,240);
        s.setScene(scene);
        s.setTitle("Tutorial");
        tutorial.setBackground(Background.fill(new LinearGradient(
                0, 0, 1, 1, true,                      //sizing
                CycleMethod.NO_CYCLE,                  //cycling
                new Stop(0, Color.web("#81c483")),     //colors
                new Stop(1, Color.web("#fcc200")))));
        s.centerOnScreen();
        s.show();
    }
    public void closeTutorial(){
         s.close();
    }
}
