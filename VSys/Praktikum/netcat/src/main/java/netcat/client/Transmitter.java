package netcat.client;

import java.net.SocketException;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.io.UdpConnected;
import util.UDPSocket;

public class Transmitter extends AbstractActor {

	public final UDPSocket connection;

	public static Props props(String host, int port) {
		return Props.create(Transmitter.class, () -> new Transmitter(host, port));

	}

	public Transmitter(String host, int port) throws SocketException {
		connection = new UDPSocket(port, host);

	    final ActorRef mgr = UdpConnected.get(getContext().getSystem()).getManager();

	}

	@Override
	public void preStart() throws Exception {
		//getContext().stop(getContext().getParent());
		//System.out.println("Transmitter started. Feel free to type something!");
	}

	@Override
	public void postStop() throws Exception {
		//System.out.println("Transmitter has shut down.");
		connection.closeSocket();
	}

	@Override
	public Receive createReceive() {
		return receiveBuilder().match(String.class, r -> {
			if (!r.equals(UDPSocket.EOF)) {
				connection.send(r);
			} else {
				connection.send(UDPSocket.EOF);
				getContext().stop(getSelf());
				postStop();
			}
			
		}).build();
	}
}
