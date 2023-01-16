package com.example.tresenrayaivanmm.controller;

import com.example.tresenrayaivanmm.MainApp;
import com.example.tresenrayaivanmm.model.Persona;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ControllerStats {
    private static Stage secondaryStage;
    @FXML
    private TextField textFieldWinner;
    @FXML
    private void initialize() {
        textFieldWinner.setText(Controller.textLastWinnerName);
    }


    public void showStatsName (){

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("views/stats-name.fxml"));
        new VBox();
        VBox statsName;
        try {
            statsName = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        statsName.setBackground(
                Background.fill(new LinearGradient(
                0, 0, 1, 1, true,                      //sizing
                CycleMethod.NO_CYCLE,                  //cycling
                new Stop(0, Color.web("#F6F2E5")),     //colors
                new Stop(1, Color.web("#7B7A79")))));
        Scene scene = new Scene(statsName,320,240);
        secondaryStage = new Stage();
        secondaryStage.setScene(scene);
        secondaryStage.setTitle(Controller.textLastWinner.getText());
        secondaryStage.show();
        // Set person overview into the center of root layout.

    }
    public void acceptNameStats(){
        String winnerName= textFieldWinner.getText();
        if (!winnerName.equals(" ")) {
            setFileWriterwinner(winnerName);
            Controller.textLastWinnerName = winnerName;
            secondaryStage.close();
        }

    }
    public void closeStats()  {
        secondaryStage.close();
    }

    private void setFileWriterwinner(String td) {
        ArrayList<Persona>personas=new ArrayList<>();
        List<List<String>> records = new ArrayList<>();
        boolean exist=false;
        try {
            FileReader fileReader = new FileReader("src/main/resources/com/example/tresenrayaivanmm/data/stats.txt");
            CSVReader csvReader = new CSVReader(fileReader);
            String[] values;
            String fileTxt="";
            while ((values = csvReader.readNext()) != null) {
                records.add(Arrays.asList(values));
            }
            for(List l : records) {
                          personas.add(new Persona(l.get(0).toString(),Integer.parseInt(l.get(1).toString())));
            }
            for(Persona p:personas){
                if (td.equals(p.getName())){
                    p.setPartidasGanadas(p.getPartidasGanadas()+1);
                    exist=true;
                }
            }
            if (!exist){
                personas.add(new Persona(td,1));
            }

            for (Persona p:personas){
                fileTxt += p.getName()+","+p.getPartidasGanadas()+"\n";
            }
            FileWriter fileWriter = new FileWriter(new File("src/main/resources/com/example/tresenrayaivanmm/data/stats.txt"));
            fileWriter.write(fileTxt);
            fileWriter.close();
            textFieldWinner.setText(td);

        } catch (CsvValidationException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showStatsTable2() {


        ArrayList<Persona>personas=new ArrayList<>();
        List<List<String>> records = new ArrayList<List<String>>();

        try {
            FileReader fileReader = new FileReader("src/main/resources/com/example/tresenrayaivanmm/data/stats.txt");
            CSVReader csvReader = new CSVReader(fileReader);
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                records.add(Arrays.asList(values));
            }
            for(List l : records) {
                personas.add(new Persona(l.get(0).toString(),Integer.parseInt(l.get(1).toString())));
            }


        } catch (CsvValidationException | IOException e) {
            throw new RuntimeException(e);
        }

        personas.sort((o1, o2) -> o1.compareTo(o2));
        int contadorRanking=1;
        List<String> rankValues = new LinkedList<>();
        List<String> stringValues = new LinkedList<>();
        List<Integer> intValues = new LinkedList<>();
      Stage s = new Stage();
        s.setTitle("Ranking de ganadores");

        for(Persona p:personas) {
            rankValues.add("   "+contadorRanking+"º");
            contadorRanking++;
            stringValues.add(p.getName());
            intValues.add(p.getPartidasGanadas());
        }

    TableView<Integer> table = new TableView<>();
        for (int i = 0; i < intValues.size() && i < stringValues.size(); i++) {
        table.getItems().add(i);
    }
        TableColumn<Integer, String> rankColumn = new TableColumn<>("Ranking");
        List<String> rankingIntValues = rankValues;
        rankColumn.setCellValueFactory(cellData -> {
            Integer rowIndex = cellData.getValue();
            return new ReadOnlyStringWrapper(rankingIntValues.get(rowIndex));
        });

        TableColumn<Integer, String> nameColumn = new TableColumn<>("Nombre");
        List<String> finalStringValues = stringValues;
        nameColumn.setCellValueFactory(cellData -> {
            Integer rowIndex = cellData.getValue();
            return new ReadOnlyStringWrapper(finalStringValues.get(rowIndex));
        });

    TableColumn<Integer, Number> intColumn = new TableColumn<>("Partidas Ganadas");
        List<Integer> finalIntValues = intValues;
        intColumn.setCellValueFactory(cellData -> {
        Integer rowIndex = cellData.getValue();
        return new ReadOnlyIntegerWrapper(finalIntValues.get(rowIndex));
    });
        table.getColumns().add(rankColumn);
        table.getColumns().add(nameColumn);
        table.getColumns().add(intColumn);
        Scene scene = new  Scene(new BorderPane(table), 300, 300);
        scene.getStylesheets().add(Objects.requireNonNull(MainApp.class.getResource("styles/applicationLight.css")).toExternalForm());
        s.setScene(scene);
        s.show();
}
}
