package queue.StackQueue;

import java.util.List;

import queue.AbstractQueue;
import queue.QueueError;
import stack.ListStack.ListStack;

public class QueueStack<E> extends AbstractQueue<E> {
	

	ListStack<E> in;
	ListStack<E> out;
	
	public QueueStack()
	{
		super();
		in = new ListStack<E>();
		out = new ListStack<E>();
	}

	public void enQueue(E e) {
		in.push(e);
	}

	@Override
	public boolean isEmpty() {
		emptyIn();
		return out.isEmpty();
	}

	private void emptyIn() {
		while (!in.isEmpty()) {
			out.push(in.poptop());
		}
	}

	@Override
	public void deQueue() {
		if (in.isEmpty() && out.isEmpty())
			throw new QueueError("Its Empty already");

		if (out.isEmpty()) {
			emptyIn();
		}
		out.pop();
	}

	@Override
	public E front() {
		emptyIn();
		return out.top();
	}

	@Override
	public E[] toArray() {
		emptyIn();
		return out.toArray();
	}

	@Override
	public List<E> toList() {
		emptyIn();
		return out.toList();
	}
}
