package netcat_Test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public abstract class NcTest {

//	@SuppressWarnings("unused")
//	private Object[] inputs() {
//		return new Object[] {
//				new Object[] {"Hallo\n"},
//				new Object[] {"Hallo\n Hallo\n"},
//				new Object[] {"Hallo\n Hallo\n Hallo\n"}
//		};
//	}
	
	@SuppressWarnings("unused")
	private String[] inputs() {
		return new String[] {
				"Hallo\n",
				"Hallo\n Hallo\n",
				"Hallo\n Hallo\n Hallo\n"
		};
	}
	
	
	
	private Thread clientThread;
	private Thread serverThread;
	
	protected abstract Thread createClient(InputStream in);
	protected abstract Thread createServer(PrintStream out);
	
	@Test
	@Parameters(method="inputs")
	public void Test(String input) throws Exception
	{
		System.out.println("EYS");
		ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
		ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
		PrintStream outputStream = new PrintStream(byteOutput);
		System.out.println(byteOutput.toString());
		serverThread = createServer(outputStream);
		clientThread = createClient(inputStream);
		
		serverThread.start();
		Thread.sleep(1000);
		
		clientThread.start();
		clientThread.join();
		serverThread.join();
		
		String output = byteOutput.toString();
		
		assertEquals(output, input);
	}

}
