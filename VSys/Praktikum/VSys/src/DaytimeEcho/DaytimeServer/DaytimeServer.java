package DaytimeEcho.DaytimeServer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DaytimeServer {
	public static final int BUFSIZE = 508;
	
	public static void main(String[] args) {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		
		int port = Integer.parseInt(args[0]);
		
		try(DatagramSocket socket = new DatagramSocket(port)) {
			
			DatagramPacket packet = new DatagramPacket(new byte[BUFSIZE], BUFSIZE);
			int len = packet.getLength();
			
			while(true)
			{
				socket.receive(packet);
				System.out.println("Received: " + packet.getLength() + " bytes");
				
				if(packet.getLength() == len) 
				{
					String currentTime = dateFormat.format(new Date());
					packet.setData(currentTime.getBytes());
					socket.send(packet);
					//System.out.println("Sent: " + new String(packet.getData(), 0, packet.getLength()));
				}
				
			}
			
		} catch (IOException e) {System.err.println(e);}
	} 
	

}
