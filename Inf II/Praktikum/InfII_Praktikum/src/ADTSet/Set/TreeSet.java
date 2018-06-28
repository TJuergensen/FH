package ADTSet.Set;

import java.util.function.Function;

import ADTList.list.ADTList;
import Tree.Tree;

public class TreeSet<A extends Comparable<A>> implements ADTSet<A> {

	private final Tree<A> t;

	private TreeSet() {
		t = Tree.empty();
	}

	@SafeVarargs
	private TreeSet(A... a) {
		t = Tree.tree(a);

	}

	private TreeSet(Tree<A> a) {
		t = a;
	}

	private TreeSet(ADTList<A> a) {
		t = Tree.tree(a);
	}

	public static <A extends Comparable<A>> TreeSet<A> empty() {
		return new TreeSet<A>();
	}

	public static <A extends Comparable<A>> TreeSet<A> fromTree(Tree<A> a) {
		return new TreeSet<A>(a);
	}

	public static <A extends Comparable<A>> TreeSet<A> fromList(ADTList<A> xs) {
		return new TreeSet<A>(xs);
	}

	@SafeVarargs
	public static <A extends Comparable<A>> TreeSet<A> set(A... as) {
		return new TreeSet<A>(as);
	}

	@Override
	public ADTSet<A> insert(A e) {
		Tree<A> ret = t;
		ret = ret.insert(e);
		return fromTree(ret);
	}
	
	public String toString() {
		if (this.isEmpty())
			return "{}";
		return "{" + t.toString() + "}";
	}

	@Override
	public ADTSet<A> delete(A e) {
		Tree<A> ret = t;
		ret = ret.delete(e);
		return fromTree(ret);
	}

	@Override
	public boolean member(A e) {
		return t.member(e);
	}

	@Override
	public int size() {
		return t.size();
	}

	@Override
	public boolean isEmpty() {
		return t.isEmpty();
	}

	@Override
	public A get(A e) {
		return t.get(e);
	}

	@Override
	public ADTList<A> toList() {
		return t.toListInorder();
	}

	@Override
	public boolean any(Function<A, Boolean> p) {
		return t.toListInorder().any(p, t.toListInorder());
	}

	@Override
	public boolean all(Function<A, Boolean> p) {
		return t.toListInorder().all(p, t.toListInorder());
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

	@Override
	public boolean isEqualTo(ADTSet<A> s) {
		//return (this.isSubsetOf(s) && s.isSubsetOf(this));
		
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

	@Override
	public boolean disjoint(ADTSet<A> s) {
		
		return (!this.isSubsetOf(s) && !s.isSubsetOf(this));
	}

	@Override
	public ADTSet<A> union(ADTSet<A> s) {
		return fromList(ADTList.append(s.toList(), this.toList()));
	}

	@Override
	public ADTSet<A> intersection(ADTSet<A> s) {
		return filter(y -> s.member(y), this);
	}

	public static <A, B> B foldr(Function<A, Function<B, B>> f, B s, ADTSet<A> set) {
		return ADTList.foldr(f, s, set.toList());
	}

	public static <A, B> B foldl(Function<B, Function<A, B>> f, B s, ADTSet<A> set) {
		return ADTList.foldl(f, s, set.toList());
	}

}
