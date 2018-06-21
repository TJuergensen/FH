package ADTMap.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import ADTList.list.ADTList;
import ADTMap.Entry;
import ADTMap.ListMap;
import ADTMap.Map;
import ADTMap.Tuple;
import ADTSet.Set.ADTSet;
import ADTSet.Set.ListSet;

@RunWith(Theories.class)
public class MapTest {

	@DataPoint
	public static Map<String, Integer> testMap = ListMap.empty();

	@DataPoint
	public static ADTList<Tuple<String, Integer>> list = ADTList.list(new Tuple<String, Integer>("Hey", 0),
			new Tuple<String, Integer>("Ho", 1), new Tuple<String, Integer>("asd", 2),
			new Tuple<String, Integer>("asdfg", 3), new Tuple<String, Integer>("34trd", 4));
	@DataPoint
	public static ADTList<Tuple<String, Integer>> list2 = ADTList.list(new Tuple<String, Integer>("34trd", 4),
			new Tuple<String, Integer>("asd", 2), new Tuple<String, Integer>("fghfgh", 5),
			new Tuple<String, Integer>("erwrwer", 6), new Tuple<String, Integer>("vbnvbn", 7),
			new Tuple<String, Integer>("gdfhdfg", 8));

	@DataPoints
	public static ArrayList<Entry<String, Integer>> entries() {
		ArrayList<Entry<String, Integer>> ret = new ArrayList<Entry<String, Integer>>();
		ret.add(new Entry<String, Integer>("fghölpjö", 4));
		ret.add(new Entry<String, Integer>("dbre456", 6));
		ret.add(new Entry<String, Integer>("2345sgdf", 22));
		ret.add(new Entry<String, Integer>("asv3r", 123));
		ret.add(new Entry<String, Integer>("1235543z", 0));
		ret.add(new Entry<String, Integer>("yvxcbrth", 12346));
		ret.add(new Entry<String, Integer>("67gfh", 875623));
		ret.add(new Entry<String, Integer>("cvbcvb5", 13456));

		return ret;
	}

	@DataPoints
	public static int[] integers() {
		return new int[] { 876, -1, -10, -1234567, 1, 10, 1234567, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 2, 4, 3, 5,
				6, 7, 8, 9, 10, 11, 12, 13, 22, 52, 13456 };
	}

	@DataPoints
	public static String[] strings() {
		return new String[] { "asd", "fdg", "123", "fghfg", "nbmbnm", "ghm546", "fghölpjö", "yvxcbrth", "erwrwer",
				"34trd" };
	}

	// axioms: ∀m : Map, ∀k,k1,k2 : K, ∀v,v1,v2 : V
	// (1) isEmpty(empty) = true
	@Theory
	public void isEmpty_empty() {
		Map<String, Integer> test = ListMap.empty();
		assertTrue(test.isEmpty());
	}

	// (2) isEmpty(insert(k,v,m)) = false
	@Theory
	public void isEmpty_insert(Map<String, Integer> t, String k, int v) {
		t = t.insert(k, v);
		assertFalse(t.isEmpty());
	}

	// (3) member(k,empty) = false
	@Theory
	public void member_empty(Map<String, Integer> t, String k) {
		assertFalse(t.member(k));
	}

	// (4) member(k2,insert(k1,v,m)) = k1==k2 ? true : member(k2,m)
	@Theory
	public void member_insert(Map<String, Integer> t, String k1, String k2, int v) {
		t = t.insert(k1, v);
		if (k1.equals(k2)) {
			assertTrue(t.member(k2));
		} else {
			assertFalse(t.member(k2));
		}
	}

	// (5) member(k2,delete(k1,m)) = k1==k2 ? false : member(k2,m)
	@Theory
	public void member_delete(String k1, String k2, int v, int v2) {
		Map<String, Integer> t = ListMap.empty();
		t = t.insert(k1, v);
		t = t.insert(k2, v2);
		t = t.delete(k1);


		if (k1.equals(k2)) {
			assertFalse(t.member(k2));
		} else {
			assertTrue(t.member(k2));
		}

	}

