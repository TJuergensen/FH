package ADTMap;

import java.util.function.Function;

import ADTList.list.ADTList;
import ADTSet.Set.ADTSet;
import ADTSet.Set.ListSet;

public class ListMap<K, V> implements Map<K, V> {

	private final ADTSet<Entry<K, V>> set;

	private ListMap() {
		this.set = ListSet.empty();
	}

	@SafeVarargs
	private ListMap(Entry<K, V>... e) {
		ADTSet<Entry<K, V>> ret = ListSet.empty();
		for (int i = 0; i < e.length; i++) {
			ret = ret.insert(e[i]);
		}
		this.set = ret;
	}

	private ListMap(ADTSet<Entry<K, V>> set) {
		this.set = set;
	}

	@Override
	public Map<K, V> insert(K key, V value) {
		ADTSet<Entry<K,V>> ret = set;
		
		if(this.member(key))
		{
			ret = ret.delete(new Entry<K, V>(key, lookup(key)));
		}
		return new ListMap<K, V>(ret.insert(new Entry<K, V>(key, value)));
	}

	@Override
	public boolean member(K key) {
		return set.any(x -> x.getKey().equals(key));
	}

	@Override
	public Map<K, V> delete(K key) {
		
		Entry<K,V> toDelete = new Entry<K, V>(key, lookup(key));
		return new ListMap<K, V>(this.set.delete(toDelete));
		//return new ListMap<K, V>(this.set.delete(new Entry<K, V>(key, lookup(key))));
	}

	@Override
	public V lookup(K key) {

		if (member(key)) {
			//System.out.println("LOOKUP WANTED!!  "+ADTList.filter(x -> x.getKey().equals(key), this.set.toList()).toString());
			return ADTList.filter(x -> x.getKey().equals(key), set.toList()).head().getValue();
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		return set.isEmpty();
	}

	@Override
	public int size() {
		return set.size();
	}

	public static <K, V> Map<K, V> empty() {
		return new ListMap<K, V>();
	}

	public static <K, V> Map<K, V> fromList(ADTList<Tuple<K, V>> xs) {
		ADTSet<Entry<K, V>> ret = ListSet.empty();
		int len = xs.length(xs);
		for (int i = 0; i < len; i++) {
			ret = ret.insert(new Entry<K, V>(xs.head().getfst(), xs.head().getsnd()));
			xs = xs.tail();
		}
		return new ListMap<K, V>(ret);
	}

	public String toString() {
		String ret = "{";
		if (!isEmpty()) {
			ADTList<Entry<K, V>> asList = set.toList();
			int len = asList.length(asList);
			for (int i = 0; i < len; i++) {
				ret += asList.head().toString();
				asList = asList.tail();
			}
		}
		ret += "}";
		return ret;
	}
	

	@Override
	public ADTList<Tuple<K, V>> toList() {
		ADTList<Tuple<K,V>> ret = ADTList.list();
		ADTList<Entry<K,V>> xs = set.toList();
		int len = xs.length(xs);
		
		for (int i = 0; i < len; i++) {
			ret = ret.cons(new Tuple<K, V>(xs.head().getKey(), xs.head().getValue()));
			xs = xs.tail();
		}
				
		return ret;
	}

	@Override
	public boolean all(Function<Entry<K, V>, Boolean> p) {
		return set.all(p);
	}

	@Override
	public boolean allKeys(Function<K, Boolean> p) {
		return !(set.any(x -> !p.apply(x.getKey())));
	}

	@Override
	public boolean isEqualTo(Map<K, V> m) {
		//return set.isEqualTo(m.toList().toSet(m.toList()));
		
		return this.toList().toSet(this.toList()).isEqualTo(m.toList().toSet(m.toList()));
		
	}
	
	@Override
	public Entry<K,V> get(K key)
	{
		return new Entry<K,V>(key, lookup(key));
	}
	
	
	

}
