package netcat.server;

import java.io.IOException;
import java.io.PrintStream;

import netcat.server.Reciever;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

public class netcatServer implements Runnable {
	private ActorSystem system;
	private String[] args;
	private PrintStream out;
	
	public netcatServer(ActorSystem system, String[] args, PrintStream out)
	{
		this.system = system;
		this.args = args;
		this.out = out;
		run();
	}
	
	public void run()
	{
		try {
			ActorRef printer = system.actorOf(Printer.props(out), "netcat-printer");
			Reciever rcv = new Reciever(Integer.parseInt(args[0]), printer);
			rcv.recieve();

		} catch (IOException e) {
			System.out.println("Ow...something went terribly wrong! the system failed! o.O");
			System.err.println(e);
			system.terminate();
//			System.exit(0);
		}
	}

}
