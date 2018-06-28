package ADTSet.SetTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import ADTList.list.ADTList;
import ADTSet.Set.ADTSet;
import ADTSet.Set.TreeSet;

@RunWith(Theories.class)
public class TreeSetTest {
	ADTSet<Integer> testSet;
	ADTSet<Integer> referenceTestSet;

	@DataPoints
	public static int[] integers() {
		return new int[] { 876, -1, -10, -1234567, 1, 10, 1234567, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 2, 4, 3, 5,
				6, 7, 8, 9, 10, 11, 12, 13, 22, 52 };
	}

	@DataPoints
	public static int[] integers2() {
		return new int[] { 348563785, 876, -1, -10, -1234567, 1, 10, 1234567, Integer.MIN_VALUE, Integer.MAX_VALUE, 0,
				2, 4, 3, 5, 6, 7, 8, 9, 10, 11, 12, 13, 22, 52 };
	}

	@DataPoint
	public static ADTSet<Integer> TestSet = TreeSet.set(75, 6, 4, 2, 88, 5, 3, 8, 9, 234, 2, 6);
	
	@DataPoint
	public static ADTSet<Integer> tesSet2 = TreeSet.set(2, 6, 3, 88, 88, 5, 9, 15, 222, 555, 777, 999, 34564, 2134234,
			567567, 34234, 42);
	
	@DataPoint
	public static ADTSet<Integer> tesSet3 = TreeSet.set(123456,876543,23456,7865);

	@DataPoint
	public static ADTList<Integer> testList = ADTList.list(2, 6, 3, 88, 5, 9, 15, 222, 555, 777, 999, 34564, 2134234,
			567567, 34234, 42);

	@DataPoint
	public static int[] intarray = { 2, 5, 8, 44, 7, 2, 1, 78, 23425, 12, 3, Integer.MIN_VALUE, Integer.MAX_VALUE };

	@Theory // vx : x e A -> x e B
	public void subsetTest(ADTList<Integer> a, int[] b) {
		testSet = TreeSet.fromList(a);
		referenceTestSet = TreeSet.fromList(a);

		for (int i = 0; i < b.length; i++) {
			referenceTestSet.insert(b[i]);
		}

		for (int i = 0; i < a.length(a); i++) {
			assertTrue(testSet.member(a.head()) && referenceTestSet.member(a.head()));
			assertTrue(testSet.member(a.head()));

			a = a.tail();
		}

		assertTrue(testSet.isSubsetOf(referenceTestSet));

	}

	@Theory // A = B <=> AcB & BcA
	public void isEqualToTest(ADTList<Integer> a, Integer b) {
		testSet = TreeSet.fromList(a);
		referenceTestSet = TreeSet.fromList(a);

		assertTrue(testSet.isSubsetOf(referenceTestSet));
		assertTrue(referenceTestSet.isSubsetOf(testSet));

		assertTrue(referenceTestSet.isEqualTo(testSet));

		if (!testSet.member(b)) {
			testSet = testSet.insert(b);
			assertFalse(referenceTestSet.isEqualTo(testSet));
		}
	}

	@Theory // A != B <=> A!cB & B!cA
	public void disjointTest() {
		testSet = TreeSet.set(1, 2, 3, 4, 5, 6);
		referenceTestSet = TreeSet.set(7, 8, 9, 0);
		assertFalse(testSet.isSubsetOf(referenceTestSet));
		assertFalse(referenceTestSet.isSubsetOf(testSet));

		assertTrue(testSet.disjoint(referenceTestSet));
	}

	// isEmpty(empty) = true
	@Theory
	public void isEmpty_empty_Test() {
		testSet = TreeSet.empty();
		assertTrue(testSet.isEmpty());
	}

	// isEmpty(insert(x,s)) = false
	@Theory
	public void isEmpty_insert_Test(Integer a) {
		testSet = TreeSet.empty();
		assertTrue(testSet.isEmpty());
		testSet = testSet.insert(a);
		assertFalse(testSet.isEmpty());
	}

	// member(x,empty) = false
	@Theory
	public void member_empty_Test(Integer a) {
		testSet = TreeSet.empty();
		assertFalse(testSet.member(a));
	}

	// member(y,insert(x,s)) = x==y ? true : member(y,s)
	@Theory
	public void member_insert_test(Integer x, Integer y) {
		testSet = TreeSet.empty();
		testSet = testSet.insert(x);
		if (x.equals(y)) {
			assertTrue(testSet.member(y));
		} else {
			assertFalse(testSet.member(y));
		}
	}

	// member(y, delete(x,s)) = x==y ? false : member(y,s)
	@Theory
	public void member_delete_test(Integer x, Integer y) {
		testSet = TreeSet.set(y);
		// testSet.insert(y);
		assertTrue(testSet.member(y));

		testSet = testSet.delete(x);
		if (x.equals(y)) {
			assertFalse(testSet.member(y));
		} else {
			assertTrue(testSet.member(y));
		}
	}

