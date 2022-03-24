package ui;

import java.net.URL;
import java.util.ResourceBundle;

import com.google.gson.Gson;

import javafx.css.Match;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Generic;
import model.Words;
import model.comm.TCPConnection;
import model.comm.Receiver.OnMessageListener;

public class Ventana2 implements Initializable,OnMessageListener {

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

    private Words []results=new Words[2];

    private Gson gson = new Gson();

    private TCPConnection connection;

    

   


    public Words[] getResults() {
        return results;
    }




    public void setResults(Words[] results) {
        this.results = results;
    }




    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		connection = TCPConnection.getInstance();
		connection.setListenerOfMessages(this);
		
	}




    @Override
    public void OnMessage(String msg) {
        System.out.println("VENTANA 2: "+msg);
        
    }

}
