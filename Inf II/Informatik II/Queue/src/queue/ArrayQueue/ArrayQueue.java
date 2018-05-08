package queue.ArrayQueue;

import java.util.List;

import queue.AbstractQueue;

public class ArrayQueue<E> extends AbstractQueue<E> {
	private E[] array;
	private int size, anfang, ende;
	private static final int CAPACITY = 10;
	
	@SuppressWarnings("unchecked")
	public ArrayQueue() {
		super();
		array = (E[]) new Object[CAPACITY];
		anfang = 0; ende = -1; size = 0;
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
		ende = inc(ende);
		array[ende] = e;
		size++; 
	}

	@Override
	public void deQueue() {
		anfang = inc(anfang);
		size--;

	}

	@Override
	public E front() {
		return array[anfang];

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
