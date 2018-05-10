package queueTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import queue.QueueError;
import queue.ArrayQueue.ArrayQueue;
import queue.ListQueue.ListQueue;

class ADTTest {

	@Test
	void equalMethods() {
		int x = 55;
		int y = 12;
		ListQueue<Integer> q = new ListQueue<Integer>();
		ListQueue<Integer> q1 = new ListQueue<Integer>();
		ArrayQueue<Integer> p = new ArrayQueue<Integer>(5);
		ArrayQueue<Integer> p1 = new ArrayQueue<Integer>(5);

		assertEquals(q.toList().isEmpty(), q.isEmpty());
		assertEquals(p.toList().isEmpty(), p.isEmpty());

		assertTrue(q.isEqualTo(q1));
		assertTrue(p.isEqualTo(p1));

		q.enQueue(x);
		assertFalse(q.isEqualTo(q1));
		p.enQueue(x);
		assertFalse(p.isEqualTo(p1));

		assertTrue(q.isEqualTo(p));

		q.enQueue(y);
		assertFalse(q.isEqualTo(q1));
		p.enQueue(y);
		assertFalse(p.isEqualTo(p1));
		assertTrue(q.isEqualTo(p));

	}

	// isEmpty(empty) = true
	@Test
	void empty_empty() {

		ListQueue<Integer> q = new ListQueue<Integer>();
		assertTrue(q.toList().isEmpty());
		assertTrue(q.isEmpty());

		ArrayQueue<Integer> p = new ArrayQueue<Integer>(5);
		assertTrue(p.toList().isEmpty());
		assertTrue(p.isEmpty());
	}

	// isEmpty(enQueue(x,q)) = false
	@Test
	void empty_enqueue() {
		ListQueue<Integer> q = new ListQueue<Integer>();
		assertTrue(q.isEmpty());
		q.enQueue(5);
		assertFalse(q.isEmpty());

		ArrayQueue<Integer> p = new ArrayQueue<Integer>(1);
		assertTrue(p.isEmpty());
		p.enQueue(5);
		assertFalse(p.isEmpty());
	}

	// deQueue(enQueue(x,empty)) = empty
	@Test
	void dequeue_enqueue() {
		ListQueue<Integer> q = new ListQueue<Integer>();
		q.enQueue(5);
		assertFalse(q.isEmpty());
		q.deQueue();
		assertTrue(q.isEmpty());

		ArrayQueue<Integer> p = new ArrayQueue<Integer>(5);
		p.enQueue(5);
		assertFalse(p.isEmpty());
		p.deQueue();
		assertTrue(p.isEmpty());
	}

	// front(enQueue(x,empty)) = x
	@Test
	void front_enqueue() {
		int x = 123;
		ListQueue<Integer> q = new ListQueue<Integer>();
		q.enQueue(x);
		assertTrue(q.front() == x);

		ArrayQueue<Integer> p = new ArrayQueue<Integer>(5);
		p.enQueue(x);
		assertTrue(p.front() == x);
	}

	// front(enQueue(y, enQueue(x,q))) = front(enQueue(x, q))
	@Test
	void front_enq_enq() {
		int x = 2131;
		int y = 5;
		ListQueue<Integer> q = new ListQueue<Integer>();
		ListQueue<Integer> q1 = new ListQueue<Integer>();
		q.enQueue(x);
		q.enQueue(y);

		q1.enQueue(x);
		assertEquals(q.front(), q1.front());

		ArrayQueue<Integer> p = new ArrayQueue<Integer>(5);
		ArrayQueue<Integer> p1 = new ArrayQueue<Integer>(5);
		p.enQueue(x);
		p.enQueue(y);

		p1.enQueue(x);
		assertEquals(p.front(), p1.front());
	}

	// deQueueFront(q) = front(q),ignore, falls q nicht leer
	@Test
	void dequeueFront() {
		ListQueue<Integer> q = new ListQueue<Integer>();
		ListQueue<Integer> q1 = new ListQueue<Integer>();
		int x = 2131;
		int y = 5;
		q.enQueue(x);
		q.enQueue(y);
		q1.enQueue(x);
		q1.enQueue(y);
		assertFalse(q.isEmpty());
		assertFalse(q1.isEmpty());
		assertEquals(q.deQueueFront(), q1.front());
	}

