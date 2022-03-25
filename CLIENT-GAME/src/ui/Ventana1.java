package ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.LoadListener;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.ControllerGame;
import model.Generic;
import model.Match;
import model.Words;
import model.ControllerGame.InitGameListener;
import model.ControllerGame.LoadResultsListener;
import model.ControllerGame.StopGameListener;
import model.comm.TCPConnection;
import model.comm.Receiver.OnMessageListener;

public class Ventana1 implements Initializable, StopGameListener, InitGameListener,LoadResultsListener {


	@FXML
	private AnchorPane mainPane;

	@FXML
	private Label title;

	@FXML
	private Button stopBtn;

	@FXML
	private TextField nameAnswer;

	@FXML
	private TextField animalAnswer;

	@FXML
	private TextField locationAnswer;

	@FXML
	private TextField objectAnswer;

	private int id;

	private ControllerGame controller;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
        controller=ControllerGame.getInstance();
		controller.setInitGameListener(this);
		controller.setStopGameListener(this);
		controller.setLoadResultsListener(this);

		title.setText("WAITING...");
		stopBtn.setDisable(true);
		id = 0;


	}

	@FXML
	public void stop(ActionEvent event) {

		if(nameAnswer.getText().isEmpty() || animalAnswer.getText().isEmpty() || locationAnswer.getText().isEmpty() || objectAnswer.getText().isEmpty()){

		}else{
			controller.stop(id, nameAnswer.getText(), animalAnswer.getText(), locationAnswer.getText(),
			objectAnswer.getText());
		}




	}

	private void loadWindow2() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Ventana2.fxml"));
		Parent pane = fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.getChildren().addAll(pane);

	}

	@Override
	public void initGame(int id, String string) {

		Platform.runLater(() -> {
			title.setText("LETRA: " + string);
		});
		stopBtn.setDisable(false);
		this.id = id;
	}

	@Override
	public void stop() {

		controller.stop(id, nameAnswer.getText(), animalAnswer.getText(), locationAnswer.getText(),
				objectAnswer.getText());
	}

	@Override
	public void LoadResults() {
		
		Platform.runLater(() -> {
			try {
				loadWindow2();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		
	}

	



}
