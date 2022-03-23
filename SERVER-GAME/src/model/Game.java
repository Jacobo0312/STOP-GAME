package model;


import java.util.UUID;

import com.google.gson.Gson;

import comm.Client;
import comm.Receiver.OnMessageListener;

public class Game implements OnMessageListener{

private Gson gson = new Gson();
private Client player1;
private Client player2;
private String id;


public Game(Client player1, Client player2) {
    this.player1 = player1;
    this.player2 = player2;
    this.id=UUID.randomUUID().toString();
}



public void initGame(){


    Match match=new Match();

    match.setId(1);
    String json=gson.toJson(match);
    player1.getTransmitter().sendMessage(json);

    match.setId(2);
    json=gson.toJson(match);
    player2.getTransmitter().sendMessage(json);


    player1.getReceiver().setListener(this);
    player2.getReceiver().setListener(this);
}


public Client getPlayer1() {
    return player1;
}


public void setPlayer1(Client player1) {
    this.player1 = player1;
}


public Client getPlayer2() {
    return player2;
}


public void setPlayer2(Client player2) {
    this.player2 = player2;
}


public String getId() {
    return id;
}


public void setId(String id) {
    this.id = id;
}


@Override
public void OnMessage(String json) {
    System.out.println("GAME: "+id);
    System.out.println("Recibido: "+json);

    Generic generic = gson.fromJson(json, Generic.class);
    System.out.println(json);
    System.out.println(generic.type);

    switch (generic.type) {
    case "Words":
        Words words = gson.fromJson(json, Words.class);
        System.out.println(words);
        //Enviar arreglo de respuestas
        break;

    }
    
    
}





    
}
