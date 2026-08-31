package collection.set;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SetStudy {
	public static void main(String[] args) {
		System.out.println("1. HashSet");
		hashSetExample();

		System.out.println("\n2. LinkedHashSet");
		linkedHashSetExample();

		System.out.println("\n3. TreeSet");
		treeSetExample();

		System.out.println("\n4. Set example");
		duplicateExample();

		System.out.println("\n5. Optimization");
		optimizationExample();
	}

	static void hashSetExample() {
		Set<Integer> set = new HashSet<>();

		set.add(30);
		set.add(10);
		set.add(20);
		set.add(10);

		System.out.println("Set = " + set);
		System.out.println("Size = " + set.size());
		System.out.println("Contains 20 = " + set.contains(20));

		set.remove(20);

		System.out.println("After remove = " + set);
	}

	static void linkedHashSetExample() {
		Set<Integer> set = new LinkedHashSet<>();

		set.add(30);
		set.add(10);
		set.add(20);
		set.add(10);

		System.out.println("Set = " + set);
	}

	static void treeSetExample() {
		TreeSet<Integer> set = new TreeSet<>();

		set.add(30);
		set.add(10);
		set.add(50);
		set.add(20);
		set.add(40);

		System.out.println("Set = " + set);

		System.out.println("First = " + set.first());
		System.out.println("Last = " + set.last());

		System.out.println("Higher 30 = " + set.higher(30));
		System.out.println("Lower 30 = " + set.lower(30));
	}

	static void duplicateExample() {
		List<String> names = List.of(
			"Tom",
			"Bob",
			"Tom",
			"Alice",
			"Bob"
		);

		Set<String> uniqueNames = new LinkedHashSet<>(names);

		System.out.println("Original = " + names);
		System.out.println("Unique = " + uniqueNames);
	}

	static void optimizationExample() {
		List<Integer> list = new ArrayList<>();
		Set<Integer> set = new HashSet<>();

		for (int i = 0; i < 10000; i++) {
			list.add(i);
			set.add(i);
		}

		int target = 9999;

		System.out.println(
			"List contains = " + list.contains(target)
		);

		System.out.println(
			"Set contains = " + set.contains(target)
		);

		System.out.println(
			"List contains complexity = O(n)"
		);

		System.out.println(
			"HashSet contains average complexity = O(1)"
		);
	}
}
