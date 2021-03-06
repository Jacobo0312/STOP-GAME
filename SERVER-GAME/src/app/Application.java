package app;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import comm.Client;
import comm.Receiver.OnMessageListener;
import comm.TCPConnection.NewClientListener;
import model.Game;
import model.Game.FinishGameListener;
import comm.TCPConnection;

public class Application implements OnMessageListener, NewClientListener, FinishGameListener {

	private TCPConnection connection;
	private ArrayList<Game> games;
	private Queue<Client> clients;

	public Application() {

		connection = TCPConnection.getInstance();
		connection.setPuerto(5000);
		connection.setListenerOfMessages(this);
		connection.setNewClientListener(this);
		clients = new LinkedList<Client>();
		games = new ArrayList<Game>();

	}

	public void init() {
		connection.start();
	}

	@Override
	public void OnMessage(String json) {
		System.out.println("SERVER: " + json);
	}

	@Override
	public void newClient(Client client) {
		clients.add(client);
		// System.out.println(clients);
		if (clients.size() >= 2)
			startNewGame();
	}

	private void startNewGame() {
		Game game = new Game(clients.poll(), clients.poll());
		game.setFinishGameListener(this);
		games.add(game);
		game.initGame();
	}

	@Override
	public void finishGame(Game game) {
		games.remove(game);
	}

}
