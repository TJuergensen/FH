package stack.ListStack;

import java.util.LinkedList;
import java.util.List;

import stack.AbstractStack;
import stack.StackError;

public class ListStack<E> extends AbstractStack<E> {

    private Cell<E> top;

    public ListStack() {
	super();
	top = null;
    }

    @Override
    public boolean isEmpty() {
	return top == null;
    } // O(1)

    @Override
    public void push(E e) {
	Cell<E> newTop = new Cell<E>(e);
	newTop.setNext(top);
	top = newTop;
    } // O(1)

    @Override
    public void pop() {
	if (isEmpty()) {
	    throw new StackError("Not possible. Stack is empty already");
	} else if (top.getNext() == null) {
	    top = null;
	} else {
	    top = top.getNext();
	}
    } // O(1)

    @Override
    public E top() {
	if (isEmpty()) {
	    throw new StackError("Not possible. Stack is empty");
	} else {
	    return top.getData();
	}
    } // O(1)

    @Override
    public List<E> toList() {
	List<E> retList = new LinkedList<E>();
	ListStack<E> tempStack = new ListStack<E>();
	while (!isEmpty()) { //O(n)
	    retList.add(top());
	    tempStack.push(top());//O(1) + O(1)
	    pop(); //O(1)
	}

	while (!tempStack.isEmpty()) { //O(n)
	    push(tempStack.poptop()); //O(1+1)
	}
	return retList;
    } //O(n)

    @Override
    public void multipop(int k) {
	// TODO Auto-generated method stub
	
    }

}
