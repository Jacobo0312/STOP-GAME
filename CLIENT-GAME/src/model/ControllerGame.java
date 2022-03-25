package model;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.google.gson.Gson;

import javafx.application.Platform;
import javafx.scene.paint.Stop;
import model.Generic;
import model.Match;
import model.Words;
import model.comm.TCPConnection;
import model.comm.Receiver.OnMessageListener;

public class ControllerGame implements OnMessageListener{

  
	private int id;
    private String idGame;

    private TCPConnection connection;
	private Gson gson=new Gson();
	private InitGameListener initGameListener;
	private StopGameListener stopGameListener;
	private LoadResultsListener loadResultsListener;
	private Words[] results;


		
	private static ControllerGame instance = null;
	private ControllerGame() {
		connection = TCPConnection.getInstance();
		connection.setIp("127.0.0.1");
		connection.setPuerto(5000);
		connection.setListenerOfMessages(this);
		connection.start();
		id=0;
        idGame="TEST";
	}

	public static synchronized ControllerGame getInstance() {
		
		if(instance == null) {
			instance = new ControllerGame();
		}
		
		return instance;
		
	}


/*
	public ControllerGame() {
		connection = TCPConnection.getInstance();
		connection.setIp("127.0.0.1");
		connection.setPuerto(5000);
		connection.setListenerOfMessages(this);
		connection.start();
		id=0;
        idGame="TEST";
		
	}
	*/

	public void setInitGameListener(InitGameListener listener) {
		this.initGameListener = listener;
		
	}

	public void setLoadResultsListener(LoadResultsListener listener) {
		this.loadResultsListener = listener;
		
	}

	public void setStopGameListener(StopGameListener listener) {
		this.stopGameListener = listener;
		
	}


    
	
	public void stop(int id,String nameAnswer,String animalAnswer,String locationAnswer,String objectAnswer) {


		Words words=new Words(id,nameAnswer,animalAnswer,locationAnswer,objectAnswer);

		String json=gson.toJson(words);
		System.out.println(json);
		connection.getTransmitter().sendMessage(json);
	
	}
    




	@Override
	public void OnMessage(String json) {
				System.out.println("RECIBIDO: "+json);

				if (json.startsWith("[")) {
					loadResultsListener.LoadResults();
					Generic[] generics = gson.fromJson(json, Generic[].class);
					String type = generics[0].type;
				switch (type) {
				case "Words":
					results = gson.fromJson(json, Words[].class);
					break;
				}

				}else{
					Generic generic = gson.fromJson(json, Generic.class);
					switch (generic.type) {
					case "Match":
						Match match = gson.fromJson(json, Match.class);
						initGameListener.initGame(match.getId(),match.getLetter()+"");
						break;
					case "Stop":
						stopGameListener.stop();
						break;
					}
				}
		

		System.out.println(json);
	}



	public interface InitGameListener {
		public void initGame(int id,String letter);
	
	}


	public interface StopGameListener {
		public void stop();
	
	}

	
	public interface LoadResultsListener {
		public void LoadResults();
	
		
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Words[] getResults() {
		return results;
	}

	public void setResults(Words[] results) {
		this.results = results;
	}

	



}


