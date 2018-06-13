package ADTStack.stackTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ADTStack.ArrayStack.ArrayStack;
import ADTStack.ListStack.ListStack;

class StackTest {

    // isEmpty(push(x,s)) = false
    @Test
    public void isEmpty_push() {
	ArrayStack<Integer> aStack = new ArrayStack<Integer>(1);
	aStack.push(5);
	// assertTrue
	assertFalse(aStack.isEmpty());

	ListStack<Integer> lStack = new ListStack<Integer>();
	lStack.push(5);
	// assertTrue
	assertFalse(lStack.isEmpty());
    }

    // isEmpty() == true
    @Test
    public void isEmpty_true() {
	ArrayStack<Integer> aStack = new ArrayStack<Integer>(1);
	assertTrue(aStack.isEmpty());
	aStack.push(5);
	aStack.pop();
	assertTrue(aStack.isEmpty());

	ListStack<Integer> lStack = new ListStack<Integer>();
	assertTrue(lStack.isEmpty());
	aStack.push(5);
	aStack.pop();
	assertTrue(lStack.isEmpty());
    }

    // top(push(x,s)) = x
    @Test
    public void top_push() {
	ArrayStack<Integer> aStack = new ArrayStack<Integer>(2);
	aStack.push(3);
	assertEquals((int) aStack.top(), 3);

	ListStack<Integer> lStack = new ListStack<Integer>();
	lStack.push(3);
	assertEquals((int) lStack.top(), 3);

    }

    // popTop(s) = top(s), ignore
    @Test
    public void popTop_top() {
	ArrayStack<Integer> aStack = new ArrayStack<Integer>(2);
	ArrayStack<Integer> aStack1 = new ArrayStack<Integer>(2);
	aStack.push(3);
	aStack.push(5);
	aStack1.push(3);
	aStack1.push(5);
	assertEquals(aStack.poptop(), aStack1.top());

	ListStack<Integer> lStack = new ListStack<Integer>();
	ListStack<Integer> lStack1 = new ListStack<Integer>();
	lStack.push(3);
	lStack.push(5);
	lStack1.push(3);
	lStack1.push(5);
	assertEquals(lStack.poptop(), lStack1.top());
    }

    // pop(push(x,s)) = s
    @Test
    public void pop_push() {
	ArrayStack<Integer> aStack = new ArrayStack<Integer>(2);
	ArrayStack<Integer> aStack1 = new ArrayStack<Integer>(2);
	assertTrue(aStack.isEqualTo(aStack1));
	aStack.push(3);
	aStack1.push(3);
	assertTrue(aStack.isEqualTo(aStack1));

	aStack.push(2);
	aStack.pop();
	assertTrue(aStack.isEqualTo(aStack1));

	// ArrayStack und ListStack:
	ArrayStack<Integer> aStack2 = new ArrayStack<Integer>(2);
	ListStack<Integer> lStack = new ListStack<Integer>();
	assertTrue(aStack2.isEqualTo(lStack));
	aStack2.push(5);
	aStack2.pop();
	assertTrue(aStack2.isEqualTo(lStack));
    }

    // push(top(s),pop(s)) = s , falls s nicht leer
    @Test
    public void push_top_pop() {
	ArrayStack<Integer> aStack = new ArrayStack<Integer>(2);
	ArrayStack<Integer> aStack1 = new ArrayStack<Integer>(2);

	aStack.push(2);
	aStack1.push(2);
	assertTrue(aStack.isEqualTo(aStack1));

	assertFalse(aStack1.isEmpty());
	aStack.push(aStack.top());
	aStack.pop();
	assertTrue(aStack.isEqualTo(aStack1));
    }

    // popTop(s) = top(s), pop(s) , falls s nicht leer
    @Test
    public void popTop_top_pop() {
	ArrayStack<Integer> aStack = new ArrayStack<Integer>(2);
	ArrayStack<Integer> aStack1 = new ArrayStack<Integer>(2);

	aStack.push(2);
	aStack1.push(2);
	aStack.push(6);
	aStack1.push(6);
	assertTrue(aStack.isEqualTo(aStack1));

	assertEquals(aStack.poptop(), aStack1.top());
	aStack1.pop();
	assertTrue(aStack.isEqualTo(aStack1));
    }

    // push(popTop(s)) = s , falls s nicht leer
    @Test
    public void push_popTop() {
	ArrayStack<Integer> aStack = new ArrayStack<Integer>(3);
	ArrayStack<Integer> aStack1 = new ArrayStack<Integer>(3);

	aStack.push(2);
	aStack1.push(2);
	aStack.push(6);
	aStack1.push(6);
	assertTrue(aStack.isEqualTo(aStack1));

	aStack.push(aStack.poptop());
	assertTrue(aStack.isEqualTo(aStack1));
    }

    // pop(empty) = error
    @Test
    public void pop_empty() {
	ArrayStack<Integer> aStack = new ArrayStack<Integer>(3);
	assertTrue(aStack.isEmpty());

//	assertThrows(StackError.class, () -> {
//	    aStack.pop();
//	});
//
//	ListStack<Integer> lStack = new ListStack<Integer>();
//	assertTrue(aStack.isEmpty());
//	assertThrows(StackError.class, () -> {
//	    lStack.pop();
//	});
    }

    // top(empty) = error
    @Test
    public void top_empty() {

	ArrayStack<Integer> aStack = new ArrayStack<Integer>(3);
	assertTrue(aStack.isEmpty());

//	assertThrows(StackError.class, () -> {
//	    aStack.top();
//	});
//
//	ListStack<Integer> lStack = new ListStack<Integer>();
//	assertTrue(aStack.isEmpty());
//	assertThrows(StackError.class, () -> {
//	    lStack.top();
//	});
    }

    // popTop(empty) = error
    @Test
    public void poptop_empty() {

//	ArrayStack<Integer> aStack = new ArrayStack<Integer>(3);
//	assertTrue(aStack.isEmpty());
//	assertThrows(StackError.class, () -> {
//	    aStack.poptop();
//	});

//	ListStack<Integer> lStack = new ListStack<Integer>();
//	assertTrue(aStack.isEmpty());
//	assertThrows(StackError.class, () -> {
//	    lStack.poptop();
//	});
    }
}
