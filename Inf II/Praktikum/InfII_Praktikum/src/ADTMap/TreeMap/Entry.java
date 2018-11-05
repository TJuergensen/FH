package ADTMap.TreeMap;

public class Entry<K extends Comparable<K>,V> implements Comparable<Entry<K,V>>{
	
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
	public int compareTo(Entry<K, V> e) {
		return getKey().compareTo(e.getKey());
	}

}

