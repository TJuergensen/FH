package stack.ListStack;

import java.util.LinkedList;
import java.util.List;

import list.ADTList;
import stack.AbstractStack;

public class ADTListStack <E> extends AbstractStack<E>{
    
    private ADTList<E> list;
    
    public ADTListStack() {
	super();
	list = ADTList.list();
    }

    @Override
    public boolean isEmpty() {
	return list.isEmpty();
    }

    @Override
    public void push(E e) {
	list.cons(e);
    }

    @Override
    public void pop() {
	list = list.tail();
	
    }

    @Override
    public E top() {
	return list.head();
    }

    @Override
    public List<E> toList() {
	List<E> retList = new LinkedList<E>();
	for(int i=0; i< list.length(list); i++)
	{
	    retList.add(list.head());
	    pop();
	}
	return retList;
    }

}
