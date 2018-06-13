package ADTQueue.queueTest;

import java.util.List;

import ADTQueue.ListQueue.ListQueue;

public class ListTest {
	
	public static void main(String[] args) {
		String testString = "This is a String for testing issues";
		ListQueue<Character> queue = new ListQueue<Character>();

		// enqueue String character by character onto the queue and show what is inserted
		for (int i = 0; i < testString.length(); i++) {
		    queue.enQueue(testString.charAt(i));
		    System.out.print(testString.charAt(i));
		}
		// Break Line
		System.out.println("");
		/***
		 * Read the elements on the queue. Note that Items cannot be read straight
		 * ahead; the queue has to be copied, popped and topped
		 */
		ListQueue<Character> tempQueue = new ListQueue<Character>();
		while (!queue.isEmpty()) {
		    System.out.print(queue.front());
		    tempQueue.enQueue(queue.deQueueFront());
		}
		// Break Link
		System.out.println("");

		// refill original queue
		while (!tempQueue.isEmpty()) {
		    queue.enQueue(tempQueue.deQueueFront());
		}
		
		System.out.println("\ntoList() test. Should contain same String from queue: ");
		//toArray Test
		List<Character> charTest = queue.toList();
		for(int i=0; i < charTest.size(); i++) {
			System.out.print(charTest.get(i));
		}
		tempQueue = null;
		System.out.println("\n");

		System.out.println("State of original queue (empty=true): " + queue.isEmpty());

	    }

}
