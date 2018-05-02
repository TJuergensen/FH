package stack.ArrayStack;

import stack.StackError;
import stack.ListStack.Stack;

public class ArrayStack<E> implements Stack<E> {

	private E[] array;
	private int top;
	private static final int CAPACITY = 10;

	ArrayStack()
	{
		array = (E[]) new Object[CAPACITY];
		top = -1;
	}

	public boolean isFull()
	{
		return top == 9;
	}

	@Override
	public boolean isEmpty()
	{
		return top == -1;
	}

	@Override
	public void push(E e)
	{
		if (isFull())
		{
			throw new StackError("Cannot do. Stack is full.");
		} else
		{
			array[++top] = e;
		}

	}

	@Override
	public void pop()
	{
		if(isEmpty())
		{
			throw new StackError("Cannot do. Stack is empty.");
		} else {
			array[top--] = null;
		}
		
	}

	@Override
	public E top()
	{
		if(isEmpty())
		{
			throw new StackError("Cannot do. Stack is empty.");
		} else {
			return array[top];
		}
	}

	@Override
	public E poptop()
	{
		if(isEmpty())
		{
			throw new StackError("Cannot do. Stack is empty");
		} else {
			pop();
			return top();
		}
	}

}
