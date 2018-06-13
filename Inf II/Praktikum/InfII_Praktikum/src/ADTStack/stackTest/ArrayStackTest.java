package ADTStack.stackTest;

import ADTStack.ArrayStack.ArrayStack;

public class ArrayStackTest {

    public static void main(String[] args) {
	String testString = "This is a String for testing issues";
	ArrayStack<Character> stack = new ArrayStack<Character>(testString.length());

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
	ArrayStack<Character> tempStack = new ArrayStack<Character>(testString.length());
	while (!stack.isEmpty()) {
	    System.out.print(stack.top());
	    tempStack.push(stack.poptop());
	}
	// Break Link
	System.out.println("");

	// refill original stack
	while (!tempStack.isEmpty()) {
	    stack.push(tempStack.poptop());

	}

	tempStack = null;

	System.out.println("State of original Stack (empty=true): " + stack.isEmpty());

    }

}
