package stack.ArrayStack;

import java.util.ArrayList;
import java.util.List;
import stack.AbstractStack;
import stack.StackError;

public class ArrayStack<E> extends AbstractStack<E> {

    private E[] array;
    private int top;
    private final int CAPACITY;

    @SuppressWarnings("unchecked")
    public ArrayStack(final int size) {
	super();
	CAPACITY = size;
	array = (E[]) new Object[CAPACITY];
	top = -1;
    }

    public boolean isFull() {
	return top == CAPACITY - 1;
    }// O(1)

    @Override
    public boolean isEmpty() {
	return top == -1;
    } // O(1)

    @SuppressWarnings("unchecked")
    @Override
    public void push(E e) {
	if (isFull()) {
	    // throw new StackError("Cannot do. Stack is full.");
	    List<E> copy = toList();
	    copy.add(0, e);
	    array = null;
	    array = (E[]) new Object[CAPACITY * 2];
	    for (int i = copy.size() - 1; i >= 0; i--) {
		push(copy.get(i));
	    }
	} else {
	    array[++top] = e; // O(1) + O(1)
	}

    } // O(1)

    @Override
    public void pop() {
	if (isEmpty()) {
	    throw new StackError("Cannot do. Stack is empty.");
	} else {
	    array[top--] = null;
	} // O(1)
    }

    @Override
    public E top() {
	if (isEmpty()) {
	    throw new StackError("Cannot do. Stack is empty.");
	} else {
	    return array[top];
	} // O(1)
    }

    @Override
    public List<E> toList() {
	List<E> retList = new ArrayList<E>();
	ArrayStack<E> tempStack = new ArrayStack<E>(CAPACITY);
	while (!isEmpty()) { // O(n), mit n=Elemente im Stack, max CAPACITY
	    retList.add(top());
	    tempStack.push(poptop()); // O(1)+O(1)

	} // O(n)
	  // Refill the original Stack
	while (!tempStack.isEmpty()) { // O(n)
	    push(tempStack.poptop());// O(1)+O(1)
	} // O(n)

	return retList;
    }// O(n)
}
