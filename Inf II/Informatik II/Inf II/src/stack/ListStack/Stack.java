package stack.ListStack;

public interface Stack<E> {
	
	boolean isEmpty();
	void push(E e);
	void pop();
	E top();
	E poptop();

}