	// front(empty) = error
	@Test
	void front_empty() {
		ListQueue<Integer> q = new ListQueue<Integer>();
		assertTrue(q.isEmpty());

		assertThrows(QueueError.class, () -> {
			q.front();
		});
	}

	// deQueue(empty) = error
	@Test
	void dequeue_empty() {
		ListQueue<Integer> q = new ListQueue<Integer>();
		ArrayQueue<Integer> p = new ArrayQueue<Integer>(5);
		assertTrue(q.isEmpty());
		assertTrue(p.isEmpty());

		assertThrows(QueueError.class, () -> {
			q.deQueue();
			p.deQueue();
		});
	}

	// deQueue(enQueue(y,enQueue(x,q)))= enQueue(y,deQueue(enQueue(x,q)))
	@Test
	void dequeue_enqueue_enqueue() {
		int x = 12;
		int y = 0;
		ListQueue<Integer> q = new ListQueue<Integer>();
		ListQueue<Integer> q1 = new ListQueue<Integer>();

		q.enQueue(x);
		q.enQueue(y);
		q.deQueue();

		q1.enQueue(x);
		q1.deQueue();
		q1.enQueue(y);

		assertTrue(q.isEqualTo(q1));

		ArrayQueue<Integer> p = new ArrayQueue<Integer>(5);
		ArrayQueue<Integer> p1 = new ArrayQueue<Integer>(5);

		p.enQueue(x);
		p.enQueue(y);
		p.deQueue();

		p1.enQueue(x);
		p1.deQueue();
		p1.enQueue(y);
		assertTrue(p.isEqualTo(p1));
	}

	// deQueueFront(q) = front(q), deQueue(q), falls q nicht leer
	@Test
	void deQueueFront()
	{
		int x = 123;
		int y = 22222;
		ListQueue<Integer> q = new ListQueue<Integer>();
		ListQueue<Integer> q1 = new ListQueue<Integer>();
		
		q.enQueue(x);
		q1.enQueue(x);
		q.enQueue(y);
		q1.enQueue(y);
		assertFalse(q.isEmpty());
		assertFalse(q1.isEmpty());
		assertTrue(q.isEqualTo(q1));
		
		
		assertEquals(q.deQueueFront(), q1.front());
		q1.deQueue();
		assertTrue(q.isEqualTo(q1));
			
	}
	// enQueue(deQueueFront(q)) = deQueue(enQueue(front(q),q)), falls q nicht leer
	@Test
	void enQueue_deQueue()
	{
		int x = 123;
		int y = 22222;
		ListQueue<Integer> q = new ListQueue<Integer>();
		ListQueue<Integer> q1 = new ListQueue<Integer>();
		q.enQueue(x);
		q1.enQueue(x);
		q.enQueue(y);
		q1.enQueue(y);
		assertFalse(q.isEmpty());
		assertFalse(q1.isEmpty());
		assertTrue(q.isEqualTo(q1));
		
		q.enQueue(q.deQueueFront());
		q1.enQueue(q1.front());
		q1.deQueue();
		
		assertTrue(q.isEqualTo(q1));
	}
	// enQueue(front(q),deQueue(q)) = deQueue(enQueue(front(q),q)), falls q nicht
	// leer
	@Test
	void enQueue_front_dequeue()
	{
		int x = 2251;
		int y = 5;
		ListQueue<Integer> q = new ListQueue<Integer>();
		ListQueue<Integer> q1 = new ListQueue<Integer>();
		q.enQueue(x);
		q1.enQueue(x);
		q.enQueue(y);
		q1.enQueue(y);
		assertFalse(q.isEmpty());
		assertFalse(q1.isEmpty());
		assertTrue(q.isEqualTo(q1));
		
		int temp = q.front();
		q.deQueue();
		q.enQueue(temp);
		
		q1.enQueue(q1.front());
		q1.deQueue();
		assertTrue(q.isEqualTo(q1));
	}

}
