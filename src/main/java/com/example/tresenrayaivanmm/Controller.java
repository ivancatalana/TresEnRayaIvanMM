package com.example.tresenrayaivanmm;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

public class Controller implements Initializable {

    private  int contadorX = 0 ;

    private  int contadorO = 0 ;

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Button button4;

    @FXML
    private Button button5;

    @FXML
    private Button button6;

    @FXML
    private Button button7;

    @FXML
    private Button button8;

    @FXML
    private Button button9;

    @FXML
    private Text winnerText;
    @FXML
    private Text winsPlayerX;
    @FXML
    private Text winsPlayerO;

    private int playerTurn = 0;

    private int contadorTiradas = 0;
     @FXML
      private  Button startButton;
    @FXML
     private Text playerO;
    @FXML
    private Text playerX;
    ArrayList<Button> buttons;

    boolean playerVsCpu=false;
    boolean cpuVSCpu = false;

    boolean gameOver=false;

    static Text textLastWinner=new Text("");

    static String textLastWinnerName="";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttons = new ArrayList<>(Arrays.asList(button1,button2,button3,button4,button5,button6,button7,button8,button9));

        buttons.forEach(button ->{
            setupButton(button);
            button.setFocusTraversable(false);
        });
    }
    @FXML
    void restartGame (ActionEvent event)  throws InterruptedException {
        gameOver=false;
        buttons.forEach(this::resetButton);
        winnerText.setText("Tres en raya");
        playGameMode();
    }
     public void playGameMode(){

         if(!playerVsCpu&&!cpuVSCpu) {

         }
         else if (playerVsCpu&&!cpuVSCpu){
             playerTurn=0;
         }
         else if (!playerVsCpu&&cpuVSCpu) {
                 playerTurn = 0;
                 while (!gameOver) {
                     if (contadorTiradas == 8) {
                         contadorTiradas += 1;
                         for (Button b : buttons) {
                             if (!b.isDisabled()) {
                                 b.setDisable(true);
                                 b.setText("X");
                             }
                         }

                     }
                     else if (playerTurn % 2 != 0) {
                         contadorTiradas += 1;
                         Button cPU = findFreeButton();
                         cPU.setDisable(true);
                         cPU.setText("O");
                         playerTurn = 0;
                     } else {
                         contadorTiradas += 1;
                         Button cPU = findFreeButton();
                         cPU.setDisable(true);
                         cPU.setText("X");
                         playerTurn = 1;
                     }
                     checkIfGameIsOver();
             }
         }
     }


    public void resetButton(Button button){
        button.setDisable(false);
        button.setText("");
    }

    private void setupButton(Button button) {
        button.setOnMouseClicked(mouseEvent -> {
            setPlayerSymbol(button);
            button.setDisable(true);
        });
    }
    public void setPlayerVsCpu(){
        contadorX=0;
        contadorO=0;
        playerO.setText("Player O (CPU)= ");
        winsPlayerX.setText("" + contadorO);
        winsPlayerO.setText("" + contadorO);
        playerTurn=0;
        contadorTiradas=0;
        playerVsCpu=true;
        cpuVSCpu=false;
        gameOver=false;
        buttons.forEach(this::resetButton);
        winnerText.setText("Tres en raya");
        playGameMode();
    }
    public void setCpuVsCpu(){
        contadorX=0;
        contadorO=0;
        playerX.setText("Player X (CPU)= ");
        playerO.setText("Player O (CPU)= ");
        winsPlayerX.setText("" + contadorO);
        winsPlayerO.setText("" + contadorO);
        contadorTiradas=0;
        playerVsCpu=false;
        cpuVSCpu=true;
        gameOver=false;
        buttons.forEach(this::resetButton);
        winnerText.setText("Tres en raya");
    }
    public void setPlayerVsPlayer(){
        contadorX=0;
        contadorO=0;
        playerX.setText("Player X = ");
        playerO.setText("Player O = ");
        winsPlayerX.setText("" + contadorO);
        winsPlayerO.setText("" + contadorO);
        playerTurn=0;
        contadorTiradas=0;
        playerVsCpu=false;
        cpuVSCpu=false;
        gameOver=false;
        buttons.forEach(this::resetButton);
        winnerText.setText("Tres en raya");
        playGameMode();
    }

    public void setPlayerSymbol(Button button) {
        contadorTiradas++;
        if (!playerVsCpu && !cpuVSCpu) {
            if (playerTurn % 2 == 0) {
                button.setText("X");
                playerTurn = 1;
            } else {
                button.setText("O");
                playerTurn = 0;
            }
            checkIfGameIsOver();
        }
        else if (playerVsCpu&&!cpuVSCpu) {
            if (playerTurn % 2 == 0) {
                button.setText("X");
                playerTurn = 1;
                checkIfGameIsOver();
                if(!gameOver) {
                    contadorTiradas++;
                    Button cPU = findFreeButton();
                    cPU.setDisable(true);
                    cPU.setText("O");
                    playerTurn = 0;
                    checkIfGameIsOver();
                }
            }
        }
    }
    private Button findFreeButton(){
        int randomButton = ThreadLocalRandom.current().nextInt(0, 8);
        boolean allDisabled=true;

        while( !buttons.get(randomButton).getText().isEmpty()){
            randomButton=ThreadLocalRandom.current().nextInt(0, 8);
        }
        return buttons.get(randomButton);
    }
    public void checkIfGameIsOver(){


        for (int a = 0; a < 8; a++) {
            String line = switch (a) {
                case 0 -> button1.getText() + button2.getText() + button3.getText();
                case 1 -> button4.getText() + button5.getText() + button6.getText();
                case 2 -> button7.getText() + button8.getText() + button9.getText();
                case 3 -> button1.getText() + button5.getText() + button9.getText();
                case 4 -> button3.getText() + button5.getText() + button7.getText();
                case 5 -> button1.getText() + button4.getText() + button7.getText();
                case 6 -> button2.getText() + button5.getText() + button8.getText();
                case 7 -> button3.getText() + button6.getText() + button9.getText();
                default -> null;
            };

            //X winner
            if (line.equals("XXX")) {
                winnerText.setText("X Gana!");
                contadorX += 1;
                winsPlayerX.setText("" + contadorX);
                contadorTiradas=0;
                for (Button b : buttons)b.setDisable(true);
                gameOver=true;
                popUpStatsName();
            }
            //O winner
            else if (line.equals("OOO")) {
                winnerText.setText("O Gana!");
                contadorO += 1;
                winsPlayerO.setText("" + contadorO);
                contadorTiradas=0;
                for (Button b : buttons)b.setDisable(true);
                gameOver=true;
                popUpStatsName();

            }

        }
            if (contadorTiradas == 9){
            winnerText.setText(" Empate!");
            contadorTiradas=0;
            gameOver=true;
        }
    }
    public void showStatsName(){
        ControllerStats controllerStats=new ControllerStats();
        controllerStats.showStatsName();
    }
    public void showStatsTable(){
        ControllerStats controllerStats=new ControllerStats();
        controllerStats.showStatsTable2();
    }
    public void popUpStatsName(){
        if(!playerVsCpu&&!cpuVSCpu){
            if(winnerText.getText().equals("X Gana!")) {
                textLastWinner.setText("Felicidades X. Has Ganado!");
                showStatsName();
            }
            else if(winnerText.getText().equals("O Gana!")) {
                textLastWinner.setText("Felicidades O. Has Ganado!");
                showStatsName();
            }
        }
        else if (playerVsCpu&&!cpuVSCpu){
            if(winnerText.getText().equals("X Gana!")){
                showStatsName();
            }
        }

    }
    public void exitApp(){
        Platform.exit();
    }
    public void showTutorial() throws IOException {
        ControllerTutorial controllerTutorial = new ControllerTutorial();
        controllerTutorial.showTutorial();
    }
}
