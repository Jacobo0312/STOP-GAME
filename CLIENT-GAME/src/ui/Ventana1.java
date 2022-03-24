package ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.google.gson.Gson;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Generic;
import model.Match;
import model.Words;
import model.comm.TCPConnection;
import model.comm.Receiver.OnMessageListener;

public class Ventana1 implements Initializable,OnMessageListener{

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


    private TCPConnection connection;
	private Gson gson=new Gson();



    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		connection = TCPConnection.getInstance();
		connection.setIp("127.0.0.1");
		connection.setPuerto(5000);
		connection.setListenerOfMessages(this);
		connection.start();
        title.setText("WAITING...");
		stopBtn.setDisable(true);
		id=0;
		
	}


    
	@FXML
	void stop(ActionEvent event) {


		Words words=new Words(id,nameAnswer.getText(),animalAnswer.getText(),locationAnswer.getText(),objectAnswer.getText());

		
		String json=gson.toJson(words);
		System.out.println(json);
		connection.getTransmitter().sendMessage(json);
	
	}
    
	private void loadWindow2() throws IOException {
		FXMLLoader fxmload = new FXMLLoader(getClass().getResource("Ventana2.fxml"));
		Parent root=fxmload.load();
		Scene scene = new Scene(root,700,500);
		Stage stage=new Stage();
		stage.setScene(scene);
		stage.show();

	}



	@Override
	public void OnMessage(String json) {
				System.out.println("RECIBIDO: "+json);



				if (json.startsWith("[")) {

					Generic[] generics = gson.fromJson(json, Generic[].class);
				String type = generics[0].type;
				switch (type) {
				case "Student":
					Words[] result = gson.fromJson(json, Words[].class);
					break;
				}
					
				
				}else{
					Generic generic = gson.fromJson(json, Generic.class);
	
					switch (generic.type) {
					case "Match":
						Match match = gson.fromJson(json, Match.class);
						initGame(match.getId(),match.getLetter()+"");
						break;
					case "Stop":
						stop(null);
						break;
					}
				}
		

		System.out.println(json);
	}



	private void initGame(int id,String string) {
		

		Platform.runLater(() -> {
			title.setText("LETRA: "+string);
		});
		stopBtn.setDisable(false);
		this.id=id;
	}


}
