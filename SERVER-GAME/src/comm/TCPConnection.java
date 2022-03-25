package comm;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import comm.Receiver.OnMessageListener;

public class TCPConnection extends Thread {

	private static TCPConnection instance = null;

	private TCPConnection() {
	}

	public static synchronized TCPConnection getInstance() {

		if (instance == null) {
			instance = new TCPConnection();
		}
		return instance;

	}

	private ServerSocket server;
	private Socket socket;
	private int puerto;
	private OnMessageListener listener;
	private NewClientListener newClientListener;

	public void setPuerto(int puerto) {
		this.puerto = puerto;
	}

	@Override
	public void run() {
		try {
			server = new ServerSocket(puerto);
			while (true) {
				System.out.println("Waiting client");
				socket = server.accept();
				System.out.println("Accept client");

				Receiver receiver = new Receiver(socket.getInputStream());
				receiver.setListener(listener);
				receiver.start();

				Transmitter transmitter = new Transmitter(socket.getOutputStream());

				Client client = new Client(socket, receiver, transmitter);
				newClientListener.newClient(client);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setListenerOfMessages(OnMessageListener listener) {
		this.listener = listener;
	}

	public void setNewClientListener(NewClientListener newClientListener) {
		this.newClientListener = newClientListener;
	}

	public interface NewClientListener {
		public void newClient(Client client);
	}

  
}
