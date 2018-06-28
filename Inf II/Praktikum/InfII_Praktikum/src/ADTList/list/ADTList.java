package ADTList.list;

import java.util.function.Function;

import ADTMap.ListMap;
import ADTMap.Map;
import ADTSet.Set.ADTSet;
import ADTSet.Set.ListSet;

public abstract class ADTList<A> {

	public abstract A head();

	public abstract ADTList<A> tail();

	public abstract boolean isEmpty();

	@SuppressWarnings("rawtypes")
	public static final ADTList NIL = new Nil();

	@SuppressWarnings("unchecked")
	public static <A> ADTList<A> list() {
		return NIL;
	}

	@SafeVarargs
	public static <A> ADTList<A> list(A... a) {
		ADTList<A> n = list();
		for (int i = a.length - 1; i >= 0; i--) {
			n = new Cons<>(a[i], n);
		}
		return n;
	}

	public ADTList<A> cons(A a) {
		return new Cons<>(a, this);
	}

	public ADTList<A> reverse() {
		ADTList<A> toRev = this;
		ADTList<A> ret = ADTList.list();

		while (!toRev.isEmpty()) {
			ret = ret.cons(toRev.head());
			toRev = toRev.tail();
		}

		return ret;
	}

	public ADTSet<A> toSet(ADTList<A> list) {

		return ListSet.fromList(list);
	}

	public ADTList<A> nub(ADTList<A> list) {
		ADTSet<A> ret = ListSet.fromList(list);
		return ret.toList();
	}

	public static <A> ADTList<A> setHead(ADTList<A> list, A h) {
		if (list.isEmpty()) {
			throw new IllegalStateException("List is empty but setHead() was called");
		} else {
			return new Cons<>(h, list.tail());
		}
	}

	public static Integer sum(ADTList<Integer> ints) {
		// return ints.isEmpty() ? 0 : ints.head() + sum(ints.tail());
		return foldr(x -> y -> x + y, 0, ints);
		// return foldl(y -> x -> x + y, 0, ints);
	}

	public static Double prod(ADTList<Double> doubles) {
		// return doubles.isEmpty() ? 1.0 : doubles.head() * prod(doubles.tail());
		return foldr(x -> y -> x + y, 1.0, doubles);
		// return foldl(y -> x -> x + y, 1.0, doubles);
	}

	public Boolean any(Function<A, Boolean> p, ADTList<A> list) {
		// return p.apply(list.head()) || any(p, list.tail());
		// return foldl(x -> y -> p.apply(y), false, list);
		return foldl(x -> xs -> x || p.apply(xs), false, list);

	}

	public boolean all(Function<A, Boolean> p, ADTList<A> list) {
		// List<A> q = filter(p, list) ;
		// return (length(q) == length(list));
		return !any(x -> !p.apply(x), list);
	}

	public boolean elem(A x, ADTList<A> list) {
		// if(list.isEmpty())
		// return false;
		// if (list.head() == x)
		// return true;
		// else return elem(x,list.tail());
		return this.any(z -> z.equals(x), this);
	}

	@SuppressWarnings("hiding")
	public <A> Integer length(ADTList<A> list) {
		// if(list.isEmpty())
		// {
		// return 0;
		// } else {
		// return 1+ length(list.tail());
		// }
		return foldl(y -> x -> y + 1, 0, list);
	}

	public static <A> ADTList<A> append(ADTList<A> list1, ADTList<A> list2) {
		// return list1.isEmpty() ? list2 : new Cons<>(list1.head(),
		// append(list1.tail(), list2));
		return foldr(x -> l -> new Cons<>(x, l), list1, list2);
	}

	static <A, B> ADTList<B> map(Function<A, B> f, ADTList<A> list) {
		// ADTList<B> retList = list();
		// while(!list.isEmpty())
		// {
		// retList.cons(f.apply(list.head()));
		// list = list.tail();
		// }
		// return retList;

		return foldr(x -> xs -> xs.cons(f.apply(x)), list(), list);
	}

	public <B> Map<B, ADTList<A>> groupBy(Function<A, B> f) {
		ADTList<A> workList = this;
		Map<B, ADTList<A>> m = ListMap.empty();
		while (!workList.isEmpty()) {
			ADTList<A> rt = ADTList.list();
			final B k = f.apply(workList.head());
			if (m.get(k) != null) {
				rt = m.get(k).getValue().cons(workList.head());
			} else {
				rt = rt.cons(workList.head());
			}
			m = m.insert(k, rt);
			workList = workList.tail();
		}
		return m;
	}

	public static <A> ADTList<A> filter(Function<A, Boolean> p, ADTList<A> list) {
		// if (list.isEmpty()) {
		// return list();
		// }
		// return p.apply(list.head()) ? new Cons<>(list.head(), list.tail()) :
		// filter(p, list.tail());

		// return retList;
		return foldr(x -> xs -> p.apply(x) ? xs.cons(x) : xs, list(), list);
	}

	public static <A, B> B foldr(Function<A, Function<B, B>> f, B s, ADTList<A> list) {
		return list.isEmpty() ? s : f.apply(list.head()).apply(foldr(f, s, list.tail()));
	}

	public static <A, B> B foldl(Function<B, Function<A, B>> f, B s, ADTList<A> list) {
		return list.isEmpty() ? s : foldl(f, f.apply(s).apply(list.head()), list.tail());
	}

	private ADTList() {
	}

	// ----------------------------------------------------------------------------------------------------------

	private static class Nil<A> extends ADTList<A> {
		private Nil() {
		}

		public A head() {
			throw new IllegalStateException("List is empty but head() was called");
		}

		public ADTList<A> tail() {
			throw new IllegalStateException("List is empty but tail() was called");
		}

		public boolean isEmpty() {
			return true;
		}

		public String toString() {
			return "[NIL]";
		}
	}

	// -------------------------------------------------------------------------------------

	private static class Cons<A> extends ADTList<A> {

		private final A head;
		private final ADTList<A> tail;

		private Cons(A head, ADTList<A> tail) {
			this.head = head;
			this.tail = tail;
		}

		@Override
		public A head() {
			return head;
		}

		@Override
		public ADTList<A> tail() {
			return tail;
		}

		@Override
		public boolean isEmpty() {
			return false;
		}

		public String toString() {
			return toString(this).toString();
		}

		// private StringBuilder toString(List<A> list)
		// {
		// return list.isEmpty() ? new StringBuilder() :
		// toString(list.tail()).insert(0,", ").insert(0,list.head());
		// }

		private StringBuilder toString(ADTList<A> list) {

			return foldr(x -> sb -> sb.insert(0, ", ").insert(0, x), new StringBuilder(), list);
		}
	}

	// @SuppressWarnings("unchecked")
	// public static <A> ADTList<A> list() {
	// return NIL;
	// }
}
