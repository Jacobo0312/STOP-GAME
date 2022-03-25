package ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.ControllerGame;
import model.Words;

public class Ventana2 implements Initializable {

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Label ownNameResult;

    @FXML
    private Label opponentNameResult;

    @FXML
    private Label ownAnimalResult;

    @FXML
    private Label opponentAnimalResult;

    @FXML
    private Label ownLocationResult;

    @FXML
    private Label opponentLocationResult;

    @FXML
    private Label ownObjectResult;

    @FXML
    private Label opponentObjectResult;

    @FXML
    private Button finishBtn;

    @FXML
    private Label ownTotal;

    @FXML
    private Label opponentTotal;

    private ControllerGame controller;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = ControllerGame.getInstance();
        int id = controller.getId();
        Words[] words = controller.getResults();

        if (words[0].getId() == id) {
            ownNameResult.setText(words[0].getName());
            ownAnimalResult.setText(words[0].getAnimal());
            ownLocationResult.setText(words[0].getSite());
            ownObjectResult.setText(words[0].getThing());
            ownTotal.setText(words[0].getPoints() + "");

            opponentNameResult.setText(words[1].getName());
            opponentAnimalResult.setText(words[1].getAnimal());
            opponentLocationResult.setText(words[1].getSite());
            opponentObjectResult.setText(words[1].getThing());
            opponentTotal.setText(words[1].getPoints() + "");

        } else {

            ownNameResult.setText(words[1].getName());
            ownAnimalResult.setText(words[1].getAnimal());
            ownLocationResult.setText(words[1].getSite());
            ownObjectResult.setText(words[1].getThing());
            ownTotal.setText(words[1].getPoints() + "");

            opponentNameResult.setText(words[0].getName());
            opponentAnimalResult.setText(words[0].getAnimal());
            opponentLocationResult.setText(words[0].getSite());
            opponentObjectResult.setText(words[0].getThing());
            opponentTotal.setText(words[0].getPoints() + "");

        }

    }

    @FXML
    public void finish(ActionEvent event) {

        try {
            loadWindow1();
            controller.restart();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void loadWindow1() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Ventana1.fxml"));
        Parent pane = fxmlLoader.load();
        mainPane.getChildren().clear();
        mainPane.getChildren().addAll(pane);

    }

}
