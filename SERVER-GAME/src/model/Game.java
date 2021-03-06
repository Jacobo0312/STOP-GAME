package model;

import java.util.UUID;
import com.google.gson.Gson;
import comm.Client;
import comm.Receiver.OnMessageListener;

public class Game implements OnMessageListener {

    private Gson gson = new Gson();
    private Client player1;
    private Client player2;
    private String id;
    private Words[] results = new Words[2];
    private FinishGameListener finishGameListener;

    public Game(Client player1, Client player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.id = UUID.randomUUID().toString();
    }

    public void initGame() {

        Match match = new Match();

        match.setId(1);
        String json = gson.toJson(match);
        player1.getTransmitter().sendMessage(json);

        match.setId(2);
        json = gson.toJson(match);
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
        //System.out.println("GAME: " + id);
        //System.out.println("Recibido: " + json);

        Generic generic = gson.fromJson(json, Generic.class);


        switch (generic.type) {
            case "Words":
                Words words = gson.fromJson(json, Words.class);

                results[words.getId() - 1] = words;
                json = gson.toJson(new Stop());

                if (!(results[0] != null && results[1] != null)) {
                    if (words.getId() == 1) {
                        player2.getTransmitter().sendMessage(json);
                    } else {
                        player1.getTransmitter().sendMessage(json);
                    }
                } else {

                    calculatePoints();
                    String jsonArray = gson.toJson(results);
                    player1.getTransmitter().sendMessage(jsonArray);
                    player2.getTransmitter().sendMessage(jsonArray);
                    finishGameListener.finishGame(this);
                }

                break;

        }

    }

    private void calculatePoints() {
        results[0].setPoints(400);
        results[1].setPoints(400);
        if (results[0].getName().equals(results[1].getName())) {
            results[0].subPoints(50);
            results[1].subPoints(50);
        }

        if (results[0].getAnimal().equals(results[1].getAnimal())) {
            results[0].subPoints(50);
            results[1].subPoints(50);
        }

        if (results[0].getSite().equals(results[1].getSite())) {
            results[0].subPoints(50);
            results[1].subPoints(50);
        }

        if (results[0].getThing().equals(results[1].getThing())) {
            results[0].subPoints(50);
            results[1].subPoints(50);
        }


    }


    public interface FinishGameListener {
    public void finishGame(Game game);
        
    }


    public void setFinishGameListener(FinishGameListener listener) {
        this.finishGameListener=listener;
    }


}
