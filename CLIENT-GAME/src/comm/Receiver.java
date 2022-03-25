package model.comm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Receiver extends Thread{
	
	
	private InputStream is; 
	public OnMessageListener listener;
	
	
	public Receiver(InputStream is) {
		this.is = is;
	}
	
	@Override
	public void run() {
		try {
		
			BufferedReader breader = new BufferedReader(new InputStreamReader(this.is));
			
			while(true) {	
				String msg = breader.readLine();
				if(listener != null) listener.OnMessage(msg);
				else System.out.println("No hay observer");
			}
		
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public void setListener(OnMessageListener listener) {
		this.listener = listener;
	}
	
	
	public interface OnMessageListener{
		public void OnMessage(String msg); 
	}
	

}
