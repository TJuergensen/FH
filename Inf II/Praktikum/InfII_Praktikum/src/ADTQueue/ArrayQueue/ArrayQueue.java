package ADTQueue.ArrayQueue;

import java.util.ArrayList;
import java.util.List;

import ADTQueue.AbstractQueue;
import ADTQueue.QueueError;

public class ArrayQueue<E> extends AbstractQueue<E> {
	private E[] array;
	private int size, start, end;
	private final int CAPACITY;
	
	@SuppressWarnings("unchecked")
	public ArrayQueue(final int stackSize) {
		super();
		CAPACITY = stackSize;
		array = (E[]) new Object[CAPACITY];
		start = 0; end = -1; size = 0;
		} 
	
	private int inc(int i) {
		if (++i == array.length) i = 0;
		return i;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void enQueue(E e) {
		if(size==CAPACITY) throw new QueueError("Queue is full, can't enqueue");
		end = inc(end);
		array[end] = e;
		size++; 
	}

	@Override
	public void deQueue() {
		if(isEmpty()) throw new QueueError("Queue is empty, can't dequeue");
		start = inc(start);
		size--;

	}

	@Override
	public E front() {
		return array[start];

	}

	@Override
	public E[] toArray() {
		return array;
	}

	@Override
	public List<E> toList() {
		ArrayList<E> list = new ArrayList<E>();
		for (int i=start; i<=end; i++)
		{
			list.add(array[i]);
		}
		return list;
	}

	
}
