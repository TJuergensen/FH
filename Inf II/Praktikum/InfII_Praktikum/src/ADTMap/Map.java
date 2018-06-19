package ADTMap;

import java.util.function.Function;

import ADTList.list.ADTList;

public interface Map<K, V> {
	Map<K, V> insert(K key, V value);

	boolean member(K key);

	Map<K, V> delete(K key);

	V lookup(K key);

	boolean isEmpty();

	int size();
	
	ADTList<Tuple<K,V>> toList();
	
	boolean all(Function<Entry<K,V>, Boolean> p);
	
	boolean allKeys(Function<K, Boolean> p);
	
	boolean isEqualTo (Map<K,V> m);
	
	
}

