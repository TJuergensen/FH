package Tree;

import ADTList.list.ADTList;
import ADTQueue.ListQueue.ListQueue;

public abstract class Tree<A extends Comparable<A>> {

	@SuppressWarnings("rawtypes")
	private static Tree EMPTY = new Empty();

	abstract Tree<A> left();

	abstract Tree<A> right();

	public abstract A value();

	public abstract Tree<A> insert(A a);

	public abstract boolean member(A a);

	public abstract int size();

	public abstract int height();

	public abstract Tree<A> delete(A a);

	public abstract boolean isEmpty();

	public abstract A get(A x);

	public abstract ADTList<A> toListLevelorder();

	public abstract ADTList<A> toListInorder();

	public abstract ADTList<A> toListPreorder();

	public abstract ADTList<A> toListPostorder();

	public abstract int anzBlaetter();

	public abstract int anzKnotenMitEinemKind();

	public abstract int anzInnereKnoten();

	protected abstract Tree<A> removeMerge(Tree<A> ta);

	private static class Empty<A extends Comparable<A>> extends Tree<A> {

		@Override
		public A value() {
			throw new IllegalStateException("value() called on empty");
		}

		@Override
		Tree<A> left() {
			throw new IllegalStateException("left() called on empty");
		}

		@Override
		Tree<A> right() {
			throw new IllegalStateException("right() called on empty");
		}

		@Override
		public Tree<A> insert(A value) {
			return new T<>(empty(), value, empty());
		}

		@Override
		public boolean member(A a) {
			return false;
		}

		@Override
		public int size() {
			return 0;
		}

		@Override
		public int height() {
			return -1;
		}

		@Override
		public Tree<A> delete(A a) {
			return this;
		}

		@Override
		public boolean isEmpty() {
			return true;
		}

		@Override
		protected Tree<A> removeMerge(Tree<A> ta) {
			return ta;
		}

		@Override
		public A get(A x) {
			return null;
		}

		@Override
		public String toString() {
			return "E";
		}

		@Override
		public ADTList<A> toListLevelorder() {
			return ADTList.list();
		}

		@Override
		public ADTList<A> toListInorder() {
			return ADTList.list();
		}

		@Override
		public ADTList<A> toListPreorder() {
			return ADTList.list();
		}

		@Override
		public ADTList<A> toListPostorder() {
			return ADTList.list();
		}

		@Override
		public int anzBlaetter() {
			return 0;
		}

		@Override
		public int anzKnotenMitEinemKind() {
			return 0;
		}

		@Override
		public int anzInnereKnoten() {
			return 0;
		}
	}

	private static class T<A extends Comparable<A>> extends Tree<A> {

		private final Tree<A> left;
		private final Tree<A> right;
		private final A value;
		private final int height;
		private final int size;

		private T(Tree<A> left, A value, Tree<A> right) {
			this.left = left;
			this.right = right;
			this.value = value;
			this.height = 1 + Math.max(left.height(), right.height());
			this.size = 1 + left.size() + right.size();
		}

		@Override
		public A get(A x) {
			return get(x, null, this);
		}

		private A get(A x, A ret, Tree<A> t) {

			if (!t.isEmpty()) {
				if (x.compareTo(t.value()) == 0)
					ret = t.value();

				if (x.compareTo(t.value()) < 0)
					ret = get(x, ret, t.left());

				if (x.compareTo(t.value()) > 0)
					ret = get(x, ret, t.right());
			}

			return ret;
		}

		@Override
		public A value() {
			return value;
		}

		@Override
		Tree<A> left() {
			return left;
		}

		@Override
		Tree<A> right() {
			return right;
		}

		@Override
		public Tree<A> insert(A value) {
			return value.compareTo(this.value) < 0 ? new T<>(left.insert(value), this.value, right)
					: value.compareTo(this.value) > 0 ? new T<>(left, this.value, right.insert(value))
							: new T<>(this.left, value, this.right);
		}

		@Override
		public boolean member(A value) {
			return value.compareTo(this.value) < 0 ? left.member(value)
					: value.compareTo(this.value) <= 0 || right.member(value);
		}

		@Override
		public int size() {
			return size;
		}

		@Override
		public int height() {
			return height;
		}

		@Override
		public Tree<A> delete(A a) {
			if (a.compareTo(this.value) < 0) {
				return new T<>(left.delete(a), value, right);
			} else if (a.compareTo(this.value) > 0) {
				return new T<>(left, value, right.delete(a));
			} else {
				return left.removeMerge(right);
			}
		}

		@Override
		public boolean isEmpty() {
			return false;
		}

