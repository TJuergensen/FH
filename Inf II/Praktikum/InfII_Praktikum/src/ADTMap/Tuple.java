package ADTMap;

public class Tuple<K,V> {
	public final K fst;
	public final V snd;
	
	public Tuple(K fst, V snd)
	{
		this.fst = fst;
		this.snd = snd;
	}
	
	public K getfst()
	{
		return this.fst;
	}
	
	public V getsnd()
	{
		return this.snd;
	}
	
//	public boolean equals(Tuple<K,V> other)
//	{
//		return (fst.equals(other.getfst()) && snd.equals(other.getsnd()));
//	}
	
	
	public String toString()
	{
		return "("+this.fst+","+this.snd+")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fst == null) ? 0 : fst.hashCode());
		result = prime * result + ((snd == null) ? 0 : snd.hashCode());
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
		Tuple<K, V> other = (Tuple<K, V>) obj;
		if (fst == null) {
			if (other.fst != null)
				return false;
		} else if (!fst.equals(other.fst))
			return false;
		if (snd == null) {
			if (other.snd != null)
				return false;
		} else if (!snd.equals(other.snd))
			return false;
		return true;
	}

}
