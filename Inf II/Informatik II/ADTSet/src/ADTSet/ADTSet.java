package ADTSet;

import java.util.function.Function;

import list.ADTList;

public interface ADTSet<A> {
    
    ADTSet<A> insert(A e);
    ADTSet<A> delete(A e);
    
    boolean member(A e);
    int size();
    boolean isEmpty();
    
    A get(A e);
    ADTList<A> toList();
    
    boolean any(Function<A, Boolean> p);
    boolean all(Function<A, Boolean> p);
    
    boolean isSubsetOf(ADTSet<A> s);
        
}
