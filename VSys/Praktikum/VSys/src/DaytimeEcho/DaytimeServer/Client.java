package DaytimeEcho.DaytimeServer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
	
	public static final int BUFSIZE = 508;
	public static void main(String[] args) {
		if(args.length != 2)
		{
			System.err.println("Wrong usage"); return;
		}
		
		
		String message = "TESTYTEST";
		byte[] messageAsBytes = message.getBytes();
		String host = args[0];
		int port = Integer.parseInt(args[1]);
		
		try(DatagramSocket socket = new DatagramSocket()){
			InetAddress addr = InetAddress.getByName(host);
			
			//DatagramPacket out = new DatagramPacket(messageAsBytes,messageAsBytes.length, addr, port);
			DatagramPacket out = new DatagramPacket(new byte[BUFSIZE], BUFSIZE, addr, port);
			socket.send(out);
			
//			//Recieve answer
			DatagramPacket in = new DatagramPacket(new byte[BUFSIZE], BUFSIZE);
			socket.receive(in);
//			
//			//Store and print answer
			String recieved = new String(in.getData(), 0, in.getLength());
			System.out.println(recieved);
		} catch(IOException e) {System.err.println(e);}
	}

}
