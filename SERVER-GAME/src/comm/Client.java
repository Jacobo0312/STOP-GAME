package comm;

import java.net.Socket;

public class Client {
     
    private Socket socket;
    private Receiver receiver;
    private Transmitter transmitter;


    public Client(Socket socket, Receiver receiver, Transmitter transmitter) {
        this.socket = socket;
        this.receiver = receiver;
        this.transmitter = transmitter;
    }


    public Socket getSocket() {
        return socket;
    }


    public void setSocket(Socket socket) {
        this.socket = socket;
    }


    public Receiver getReceiver() {
        return receiver;
    }


    public void setReceiver(Receiver receptor) {
        this.receiver = receptor;
    }


    public Transmitter getTransmitter() {
        return transmitter;
    }


    public void setTransmitter(Transmitter transmitter) {
        this.transmitter = transmitter;
    }


    @Override
    public String toString() {
        return "Client: \nReceiver:" + receiver + "\n Socket:" + socket + "Transmitter:" + transmitter + "\n";
    }


    


    
    


    
}
