package stackTest;

import stack.ListStack.ListStack;

public class ListStackTest {

    public static void main(String[] args) {
	String testString = "This is a String for testing issues";
	ListStack<Character> stack = new ListStack<Character>();

	// push String character by character onto the Stack and show what is inserted
	for (int i = 0; i < testString.length(); i++) {
	    stack.push(testString.charAt(i));
	    System.out.print(testString.charAt(i));
	}
	// Break Line
	System.out.println("");
	/***
	 * Read the elements on the stack. Note that Items cannot be read straight
	 * ahead; the stack has to be copied, popped and topped
	 */
	ListStack<Character> tempStack = new ListStack<Character>();
	while (!stack.isEmpty()) {
	    System.out.print(stack.top());
	    tempStack.push(stack.top());
	    stack.pop();
	}
	// Break Link
	System.out.println("");

	// refill original stack
	while (!tempStack.isEmpty()) {
	    stack.push(tempStack.top());
	    tempStack.pop();
	}

	tempStack = null;

	System.out.println("State of original Stack (empty=true): " + stack.isEmpty());

    }
}
