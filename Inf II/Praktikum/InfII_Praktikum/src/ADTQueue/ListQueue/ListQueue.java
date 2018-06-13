package ADTQueue.ListQueue;

import java.util.LinkedList;
import java.util.List;

import ADTQueue.AbstractQueue;
import ADTQueue.QueueError;

class Cell<E> {
	E data;
	Cell<E> next = null;

	Cell(E e) {
		data = e;
	}
}

public class ListQueue<E> extends AbstractQueue<E> {

	private Cell<E> end;

	public ListQueue() {
		super();
	}

	public boolean isEmpty() {
		return end == null;
	}

	public void deQueue() {
		if (isEmpty())
			throw new QueueError("Queue is empty!");
		if (end.next == end) {
			end = null;
		} else {
			end.next = end.next.next;
		}
		return;
	}

	@Override
	public void enQueue(E e) {
		Cell<E> newCell = new Cell<E>(e);
		if (isEmpty()) {
			end = newCell;
			end.next = newCell;
		} else {
			newCell.next = end.next;
			end.next = newCell;
			end = newCell;
		}
		return;

	}

	@Override
	public E front() {
		if (isEmpty())
			throw new QueueError("Queue is empty.");
		return end.next.data;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E[] toArray() {
		List<E> list = this.toList();
		return (E[]) list.toArray();
	}

	@Override
	public List<E> toList() {
		List<E> list = new LinkedList<E>();
		if (!isEmpty()) {
			Cell<E> tmp = end;
			while (tmp.next != end) {
				list.add(tmp.next.data);
				tmp = tmp.next;

			}
			// add last (missing) element
			list.add(tmp.next.data);
		}
		return list;
	}

}
