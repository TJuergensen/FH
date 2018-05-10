package queue;

import java.util.List;

public abstract class AbstractQueue<E> implements Queue<E> {
	
	@Override
	public E deQueueFront() {
		E e = front();
		deQueue();
		return e;
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
	public boolean isEqualTo(Queue<E> q)
	{
		if(isEmpty() && q.isEmpty()) return true;
		
		List<E> l1 = toList();
		List<E> l2 = q.toList();
		if(l1.size() != l2.size()) return false;
		
		return l1.equals(l2);
	}

}