		protected Tree<A> removeMerge(Tree<A> ta) {
			if (ta.isEmpty()) {
				return this;
			}
			if (ta.value().compareTo(value) < 0) {
				return new T<>(left.removeMerge(ta), value, right);
			} else if (ta.value().compareTo(value) > 0) {
				return new T<>(left, value, right.removeMerge(ta));
			}
			throw new IllegalStateException("Shouldn't be merging two subtrees with the same value");
		}

		@Override
		public String toString() {
			return String.format("(T %s %s %s)", left, value, right);
		}

		public ADTList<A> toListLevelorder() {
			return levelOrder(this, ADTList.list(), new ListQueue<A>());
		}

		private ADTList<A> levelOrder(Tree<A> t, ADTList<A> ret, ListQueue<A> q) {
			if (!t.isEmpty()) {
				q.enQueue(t.value());
				ret = ret.cons(q.deQueueFront());
				if (t.left() != null)
					ret = levelOrder(t.left(), ret, q);
				if (t.right() != null)
					ret = levelOrder(t.right(), ret, q);
			}

			return ret;
		}

		@Override
		public int anzBlaetter() {
			return anzBlaetter(this, 0);
		}

		private int anzBlaetter(Tree<A> t, int ret) {
			if (!t.isEmpty()) {
				boolean onlyLeftChild = (!t.left().isEmpty()) && (t.right().isEmpty());
				boolean onlyRightChild = (t.left().isEmpty()) && (!t.right().isEmpty());
				boolean twoChildren = (!t.left().isEmpty()) && (!t.right().isEmpty());
				boolean noChildren = (t.left().isEmpty()) && (t.right().isEmpty());

				if (noChildren)
					ret++;
				if (onlyLeftChild)
					ret = anzBlaetter(t.left(), ret);
				if (onlyRightChild)
					ret = anzBlaetter(t.right(), ret);
				if (twoChildren)
					ret = anzBlaetter(t.right(), ret) + anzBlaetter(t.left(), ret);
			}
			return ret;
		}

		@Override
		public int anzKnotenMitEinemKind() {
			return anzKnotenMitEinemKind(this, 0);
		}

		private int anzKnotenMitEinemKind(Tree<A> t, int ret) {

			if (!t.isEmpty()) {
				boolean onlyLeftChild = (!t.left().isEmpty()) && (t.right().isEmpty());
				boolean onlyRightChild = (t.left().isEmpty()) && (!t.right().isEmpty());
				boolean twoChildren = (!t.left().isEmpty()) && (!t.right().isEmpty());

				if (onlyLeftChild || onlyRightChild) {
					ret++;
				}
				if (onlyLeftChild)
					ret = anzKnotenMitEinemKind(t.left(), ret);
				if (onlyRightChild)
					ret = anzKnotenMitEinemKind(t.right(), ret);

				if (twoChildren) {
					ret = anzKnotenMitEinemKind(t.left(), ret) + anzKnotenMitEinemKind(t.right(), ret);
				}

			}
			return ret;
		}

		@Override
		public int anzInnereKnoten() {
			return anzInnereKnoten(this, 0);
		}

		private int anzInnereKnoten(Tree<A> t, int ret) {

			if (!t.isEmpty()) {
				if (t.left() != null || t.right() != null)
					ret++;
				if (t.left() != null)
					ret = anzInnereKnoten(t.left(), ret);
				if (t.right() != null)
					anzInnereKnoten(t.right(), ret);
			}

			return ret;
		}

		@Override
		public ADTList<A> toListInorder() {
			return tiefensuche(this, "in", ADTList.list());
		}

		@Override
		public ADTList<A> toListPreorder() {
			return tiefensuche(this, "pre", ADTList.list());
		}

		@Override
		public ADTList<A> toListPostorder() {
			return tiefensuche(this, "post", ADTList.list());
		}

		private ADTList<A> tiefensuche(Tree<A> k, String order, ADTList<A> ret) {

			if (!k.isEmpty()) {
				//preorder
				if (order == "pre")
					ret = ret.cons(k.value());
				ret = tiefensuche(k.left(), order, ret);
				//inorder
				if (order == "in")
					ret = ret.cons(k.value());
				ret = tiefensuche(k.right(), order, ret);
				//postorder
				if (order == "post")
					ret = ret.cons(k.value());
			}

			return ret;
		}
	}

	@SuppressWarnings("unchecked")
	public static <A extends Comparable<A>> Tree<A> empty() {
		return EMPTY;
	}

	public static <A extends Comparable<A>> Tree<A> tree(ADTList<A> list) {
		Tree<A> ret = empty();

		while (!list.isEmpty()) {
			ret = ret.insert(list.head());
			list = list.tail();
		}

		return ret;
	}

	@SafeVarargs
	public static <A extends Comparable<A>> Tree<A> tree(A... as) {
		return tree(ADTList.list(as));
	}
}
