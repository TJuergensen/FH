package netcat.client;

import java.io.InputStream;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

public class netcatClient implements Runnable {

	private ActorSystem system;
	private String[] args;
	private InputStream in;

	public netcatClient(ActorSystem system, String[] args, InputStream in) {
		this.system = system;
		this.args = args;
		this.in = in;
		run();
	}

	public void run() {
		// System.out.println("Starting client...");
		ActorRef transmitter = system.actorOf(Transmitter.props(args[0], Integer.parseInt(args[1])),
				"netcat-transmitter");
		System.out.println("Reader gonna be created");
		Reader reader = new Reader(transmitter, in);
		reader.read();

		system.terminate();
		// System.exit(0);
	}
}
