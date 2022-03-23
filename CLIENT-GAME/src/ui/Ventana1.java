package ui;

import java.net.URL;
import java.util.ResourceBundle;

import com.google.gson.Gson;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Generic;
import model.Match;
import model.Words;
import model.comm.TCPConnection;
import model.comm.Receptor.OnMessageListener;

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
		connection.getEmisor().sendMessage(json);
		
	
	}
    
	@Override
	public void OnMessage(String json) {
		Generic generic = gson.fromJson(json, Generic.class);
				System.out.println(json);
				System.out.println(generic.type);

				switch (generic.type) {
				case "Match":
					Match match = gson.fromJson(json, Match.class);
					initGame(match.getId(),match.getLetter()+"");
					break;
				case "Stop":
					//Course cs = gson.fromJson(json, Course.class);
					//System.out.println(cs.getName());
					break;
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
