package ADTQueue.StackQueue;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ADTQueue.ListQueue.ListQueue;

class QueueStackTest {

	@Test
	void test() {
		QueueStack<Integer> sq = new QueueStack<Integer>();
		ListQueue<Integer> q = new ListQueue<Integer>();
		int x = 2;
		int y = 25;

		sq.enQueue(x);
		q.enQueue(x);
		assertTrue(q.isEqualTo(sq));

		sq.enQueue(y);
		q.enQueue(y);

		sq.deQueue();
		q.deQueue();

		assertTrue(q.isEqualTo(sq));

		sq.deQueue();
		q.deQueue();

//		assertThrows(QueueError.class, () -> {
//			q.deQueue();
//			sq.deQueue();
//		});

	}

}
