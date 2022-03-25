package model.comm;

import java.io.IOException;
import java.net.Socket;

import model.comm.Receiver.OnMessageListener;

public class TCPConnection extends Thread{

	
	
	private static TCPConnection instance = null;
	private TCPConnection() {}
	public static synchronized TCPConnection getInstance() {
		
		if(instance == null) {
			instance = new TCPConnection();
		}
		
		return instance;
		
	}
	
	private Socket socket;
	private String ip;
	private int puerto;
	private Receiver receiver;
	private Transmitter transmitter;
	private OnMessageListener listener;
	
	
	
	public void setPuerto(int puerto) {
		this.puerto = puerto;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	@Override
	public void run() {
		searchServer();
	}


	public void searchServer(){
		try {
			
			
			System.out.println("Esperando un servidor");
			socket = new Socket(ip, puerto);
			System.out.println("Cliente conectado");
			
			receiver = new Receiver(socket.getInputStream());
			receiver.setListener(listener);
			receiver.start();
			
			transmitter = new Transmitter(socket.getOutputStream());
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void setListenerOfMessages(OnMessageListener listener) {
		this.listener = listener;
		
	}
	
	public Transmitter getTransmitter() {
		return this.transmitter;
	}
	
	
	
	


}
