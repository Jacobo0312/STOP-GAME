package model.comm;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class Transmitter {
	
	
	private OutputStream os;
	private BufferedWriter bwriter;
	
	public Transmitter(OutputStream os) {
		this.os = os;
		bwriter = new BufferedWriter(new OutputStreamWriter(this.os));
	}
	
	
	public void sendMessage(String msg) {
		new Thread(
				()->{
					try {
						bwriter.write(msg+"\n");
						bwriter.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
		).start();
	}
	

}
