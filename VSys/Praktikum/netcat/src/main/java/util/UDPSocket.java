package util;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPSocket {

	public static final String EOF ="\u0004";
	
	private final int port;
	private final String host;
	private DatagramSocket socket;

	public UDPSocket(int port, String host) throws SocketException {
		this.port = port;
		this.host = host;
		socket = new DatagramSocket();
	}

	public UDPSocket(int port) throws SocketException {
		this.port = port;
		this.host = null;
		socket = new DatagramSocket(port);
	}

	public void send(String msg){
		try {	
			byte[] msgAsBytes = stringToBytes(msg);
			InetAddress addr = InetAddress.getByName(host);
			DatagramPacket out = new DatagramPacket(msgAsBytes, msgAsBytes.length, addr, port);
			socket.send(out);
		} catch (IOException e) {
			System.err.println(e);
		}

		return;
	}

	public String recieve(int maxBytes) throws IOException {
		DatagramPacket in = new DatagramPacket(new byte[maxBytes], maxBytes);
		socket.receive(in);
		return bytesToString(in);
	}
	
	public void closeSocket()
	{
		socket.close();
		//System.out.println("Socket closed");
	}

	private byte[] stringToBytes(String s) {
		return s.getBytes();
	}

	
	private String bytesToString( DatagramPacket in)
	{
		return new String(in.getData(),0,in.getLength());
	}

}
