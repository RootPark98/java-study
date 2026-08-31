package collection.framework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class CollectionFrameworkStudy {
	public static void main(String[] args) {
		System.out.println("1. Custom Iterable and Iterator");
		customIterableExample();

		System.out.println("\n2. Enhanced for");
		enhancedForExample();

		System.out.println("\n3. Java Iterable and Iterator");
		javaIterableExample();

		System.out.println("\n4. Comparable");
		comparableExample();

		System.out.println("\n5. Comparator");
		comparatorExample();

		System.out.println("\n6. Sorting");
		sortingExample();

		System.out.println("\n7. Collection utilities");
		collectionUtilityExample();
	}

	static void customIterableExample() {
		MyCollection<String> collection = new MyCollection<>();

		collection.add("A");
		collection.add("B");
		collection.add("C");

		Iterator<String> iterator = collection.iterator();

		while (iterator.hasNext()) {
			String value = iterator.next();

			System.out.println("Value = " + value);
		}
	}

	static void enhancedForExample() {
		MyCollection<String> collection = new MyCollection<>();

		collection.add("Java");
		collection.add("Spring");
		collection.add("Database");

		for (String value : collection) {
			System.out.println("Value = " + value);
		}
	}

	static void javaIterableExample() {
		List<String> list = new ArrayList<>();

		list.add("A");
		list.add("B");
		list.add("C");

		Iterator<String> iterator = list.iterator();

		while (iterator.hasNext()) {
			System.out.println("Iterator = " + iterator.next());
		}

		for (String value : list) {
			System.out.println("Enhanced for = " + value);
		}
	}

	static void comparableExample() {
		List<Member> members = new ArrayList<>();

		members.add(
			new Member("Tom", 30)
		);

		members.add(
			new Member("Alice", 20)
		);

		members.add(
			new Member("Bob", 25)
		);

		Collections.sort(members);

		System.out.println(
			"Natural order = " + members
		);
	}

	static void comparatorExample() {
		List<Member> members = createMembers();

		Comparator<Member> nameComparator = Comparator.comparing(Member::getName);

		members.sort(nameComparator);

		System.out.println(
			"Name order = " + members
		);

		Comparator<Member> ageDescending = Comparator.comparingInt(Member::getAge).reversed();

		members.sort(ageDescending);

		System.out.println(
			"Age descending = " + members
		);
	}

	static void sortingExample() {
		List<Member> members = createMembers();

		members.sort(null);

		System.out.println(
			"Natural = " + members
		);

		Comparator<Member> nameThenAge =
				Comparator
				.comparing(Member::getName)
				.thenComparingInt(Member::getAge);

		members.sort(nameThenAge);

		System.out.println(
			"Name then age = " + members
		);

		members.sort(
			Comparator.reverseOrder()
		);

		System.out.println(
			"Reverse natural = " + members
		);
	}

	static void collectionUtilityExample() {
		List<Integer> numbers = new ArrayList<>(List.of(30, 10, 50, 20, 40));

		System.out.println(
			"Original = " + numbers
		);
		Collections.sort(numbers);

		System.out.println(
			"Sorted = " + numbers
		);

		System.out.println(
			"Min = "
				+ Collections.min(numbers)
		);

		System.out.println(
			"Max = "
				+ Collections.max(numbers)
		);

		int index = Collections.binarySearch(numbers, 30);

		System.out.println(
			"Index of 30 = " + index
		);

		Collections.reverse(numbers);

		System.out.println(
			"Reversed = " + numbers
		);

		Collections.swap(
			numbers,
			0,
			numbers.size() - 1
		);

		System.out.println(
			"Swapped = " + numbers
		);
	}

	static List<Member> createMembers() {
		List<Member> members = new ArrayList<>();

		members.add(
			new Member("Tom", 30)
		);

		members.add(
			new Member("Alice", 20)
		);

		members.add(
			new Member("Bob", 25)
		);

		members.add(
			new Member("Tom", 22)
		);

		return members;
	}

	static class MyCollection<T> implements Iterable<T> {
		private final List<T> values = new ArrayList<>();

		void add(T value) {
			values.add(value);
		}

		@Override
		public Iterator<T> iterator() {
			return new MyIterator<>(values);
		}
	}

	static class MyIterator<T> implements Iterator<T> {
		private final List<T> values;

		private int currentIndex;

		MyIterator(List<T> values) {
			this.values = values;
		}

		@Override
		public boolean hasNext() {
			return currentIndex < values.size();
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}

			T value = values.get(currentIndex);

			currentIndex++;

			return value;
		}
	}

	static class Member implements Comparable<Member> {
		private final String name;
		private final int age;

		Member(
			String name,
			int age
		) {
			this.name = name;
			this.age = age;
		}

		String getName() {
			return name;
		}

		int getAge() {
			return age;
		}

		@Override
		public int compareTo(Member other) {
			int ageResult = Integer.compare(age, other.age);

			if (ageResult != 0) {
				return ageResult;
			}

			return name.compareTo(other.name);
		}

		@Override
		public String toString() {
			return name + ":" + age;
		}
	}
}
