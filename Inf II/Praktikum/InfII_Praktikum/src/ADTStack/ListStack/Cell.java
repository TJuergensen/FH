package ADTStack.ListStack;

public class Cell<E> {
    private E data;
    private Cell<E> next;

    Cell(E e) {
	data = e;
    }

    public void setData(E e) {
	data = e;
	//next = null;
    }

    public E getData() {
	return data;
    }

    public void setNext(Cell<E> cell) {
	next = cell;
    }

    public Cell<E> getNext() {
	return next;
    }
}
