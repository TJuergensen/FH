package queue.ListQueue;

import java.util.List;

import queue.AbstractQueue;
import queue.QueueError;

class Cell<E> {
	E data;
	Cell<E> next = null;

	Cell(E e) {
		data = e;
	}
}

public class ListQueue<E> extends AbstractQueue<E> {
	
	private Cell<E> ende;
	
	public ListQueue()
	{
		super();
	}
	
	 public boolean isEmpty() { return ende == null;}
	 
	 public void deQueue() {
	 if (isEmpty()) throw new QueueError("Queue is empty!");
	 return;
	 }

	@Override
	public void enQueue(E e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public E front() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E deQueueFront() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void enQueueAll(List<E> li) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deQueue(int k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<E> deQueueFront(int k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<E> deQueueFrontAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}


}
