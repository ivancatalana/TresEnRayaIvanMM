package com.example.tresenrayaivanmm;
import com.opencsv.CSVReader;

import com.example.tresenrayaivanmm.model.Persona;
import com.opencsv.exceptions.CsvValidationException;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.*;

public class ControllerStats  {
    private static Stage secondaryStage;
    @FXML
    private Button acceptButton;
    @FXML
    private TextField textFieldWinner=new TextField(Controller.textLastWinnerName.getText());
    public void showStatsName() {
     //   textFieldWinner.setPromptText("Controller.textLastWinnerName.getText()");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("stats-name.fxml"));
        VBox statsName = new VBox(textFieldWinner);

        try {
            statsName = (VBox) loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(statsName,320,240);
        secondaryStage = new Stage();
        secondaryStage.setScene(scene);
        secondaryStage.setTitle(Controller.textLastWinner.getText());
        secondaryStage.show();
        // Set person overview into the center of root layout.

    }
    public void acceptNameStats(){
        String winnerName= textFieldWinner.getText();
        setFileWriterwinner(winnerName);
        secondaryStage.close();


    }
    public void closeStats() throws IOException {
        secondaryStage.close();
    }

    private void setFileWriterwinner(String td) {
        ArrayList<Persona>personas=new ArrayList<>();
        List<List<String>> records = new ArrayList<List<String>>();
        boolean exist=false;
        Controller.textLastWinnerName.setText(td);
        try {
            FileReader fileReader = new FileReader(new File("src/main/resources/com/example/tresenrayaivanmm/stats.txt"));
            CSVReader csvReader = new CSVReader(fileReader);
            String[] values = null;
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
            FileWriter fileWriter = new FileWriter(new File("src/main/resources/com/example/tresenrayaivanmm/stats.txt"));
            fileWriter.write(fileTxt);
            fileWriter.close();


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }
    public void showStatsTable() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("stats-list.fxml"));
        BorderPane statsName = new BorderPane();

        try {
            statsName = (BorderPane) loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(statsName,520,340);
        secondaryStage = new Stage();
        secondaryStage.setScene(scene);
        secondaryStage.setTitle("Lista de ganadores");
        secondaryStage.show();
        // Set person overview into the center of root layout.

    }
    public void showStatsTable2() {


        ArrayList<Persona>personas=new ArrayList<>();
        List<List<String>> records = new ArrayList<List<String>>();
        boolean exist=false;

        try {
            FileReader fileReader = new FileReader(new File("src/main/resources/com/example/tresenrayaivanmm/stats.txt"));
            CSVReader csvReader = new CSVReader(fileReader);
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                records.add(Arrays.asList(values));
            }
            for(List l : records) {
                personas.add(new Persona(l.get(0).toString(),Integer.parseInt(l.get(1).toString())));
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvValidationException e) {
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

        s.setScene(new Scene(new BorderPane(table), 300, 300));
        s.show();
}
}
