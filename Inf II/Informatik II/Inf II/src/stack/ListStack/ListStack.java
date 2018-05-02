package stack.ListStack;

import linkedList.LinkedList;
import stack.AbstractStack;


public class ListStack<E> extends AbstractStack<E> {

	private E top;
	private LinkedList<E> list;
	ListStack()
	{
		super();
		list  = new LinkedList<E>();
		list.add(top);
	}

	@Override
	public boolean isEmpty()
	{
		return top == null;
	}

	@Override
	public void push(E e)
	{
		list.add(e);
	}

	@Override
	public void pop()
	{
		list.remove(top);
		
	}

	@Override
	public E top()
	{
		return top;
	}

	@Override
	public E poptop()
	{
		pop();
		return top();
	}
}
