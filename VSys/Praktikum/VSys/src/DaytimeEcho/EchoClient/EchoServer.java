package DaytimeEcho.EchoClient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class EchoServer {
	
	public static final int BUFSIZE = 508;
	public static void main(String[] args) {
		if(args.length != 1) {
			System.err.println("Usage: JavaEcho");
			return;
		}
		
		int port = Integer.parseInt(args[0]);
		
		try(DatagramSocket socket = new DatagramSocket(port)) {
		
			DatagramPacket packet = new DatagramPacket(new byte[BUFSIZE], BUFSIZE);
			
			while(true) {
				socket.receive(packet);
				//System.out.println("Recieved: " + packet.getLength() + " bytes");
				socket.send(packet);
			}
		} catch (IOException e) {System.err.println(e);}
	}

}