	// (6) size(empty) = 0
	@Theory
	public void size_empty(Map<String, Integer> t) {
		assertTrue(t.isEmpty());
		assertTrue(t.size() == 0);

	}

	// (7) size(insert(k,v,m)) = !member(k,m) ? size(m)+1 : size(m)
	@Theory
	public void size_insert(Map<String, Integer> t, String k, int v, ADTList<Tuple<String, Integer>> list) {

		t = ListMap.fromList(list);
		t = t.insert(k, v);
		assertTrue(t.size() == (!(t.member(k)) ? t.size()+1 : t.size()) );
	}

	// (8) insert(k2,v,insert(k1,v,m)) = insert(k1,v,insert(k2,v,m))
	@Theory
	public void insert_insert_one(Map<String, Integer> t, Map<String, Integer> t2, String k1, String k2, int v) {
		t = t.insert(k1, v);
		t = t.insert(k2, v);

		t2 = t2.insert(k2, v);
		t2 = t2.insert(k1, v);
		assertTrue(t.isEqualTo(t2));
	}

	// (9) insert(k,v2,insert(k,v1,m)) = insert(k,v2,m)
	@Theory
	public void insert_insert_two(Map<String, Integer> t, Map<String, Integer> t2, String k1, int v, int v2) {
		t = t.insert(k1, v);
		t = t.insert(k1, v2);

		t2 = t2.insert(k1, v2);
		assertTrue(t.isEqualTo(t2));
	}

	// (10) delete(k2,insert(k1,v,m)) = k1==k2 ? delete(k2,m) :
	// insert(k1,v,delete(k2,m))
	@Theory
	public void delete_insert(Map<String, Integer> t1, Map<String, Integer> t2, String k1, String k2, int v,
			ADTList<Tuple<String, Integer>> a) {

		t1 = ListMap.fromList(a);
		t1.insert(k1, v);
		t1.delete(k2);

		if (k1.equals(k2)) {
			t2 = ListMap.fromList(a);
			t2.insert(k1, v);
			t2.delete(k2);
			assertTrue(t2.isEqualTo(t1));
		} else {
			t2 = ListMap.fromList(a);
			t2.insert(k2, v);
			t2.delete(k2);
			t2.insert(k1, v);
			assertTrue(t2.isEqualTo(t1));
		}

	}

	// (11) lookup(k2,insert(k1,v,m)) = k1==k2 ? v : lookup(k2,m)
	@Theory
	public void lookup_insert(Map<String, Integer> t, String k1, String k2, int v2, int v ,ADTList<Tuple<String, Integer>> list) {
		t  = ListMap.fromList(list);
		t = t.insert(k2, v2);
		t = t.insert(k1, v);
		
		assertTrue(t.lookup(k2) == (k1.equals(k2) ? v : t.lookup(k2)));
	}

	// (12) lookup(k2,delete(k1,m)) = k1==k2 ? null : lookup(k2,m)
	@Theory
	public void lookup_delete(Map<String, Integer> t, String k1, String k2, int v, ADTList<Tuple<String, Integer>> list) {
		t  = ListMap.fromList(list);
		t = t.insert(k1, v);
		t = t.insert(k2, v);
		t = t.delete(k1);
		
		assertTrue(t.lookup(k2) == (k1.equals(k2) ? null : t.lookup(k2)));
	}
	
	@Theory
	public void testGroupBy(String k1, String k2, int v1, int v2){
		ADTList<Payment> list = ADTList.list(new Payment(k1, v1),new Payment(k2, v2));
		Map<String, ADTList<Payment>> map = list.groupBy(x->x.name);
		
		if(k1.equals(k2))
		{
			int shouldBe = v1+v2;
			ADTList<Payment> workList = map.get(k2).getValue();
			int is = workList.head().value;
			workList = workList.tail();
			is += workList.head().value;
			assertTrue(shouldBe == is);
		}
	}
	
}
