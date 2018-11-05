package netcat.client;

import java.io.InputStream;
import java.util.Scanner;

import akka.actor.ActorRef;
import util.UDPSocket;

public class Reader {

	private Scanner input;
	private final String EOF = "\u0004";
	ActorRef trans;
	String msg;
	InputStream in;

	public Reader(ActorRef trans, InputStream in) {
		
		System.out.println("EY");
		input = new Scanner(in);
		this.trans = trans;
		msg = "";
	}

	public void read() {

		while (msg != EOF) {
			System.out.println("In True");
			if (input.hasNextLine()) {
				System.out.println("Next Line");
				msg = input.nextLine();
				trans.tell(msg, ActorRef.noSender());
				System.out.println(msg);
			} else {
				System.out.println("EOF");
				trans.tell(UDPSocket.EOF, ActorRef.noSender());
				input.close();
				break;
			}
		}

	}
}
