package queueTest;

import queue.ArrayQueue.ArrayQueue;

public class ArrayTest {

	public static void main(String[] args) {
		String testString = "This is a String for testing issues";
		ArrayQueue<Character> queue = new ArrayQueue<Character>(testString.length());

		// push String character by character onto the queue and show what is inserted
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
		ArrayQueue<Character> tempQueue = new ArrayQueue<Character>(testString.length());
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

		tempQueue = null;

		System.out.println("State of original queue (empty=true): " + queue.isEmpty());

	    }
}
