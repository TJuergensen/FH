package netcat.server;

import java.io.IOException;
import java.net.SocketException;

import akka.actor.ActorRef;
import util.UDPSocket;

public class Reciever {
	
	private UDPSocket connection;
	private ActorRef printer;
	private String rcvMsg = "";
	
	public Reciever(int port, ActorRef printer) throws SocketException {
		connection = new UDPSocket(port);
		this.printer=printer;
		
		
		
		
	}
	
	public void recieve() throws IOException
	{
		while(true)
		{
			rcvMsg = connection.recieve(508);
			if(rcvMsg.equals(UDPSocket.EOF)) {break;}
			//System.out.println(rcvMsg);
			printer.tell(rcvMsg, ActorRef.noSender());
		}
		//System.out.println("Detected EoF");
		printer.tell(UDPSocket.EOF,ActorRef.noSender());
		connection.closeSocket();
	}

}

