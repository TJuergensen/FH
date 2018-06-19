package ADTSet.Set;

import java.util.function.Function;

import ADTList.list.ADTList;

public class ListSet<A> implements ADTSet<A> {

	private final ADTList<A> setElements;

	private ListSet() {
		this.setElements = ADTList.list();
	}

	@SafeVarargs
	private ListSet(A... a) {
		setElements = ADTList.list(a);
	}

	private ListSet(ADTList<A> list) {
		setElements = ADTList.append(list, ADTList.list());
	}

	public static <A> ADTSet<A> empty() {
		return new ListSet<A>();
	}

	public static <A> ADTSet<A> fromList(ADTList<A> list) {

		ADTList<A> ret = ADTList.list();
		ADTList<A> tmp = list;
		int tmplen = tmp.length(tmp);

		for (int i = 0; i < tmplen; i++) {
			if (!ret.elem(tmp.head(), ret)) {
				ret = ret.cons(tmp.head());
			}
			tmp = tmp.tail();
		}

		return new ListSet<A>(ret);

	}

	@SafeVarargs
	public static <A> ADTSet<A> set(A... es) {
		return fromList(ADTList.list(es));
	}

	@Override
	public ADTSet<A> insert(A e) {
		ADTList<A> ret = this.toList();

		if (this.member(e)) {
			ret = (this.delete(e).toList());
		}
		return fromList(ret.cons(e));
	}

	@Override
	public ADTSet<A> delete(A e) {
		return new ListSet<A>(ADTList.filter(x -> !(e.equals(x)), setElements));
	}

	@Override
	public boolean member(A e) {

		return setElements.elem(e, setElements);
	}

	@Override
	public int size() {
		return setElements.length(setElements);
	}

	@Override
	public boolean isEmpty() {
		return setElements.isEmpty();
	}

	@Override
	public A get(A e) {

		return member(e) ? ADTList.filter(x -> x.equals(e), this.toList()).head() : null;
	}

	@Override
	public ADTList<A> toList() {
		return setElements;
	}

	public String toString() {
		if (this.isEmpty())
			return "{}";
		return "{" + setElements.toString() + "}";
	}

	@Override
	public boolean any(Function<A, Boolean> p) {
		return setElements.any(p, setElements);

	}

	@Override
	public boolean all(Function<A, Boolean> p) {
		return setElements.all(p, setElements);
	}

	@Override
	public boolean isSubsetOf(ADTSet<A> s) {
		// Work on the lists of these two:

		if (this.isEmpty() || s.isEmpty())
			return true;

		ADTList<A> orig = this.toList();
		ADTList<A> other = s.toList();
		for (int i = 0; i < orig.length(orig); i++) {
			// if element of orig is NOT in s, return false, Else return true
			if (!(other.elem(orig.head(), other))) {
				return false;
			}
			orig = orig.tail();
		}
		return true;
	}

	// Same as Subset, just another condition: of element of orig IS in s, return
	// false
	@Override
	public boolean disjoint(ADTSet<A> s) {
		// Work on the lists of these two:
		ADTList<A> orig = this.toList();
		ADTList<A> other = s.toList();
		for (int i = 0; i < orig.length(orig); i++) {
			// if element of orig is NOT in s, return false, Else return true
			if (other.elem(orig.head(), other)) {
				return false;
			}
			orig = orig.tail();
		}
		return true;
	}

	@Override
	public boolean isEqualTo(ADTSet<A> s) {
		ADTList<A> origThis = this.toList();
		ADTList<A> origOther = s.toList();
		int thisLen = origThis.length(origThis);
		int otherLen = origOther.length(origOther);
		boolean ret = true;
		// Just comparing these Lists on Equality would NOT do the job! Within sets, the
		// position doesn't matter
		if (thisLen != otherLen) {
			ret = false;
		} else {
			while (!origThis.isEmpty() || !origOther.isEmpty()) {
				if (!s.member(origThis.head()) || (!s.member(origOther.head()))) {
					ret = false;
				}
				origThis = origThis.tail();
				origOther = origOther.tail();
			}
		}
		return ret;
	}


	public static <A,B> B foldr(Function<A, Function<B, B>> f, B s, ADTSet<A> set) {
		return ADTList.foldr(f, s, set.toList());
	}

	public static <A,B> B foldl(Function<B, Function<A, B>> f, B s, ADTSet<A> set) {
		return ADTList.foldl(f, s, set.toList());
	}

	@Override
	public ADTSet<A> filter(Function<A, Boolean> f, ADTSet<A> set) {

		if (this.isEmpty()) {
			return empty();
		}

		ADTList<A> ret = set.toList();
		return ADTList.filter(f, ret).toSet(ret);
	}

	@Override
	public ADTSet<A> union(ADTSet<A> s) {
		return fromList(ADTList.append(s.toList(), this.toList()));
	}

	@Override
	public ADTSet<A> intersection(ADTSet<A> s) {
		return filter(y -> s.member(y), this);

	}
}
