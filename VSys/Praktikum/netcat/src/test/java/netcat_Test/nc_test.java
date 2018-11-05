package netcat_Test;

import java.io.InputStream;
import java.io.PrintStream;

import akka.actor.ActorSystem;
import netcat.client.netcatClient;
import netcat.server.netcatServer;

public class nc_test extends nc_uni_Test{

	@Override
	protected Thread createClient(InputStream in) {
		ActorSystem s1 = ActorSystem.create("NC-Test-Client");
		String[] args = {"127.0.0.1, 4444"};
		return new Thread(new netcatClient(s1, args, in));
	}

	@Override
	protected Thread createServer(PrintStream out) {
		ActorSystem s2 = ActorSystem.create("NC-Test-Server");
		String[] args = {"4444"};
		return new Thread(new netcatServer(s2,args,out));
	}

}