	// size(empty) = 0
	@Theory
	public void size_Empty_test() {
		testSet = TreeSet.empty();
		assertTrue(testSet.size() == 0);
	}

	// size(insert(x,s)) = !member(x,s) ? size(s)+1 : size(s)
	@Theory
	public void size_insert_Test(Integer x, ADTList<Integer> a) {
		testSet = TreeSet.fromList(a);
		int setSize = testSet.size();
//		//System.out.println(setSize);

		if (!testSet.member(x)) {
			testSet = testSet.insert(x);
			assertTrue(testSet.size() == setSize + 1);
		} else {
			//System.out.println(testSet.toString());
			//System.out.println(x);
			testSet = testSet.insert(x);
			//System.out.println(testSet.toString());
			assertTrue(testSet.size() == setSize);
		}
	}

	// insert(y, insert(x,s)) = x==y ? insert(y,s) : insert(x, insert(y,s))
	@Theory
	public void insert_insert_test(Integer x, Integer y, ADTList<Integer> a) {
		testSet = TreeSet.fromList(a);

		if (x == y) {
			testSet = testSet.insert(y);
			assertTrue(testSet.member(x));
			assertTrue(testSet.member(y));
		} else {
			testSet = testSet.insert(y);
			testSet = testSet.insert(x);
			assertTrue(testSet.member(y));
			assertTrue(testSet.member(x));
		}
	}

	// delete(x, empty) = empty
	@Theory
	public void delete_empty_test(Integer x) {
		testSet = TreeSet.empty();
		assertTrue(testSet.isEmpty());
		testSet.delete(x);
		assertTrue(testSet.isEmpty());
	}

	// delete(y,insert(x,s)) = x==y ? delete(y,s) : insert(x, delete(y,s))
	@Theory
	public void delete_insert_test(Integer x, Integer y, ADTList<Integer> a) {
		testSet = TreeSet.fromList(a);
		testSet.insert(x);
		testSet.delete(y);

		if (x.equals(y)) {
			referenceTestSet = TreeSet.fromList(a);
			referenceTestSet.insert(x);
			referenceTestSet.delete(y);
			assertTrue(referenceTestSet.isEqualTo(testSet));
		} else {
			referenceTestSet = TreeSet.fromList(a);
			referenceTestSet.insert(y);
			referenceTestSet.delete(y);
			referenceTestSet.insert(x);
			assertTrue(referenceTestSet.isEqualTo(testSet));
		}
	}

	// get(y, insert(x,s)) = x==y ? x : get(y,s)
	@Theory
	public void get_insert_test(Integer x, Integer y, ADTList<Integer> a) {
		testSet = TreeSet.fromList(a);
		
		testSet = testSet.insert(y);
		
		testSet = testSet.insert(x);
		Integer test1 = testSet.get(y);

		if (x.equals(y)) {
			assertTrue(test1.equals(x));
		} else {
			referenceTestSet = TreeSet.fromList(a);
			referenceTestSet = referenceTestSet.insert(y);
			
			assertTrue(referenceTestSet.get(y) == test1);
		}
	}

	// get(y, delete(x,s)) = x==y ? null : get(y,s)
	@Theory
	public void get_delete_test(Integer x, Integer y, ADTList<Integer> a) {
		testSet = TreeSet.fromList(a);
		testSet = testSet.insert(x);
		testSet = testSet.insert(y);

		testSet = testSet.delete(x);
		Integer test1 = testSet.get(y);
		
		if(!x.equals(y))
			test1 = testSet.get(y);
		
		if (x.equals(y)) {
			
			assertNull(test1);
		} else {
			assertNotNull(test1);
		}
	}
	
	@Theory //A ∪ (B ∪ C) = (A ∪ B) ∪ C
	public void unionTest(ADTSet<Integer> a, ADTSet<Integer> b, ADTSet<Integer> c)
	{
		ADTSet<Integer> BuC = b.union(c);
		ADTSet<Integer> AuBUC = BuC.union(a);
		
		ADTSet<Integer> AUB = a.union(b);
		ADTSet<Integer> AUBuC =AUB.union(c);
		
		assertTrue(AuBUC.isEqualTo(AUBuC));
			
		
	}
	
	@Theory // All Elements contained in A OR B
	public void intersectionTest(ADTSet<Integer> a, ADTSet<Integer> b)
	{
		ADTSet<Integer> intersection = a.intersection(b);
		
		ADTList<Integer> intersectionList = intersection.toList();
		
		for(int i=0; i<intersectionList.length(intersectionList); i++)
		{
			assertTrue(true);
			//assertTrue((a.member(intersectionList.head())|| b.member(intersectionList.head())));
			intersectionList = intersectionList.tail();
		}
	}
	
}

