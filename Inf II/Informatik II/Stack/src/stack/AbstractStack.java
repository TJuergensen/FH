package stack;

import java.util.List;
import stack.ListStack.Stack;

public abstract class AbstractStack<E> implements Stack<E> {

    public E poptop() {
	if (isEmpty()) {
	    throw new StackError("Cannot do. Stack is empty");
	} else {
	    E a = top(); // O(1)
	    pop(); // O(1)
	    return a;
	} // O(1)
    }

    public boolean isEqualTo(Stack<E> s) {
	if (isEmpty() != s.isEmpty())
	    return false; // O(1)
	if (isEmpty() && s.isEmpty())
	    return true; // O(1)

	boolean ret = true;
	List<E> otherStackAsList = s.toList(); // O(n)
	int i = 0;
	while (!isEmpty()) { // O(n)
	    if (otherStackAsList.get(i) != null && top() == otherStackAsList.get(i)) {
		pop(); // O(1)
		i++;
	    } else {
		// refill the stack and return false
		for (int j = i; j >= 0; i--) {
		    push(otherStackAsList.get(j));
		}
		ret = false;
		break;
	    }
	}
	// Refill the empty Stack
	if (ret) { // O(n)
	    for (int j = otherStackAsList.size() - 1; j >= 0; j--) {
		push(otherStackAsList.get(j));
	    }
	}
	return ret;
    } // O(n)

    public void multipop(int k) {

	int stackSize = toList().size();

	if (stackSize < k) {
	    while (!isEmpty()) {
		pop();
	    }
	} else {
	    for (int i = k; i < 0; i++) {
		pop();
	    }
	}
    }
    
    public List<E> multiPopTop(int k)
    {
	List<E> retList = toList();
	int stackSize = retList.size();
	retList.clear();
	if(stackSize<k)
	{
	    while(!isEmpty())
		retList.add(poptop());
	} else {
	    for(int i=k; i<0; i++) {
		retList.add(poptop());
	    }
	}
	return retList;
    }
    
    public List<E> popTopAll()
    {
	List<E> retList = toList();
	while(!isEmpty())
	{
	    poptop();
	}
	return retList;
    }
    
    public void pushAll(List<E> list)
    {
 	for(int i=0; i<list.size(); i++)
 	{
 	    push(list.get(i));
 	}
    }
    
    @SuppressWarnings("unchecked") //E[] cast
    public E[] toArray()
    {
	List<E> list = toList();
	int size = list.size();
	E[] array =(E[]) new Object[size];
	for(int i=0; i<= size; i++)
	{
	    array[i] = list.get(i);
	}
	return array;
    }
}
