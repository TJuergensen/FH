package linkedList;

public class LinkedList<E> {
	
	private Element<E> last;
	private Element<E> first;
	private int N;
	
	public LinkedList()
	{
		first = null;
		last = null;
		N = 0;
	}
	
	public void add(E item)
	{
		if(item == null)
		{
			throw new NullPointerException("The item you wanted to add is null");
		}
		if(!isEmpty())
		{
			Element<E> prev = last;
			last = new Element<E>(item, null);
			prev.setNext(last);
		} else {
			last = new Element<E>(item, null);
			first = last;
		}
		N++;
	}
	
	public boolean remove(E item)
	{
		if(isEmpty()) 
		{
			throw new NullPointerException("The list is alredy empty");
		}
		boolean result = false;
		Element<E> prev = first;
		Element<E> curr = first;
		while(curr.Next() != null || curr == last)
		{
			if(curr.getContent().equals(item))
			{
				if(N==1)
				{
					first = null;
					last = null;
				}
				else if(curr.equals(first))
				{
					first=first.Next();
				}
				else if(curr.equals(last))
				{
					last = prev;
					last.deleteNext();
				}
				else {
					prev.setNext(curr.Next());
				}
				N--;
				result = true;
				break;
			}
			prev = curr;
			curr = prev.Next();
		}
		return result;
	}
	
	public int getSize()
	{
		return N;
	}

	private boolean isEmpty()
	{
		return N == 0;
	}

}
