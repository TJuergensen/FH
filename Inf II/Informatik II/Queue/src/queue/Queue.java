package queue;

import java.util.List;

public interface Queue<E> {
	
	boolean isEmpty();
	
	void enQueue(E e);
	
	void deQueue();
	
	E front();
	
	E deQueueFront();
	
	void enQueueAll(List<E> li);
	
	void deQueue(int k);
	
	List<E> deQueueFront(int k);
	
	List<E> deQueueFrontAll();
	
	E[] toArray();
	
	List<E> toList();
	
	boolean isEqualTo(Queue<E> q);

}
