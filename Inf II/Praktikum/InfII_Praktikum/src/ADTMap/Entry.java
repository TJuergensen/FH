package ADTMap;

public class Entry<K,V>{
	
	private final K k;
	private final V v;
	
	public Entry(K k, V v)
	{
		this.k=k;
		this.v=v;
	}
	
	public K getKey()
	{
		return this.k;
	}
	
	public V getValue()
	{
		return this.v;
	}
	
//	public void setValue(V v)
//	{
//		this.v=v;
//	}
//	@Override
//	public boolean equals(Entry<K,V> e)
//	{
//		System.out.println("equals entry");
//		return this.k.equals(e.getKey());
//	}
//	
	public String toString()
	{
		return "("+this.k+","+this.v+")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((k == null) ? 0 : k.hashCode());
		result = prime * result + ((v == null) ? 0 : v.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entry<K,V> other = (Entry<K,V>) obj;
		if (k == null) {
			if (other.k != null)
				return false;
		} else if (!k.equals(other.k))
			return false;
//		if (v == null) {
//			if (other.v != null)
//				return false;
//		} else if (!v.equals(other.v))
//			return false;
		return true;
	}

}
