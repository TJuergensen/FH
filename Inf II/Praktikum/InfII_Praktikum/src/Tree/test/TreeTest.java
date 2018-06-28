package Tree.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import ADTList.list.ADTList;
import Tree.Tree;

@RunWith(Theories.class)
public class TreeTest {

	Tree<Character> t, t1, t2;

	@DataPoints
	public static Character[] chars() {
		return new Character[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', '0', '1', '2', '3', '4', '5',
				'6', '7', '8', '9' };
	}

	@Theory
	public void fabric_test(Character a, Character b, Character c) {
		assertNull(t);
		t = Tree.empty();
		assertNotNull(t);

		assertNull(t1);
		t1 = Tree.tree(a, b, c);
		assertNotNull(t1);
	}

	@Theory
	public void size_height_test(Character a, Character b, Character c) {

		t = Tree.tree(a, b, c);
		t1 = Tree.empty();

		if (a != b && a != c && b != c) {
			assertTrue(t.size() == 3);
			assertTrue(t.height() == 2 || t.height() == 1);
		}
		if (a == b && a != c && b != c) {
			assertTrue(t.size() == 2);
			assertTrue(t.height() == 1);
		}
		if (a != b && a == c && b != c)
			assertTrue(t.size() == 2);
		if (a != b && a != c && b == c)
			assertTrue(t.size() == 2);

		if (a == b && a == c && b != c) {
			assertTrue(t.size() == 1);
			assertTrue(t.height() == 0);
		}
		if (a == b && a != c && b == c)
			assertTrue(t.size() == 1);
		if (a != b && a == c && b == c)
			assertTrue(t.size() == 1);

		assertTrue(t1.size() == 0);
		assertTrue(t1.height() == -1);
	}

	@Theory
	public void insert_test(Character a, Character b, Character c) {
		t = Tree.tree(a, b, c);
		t1 = Tree.empty();
		t1 = t1.insert(a);
		t1 = t1.insert(b);
		t1 = t1.insert(c);

		assertTrue(t1.height() == t.height());
		assertTrue(t1.size() == t.size());
	}

	@Theory
	public void member_test(Character a, Character b, Character c) {
		t = Tree.tree(a, b, c);
		assertTrue(t.member(a));
		assertTrue(t.member(b));
		assertTrue(t.member(c));
	}
	
	@Theory
	public void isEmpty_test(Character a, Character b, Character c)
	{
		t = Tree.empty();
		assertTrue(t.isEmpty());
		t = t.insert(a);
		assertFalse(t.isEmpty());
	}
	
	@Theory 
	public void delete_test(Character a, Character b, Character c)
	{
		t = Tree.tree(a,b,c);
		
		assertTrue(t.member(a));
		assertTrue(t.member(b));
		assertTrue(t.member(c));
		t = t.delete(a);
		
		assertFalse(t.member(a));
		t = t.delete(b);
		assertFalse(t.member(b));
		t = t.delete(c);
		assertFalse(t.member(c));
		assertTrue(t.isEmpty());
	}
	
	@Theory
	public void get_test(Character a, Character b, Character c)
	{
		t = Tree.empty();
		assertNull(t.get(a));
		
		t = t.insert(a);
		assertNotNull(t.get(a));
		assertTrue(t.get(a).equals(a));
		
		t = t.insert(b);
		t = t.insert(c);
		
		assertNotNull(t.get(b));
		assertTrue(t.get(b).equals(b));
		
		assertNotNull(t.get(c));
		assertTrue(t.get(c).equals(c));
		
	}
	
	@Theory
	public void testLevelOrder()
	{
		Tree<Integer> t3 = Tree.empty();
		t3 = t3.insert(3);
		t3 = t3.insert(0);
		t3 = t3.insert(1);
		t3 = t3.insert(5);
		t3 = t3.insert(4);
		
//		ADTList<Integer> result = t3.toListLevelorder();
		



	}
	
	@Theory
	public void testInOrder()
	{
		Tree<Integer> t3 = Tree.empty();
		t3 = t3.insert(3);
		t3 = t3.insert(0);
		t3 = t3.insert(1);
		t3 = t3.insert(5);
		t3 = t3.insert(4);
		
		ADTList<Integer> result = t3.toListInorder();
		
		assertTrue(result.head() == 5);
		result = result.tail();
		assertTrue(result.head() == 4);
		result = result.tail();
		assertTrue(result.head() == 3);
		result = result.tail();
		assertTrue(result.head() == 1);
		result = result.tail();
		assertTrue(result.head() == 0);
	}
	@Theory
	public void testPreOrder()
	{
		Tree<Integer> t3 = Tree.empty();
		t3 = t3.insert(3);
		t3 = t3.insert(0);
		t3 = t3.insert(1);
		t3 = t3.insert(5);
		t3 = t3.insert(4);
		
		
		ADTList<Integer> result = t3.toListPreorder();
		
		assertTrue(result.head() == 4);
		result = result.tail();
		assertTrue(result.head() == 5);
		result = result.tail();
		assertTrue(result.head() == 1);
		result = result.tail();
		assertTrue(result.head() == 0);
		result = result.tail();
		assertTrue(result.head() == 3);

		//System.out.println(t3.toString());
		//System.out.println(result.toString());
	}
	@Theory
	public void testPostOrder()
	{
		Tree<Integer> t3 = Tree.empty();
		t3 = t3.insert(3);
		t3 = t3.insert(0);
		t3 = t3.insert(1);
		t3 = t3.insert(5);
		t3 = t3.insert(4);
		
		
		ADTList<Integer> result = t3.toListPostorder();
		assertTrue(result.head() == 3);
		result = result.tail();
		assertTrue(result.head() == 5);
		result = result.tail();
		assertTrue(result.head() == 4);
		result = result.tail();
		assertTrue(result.head() == 0);
		result = result.tail();
		assertTrue(result.head() == 1);
	}
	
	@Theory
	public void anzBlaetter_test()
	{
		Tree<Integer> t3 = Tree.empty();
		t3 = t3.insert(3);
		t3 = t3.insert(0);
		t3 = t3.insert(1);
		t3 = t3.insert(5);
		t3 = t3.insert(4);
		
		assertTrue(t3.anzBlaetter() == 2);
	}
	@Theory
	public void anzKnotenMitEinemKind_test()
	{
		Tree<Integer> t3 = Tree.empty();
		t3 = t3.insert(3);
		t3 = t3.insert(0);
		t3 = t3.insert(1);
		t3 = t3.insert(5);
		t3 = t3.insert(4);
		assertTrue(t3.anzKnotenMitEinemKind() == 2);
	}
	
	@Theory
	public void anzInnereKnoten_test()
	{
		Tree<Integer> t3 = Tree.empty();
		t3 = t3.insert(3);
		t3 = t3.insert(0);
		t3 = t3.insert(1);
		t3 = t3.insert(5);
		t3 = t3.insert(4);
		
		assertTrue(t3.anzInnereKnoten() == 2);
	}
	
	

}
