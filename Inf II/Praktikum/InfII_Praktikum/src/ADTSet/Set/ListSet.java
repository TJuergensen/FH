package ADTSet.Set;

import java.util.function.Function;

import ADTList.list.ADTList;

public class ListSet<A> implements ADTSet<A> {

	private ADTList<A> setElements = null;

	private ListSet() {
		this.setElements = ADTList.list();
	}

	@SafeVarargs
	private ListSet(A... a) {
		this.setElements = ADTList.list();
		for (int i = 0; i < a.length; i++) {
			this.insert(a[i]);
		}
	}

	public static <A> ADTSet<A> empty() {
		return new ListSet<A>();
	}

	public static <A> ADTSet<A> fromList(ADTList<A> list) {
		ADTSet<A> set = new ListSet<A>();
		ADTList<A> tmp = list;

		for (int i = 0; i < list.length(list); i++) {
			set.insert(tmp.head());
			// System.out.println(tmp.head());
			tmp = tmp.tail();
		}
		// System.out.println(set.toString());
		return set;
	}

	@SafeVarargs
	public static <A> ADTSet<A> set(A... es) {
		ADTSet<A> set = new ListSet<A>(es);
		return set;
	}

	@Override
	public ADTSet<A> insert(A e) {

		// if (setElements.elem(e, setElements)) {
		//
		// // Make a temporary List and go through setElements
		// ADTList<A> tempList = ADTList.list();
		// for (int i = 0; i < setElements.length(setElements); i++) {
		// // check all items of the set elements for by kicking them out of the list.
		// // if head() is item searched for, kick twice, without adding to tempList.
		// But
		// // still add it to temp List.
		// // Strange...but Task is Task.
		// if (setElements.head().equals(e)) {
		// setElements = setElements.tail();
		// tempList.cons(e);
		// }
		//
		// tempList.cons(setElements.head());
		// setElements = setElements.tail();
		// }
		// // setElemets is empty. Append tempList to reconstruct the list without but
		// WITH
		// // the item.
		// setElements = ADTList.append(setElements, tempList);
		// return this;
		// }
		//
		// setElements = setElements.cons(e);
		// System.out.println(setElements.toString());
		// return this;

		// All this above puts the new Element exactly where it was before.
		// In a Set, this is not necessary, therefore do the following:

		if (setElements.elem(e, setElements)) {
			setElements = ADTList.filter(x -> !(x.equals(e)), setElements);
		}
		setElements = setElements.cons(e);
		return this;

	}

	@Override
	public ADTSet<A> delete(A e) {

		setElements = ADTList.filter(x -> !(x.equals(e)), setElements);
		return this;

		// without using Filter method.
		// //Make a temporary List and go through elements of the set
		// ADTList<A> tempList = ADTList.list();
		// for (int i = 0; i < setElements.length(setElements); i++) {
		// //Add all elements from elements to temp and delete elements. Double kick if
		// e is wanted item..
		// if (setElements.head().equals(e))
		// setElements = setElements.tail();
		//
		// tempList.cons(setElements.head());
		// setElements = setElements.tail();
		// }
		//
		// //reunite those two Lists to reconstruct List.
		// setElements = setElements.append(setElements, tempList);
		// return this;
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
		ADTSet<A> s = fromList(this.toList());
		System.out.println("hey");
		s = filter(y -> y.equals(e));
		System.out.println("ho");
		if(s.isEmpty())
			return null;
		return s.toList().head();
	}

	@Override
	public ADTList<A> toList() {
		return setElements;
	}

	public String toString() {
		if(this.size() == 0)
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

	private <B> B foldr(Function<A, Function<B, B>> f, B s) {
		return toList().isEmpty() ? s : f.apply(setElements.head()).apply(foldr(f, s));
	}

	private <B> B foldl(Function<B, Function<A, B>> f, B s) {
		return isEmpty() ? s : foldl(f, f.apply(s).apply(setElements.head()));
	}

	public ADTSet<A> filter(Function<A, Boolean> f) {
		return foldr(x -> xs -> f.apply(x) ? xs.insert(x) : xs, empty());
	}

	public ADTSet<A> union(ADTSet<A> s) {
		return fromList(ADTList.append(s.toList(), this.toList()));
	}

	public ADTSet<A> intersection(ADTSet<A> s) {
		return filter(y -> member(y));

	}
}
