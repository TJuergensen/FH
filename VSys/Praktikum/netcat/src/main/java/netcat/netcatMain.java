package netcat;

import java.io.IOException;

import akka.actor.ActorSystem;
import netcat.client.netcatClient;
import netcat.server.netcatServer;

public class netcatMain {

	public static void main(String[] args) throws IOException {
		ActorSystem system = ActorSystem.create("netcat");
		switch (args.length) {
		case 1:
			startServer(system, args);
			break;
		case 2:
			startClient(system, args);
			break;

		default:
			System.out.println("Wrong usage. Use either <port> or <host> <port>");
			system.terminate();
			System.exit(1);
		}
	}

	private static void startServer(ActorSystem system, String[] args) throws IOException {
		//System.out.println("Starting Server...");
		@SuppressWarnings("unused")
		netcatServer server = new netcatServer(system, args, System.out);
		//server.run();
		System.exit(0);
	}

	private static void startClient(ActorSystem system, String[] args) throws IOException {
		@SuppressWarnings("unused")
		netcatClient client = new netcatClient(system, args, System.in);
		//client.run();
		System.exit(0);
	}
}
