package ADTQueue;

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
		
	    for (int i=0; i< li.size(); i++)
		{
		    enQueue(li.get(i));
		}
		
	}

	@Override
	public void deQueue(int k) {
		int qSize = toList().size();
	    if(k>qSize)
		k=qSize;
	    
	    for(int i=0; i<k; i++) {
		deQueue();
		}
		
	}

	@Override
	public List<E> deQueueFront(int k) {
		List<E> qList = toList();
		int qSize = qList.size();
		    if(k>qSize)
			k=qSize;
		    
		    
		    for(int i=0; i<k; i++) {
			qList.add(deQueueFront());
			}
	return qList;
	}

	@Override
	public List<E> deQueueFrontAll() {
	    
	return deQueueFront(toList().size());
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
