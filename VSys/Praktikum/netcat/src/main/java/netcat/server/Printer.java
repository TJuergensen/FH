package netcat.server;

import java.io.PrintStream;

import akka.actor.AbstractActor;
import akka.actor.Props;
import util.UDPSocket;

public class Printer extends AbstractActor {

	public static Props props(PrintStream out) {
		return Props.create(Printer.class, ()-> new Printer(out));
	}
	
	public Printer(PrintStream out)
	{
		System.setOut(out);
	}

	@Override
	public void preStart() throws Exception {
		//System.out.println("Receiver has started. Waiting for some Input by client.");
	}

	@Override
	public void postStop() throws Exception {
		//System.out.println("Reciever has shut down.");
	}

	@Override
	public Receive createReceive() {
		return receiveBuilder().match(String.class, r -> {
			if (r.equals(UDPSocket.EOF)) {
				getContext().stop(getSelf());
				postStop();
			} else {
				System.out.println(r);
			}
		}).build();
	}

}
