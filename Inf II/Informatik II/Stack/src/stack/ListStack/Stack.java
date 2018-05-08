package stack.ListStack;

import java.util.List;

public interface Stack<E> {

    boolean isEmpty();

    void push(E e);

    void pop();

    E top();

    E poptop();

    List<E> toList();

    boolean isEqualTo(Stack<E> s);

    void multipop(int k);

    List<E> multiPopTop(int k);

    List<E> popTopAll();

    void pushAll(List<E> list);
    
    E[] toArray();

}
