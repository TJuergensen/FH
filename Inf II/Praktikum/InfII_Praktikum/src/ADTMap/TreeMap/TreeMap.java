package ADTMap.TreeMap;

import java.util.function.Function;

import ADTList.list.ADTList;
import ADTSet.Set.ADTSet;
import ADTSet.Set.TreeSet;


public class TreeMap<K extends Comparable<K>, V> implements Map<K, V> {

	private final ADTSet<Entry<K, V>> set;

	//O(1)
	private TreeMap() {
		this.set = TreeSet.empty();
	}

	//O(n)
	@SafeVarargs
	private TreeMap(Entry<K, V>... e) {
		ADTSet<Entry<K, V>> ret = TreeSet.empty();
		for (int i = 0; i < e.length; i++) {
			ret = ret.insert(e[i]);
		}
		this.set = ret;
	}

	//O(1)
	private TreeMap(ADTSet<Entry<K, V>> set) {
		this.set = set;
	}

	//O(n*log(n))
	@Override
	public Map<K, V> insert(K key, V value) {
		ADTSet<Entry<K, V>> ret = set;

		if (this.member(key)) {
			ret = ret.delete(new Entry<K, V>(key, lookup(key)));
		}
		return new TreeMap<K, V>(ret.insert(new Entry<K, V>(key, value)));
	}
	//O(n*log(n))
	@Override
	public boolean member(K key) {
		return set.any(x -> x.getKey().equals(key));
	}

	//O(n*log(n))
	@Override
	public Map<K, V> delete(K key) {

		Entry<K, V> toDelete = new Entry<K, V>(key, lookup(key));
		return new TreeMap<K, V>(this.set.delete(toDelete));
		// return new ListMap<K, V>(this.set.delete(new Entry<K, V>(key, lookup(key))));
	}

	@Override
	public V lookup(K key) {

		if (member(key)) {

			return ADTList.filter(x -> x.getKey().equals(key), set.toList()).head().getValue();
		}
		return null;
	}

	//O(1)
	@Override
	public boolean isEmpty() {
		return set.isEmpty();
	}

	//O(1)
	@Override
	public int size() {
		return set.size();
	}

	//O(1)
	public static <K extends Comparable<K>,V> TreeMap<K, V> empty() {
		return new TreeMap<K, V>();
	}
	//O(n*log(n))
	public static <K extends Comparable<K>,V> Map<K, V> fromList(ADTList<Tuple<K, V>> xs) {
		ADTSet<Entry<K, V>> ret = TreeSet.empty();
		int len = xs.length(xs);
		for (int i = 0; i < len; i++) {
			ret = ret.insert(new Entry<K, V>(xs.head().getfst(), xs.head().getsnd()));
			xs = xs.tail();
		}
		return new TreeMap<K, V>(ret);
	}
	
	//O(n)
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

	//O(n)
	@Override
	public ADTList<Tuple<K, V>> toList() {
		ADTList<Tuple<K, V>> ret = ADTList.list();
		ADTList<Entry<K, V>> xs = set.toList();
		int len = xs.length(xs);

		for (int i = 0; i < len; i++) {
			ret = ret.cons(new Tuple<K, V>(xs.head().getKey(), xs.head().getValue()));
			xs = xs.tail();
		}

		return ret;
	}

	//O(n)
	@Override
	public boolean all(Function<Entry<K, V>, Boolean> p) {
		return set.all(p);
	}

	//O(n)
	@Override
	public boolean allKeys(Function<K, Boolean> p) {
		return !(set.any(x -> !p.apply(x.getKey())));
	}

	//O(n²)
	@Override
	public boolean isEqualTo(Map<K, V> m) {
		// return set.isEqualTo(m.toList().toSet(m.toList()));

		return this.toList().toSet(this.toList()).isEqualTo(m.toList().toSet(m.toList()));

	}

	//O(n*log(n))
	@Override
	public Entry<K, V> get(K key) {
		if (lookup(key) != null)
			return new Entry<K, V>(key, lookup(key));
		return null;
	}

}
