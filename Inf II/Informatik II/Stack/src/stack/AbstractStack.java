package stack;

import java.util.List;
import stack.ListStack.Stack;

public abstract class AbstractStack<E> implements Stack<E> {

    public E poptop() {
	if (isEmpty()) {
	    throw new StackError("Cannot do. Stack is empty");
	} else {
	    E a = top(); // O(1)
	    pop(); // O(1)
	    return a;
	} //O(1)
    }

    public boolean isEqualTo(Stack<E> s) {
	if (isEmpty() != s.isEmpty())
	    return false; // O(1)
	if (isEmpty() && s.isEmpty())
	    return true; // O(1)

	boolean ret = true;
	List<E> otherStackAsList = s.toList(); // O(n)
	int i = 0;
	while (!isEmpty()) { //O(n)
	    if (otherStackAsList.get(i) != null && top() == otherStackAsList.get(i)) {
		pop(); //O(1)
		i++;
	    } else {
		// refill the stack and return false
		for (int j = i; j >= 0; i--) {
		    push(otherStackAsList.get(j));
		}
		ret = false;
		break;
	    }
	}
	// Refill the empty Stack
	if (ret) { //O(n)
	    for (int j = otherStackAsList.size()-1; j >= 0; j--) {
		push(otherStackAsList.get(j));
	    }
	}
	return ret;
    } //O(n)
}
