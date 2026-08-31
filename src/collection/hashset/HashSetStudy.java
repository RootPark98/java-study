package collection.hashset;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class HashSetStudy {
	public static void main(String[] args) {
		System.out.println("1. String hash code");
		stringHashExample();

		System.out.println("\n2. MyHashSetV1");
		hashSetV1Example();

		System.out.println("\n3. Java hashCode");
		javaHashCodeExample();

		System.out.println("\n4. MyHashSetV2");
		hashSetV2Example();

		System.out.println("\n5. Custom object without equals and hashCode");
		badMemberExample();

		System.out.println("\n6. Custom object with equals and hashCode");
		goodMemberExample();

		System.out.println("\n7. Generic and interface");
		genericExample();
	}

	static void stringHashExample() {
		String first = "Java";
		String second = "Spring";

		System.out.println(
			first + " hash = " + first.hashCode()
		);

		System.out.println(
			second + " hash = " + second.hashCode()
		);

		System.out.println(
			"Manual Java hash = "
				+ createStringHash(first)
		);

		String collision1 = "FB";
		String collision2 = "Ea";

		System.out.println(
			collision1 + " hash = "
				+ collision1.hashCode()
		);

		System.out.println(
			collision2 + " hash = "
				+ collision2.hashCode()
		);
	}

	static int createStringHash(String value) {
		int hash = 0;

		for (int i = 0; i < value.length(); i++) {
			hash = 31 * hash + value.charAt(i);
		}

		return hash;
	}

	static void hashSetV1Example() {
		MyHashSetV1 set = new MyHashSetV1(8);

		set.add("Java");
		set.add("Spring");
		set.add("Hash");

		System.out.println(
			"Contains Java = "
				+ set.contains("Java")
		);

		System.out.println(
			"Add Java again = "
				+ set.add("Java")
		);

		System.out.println("Size = " + set.size());
	}

	static void javaHashCodeExample() {
		Integer number = 100;
		String text = "Java";

		System.out.println(
			"Integer hash = " + number.hashCode()
		);

		System.out.println(
			"String hash = " + text.hashCode()
		);

		Member member = new Member("user1", "Sejin");

		System.out.println(
			"Member hash = " + member.hashCode()
		);
	}

	static void hashSetV2Example() {
		MyHashSetV2 set = new MyHashSetV2(8);

		set.add("Java");
		set.add(100);
		set.add("Spring");

		System.out.println(
			"Contains Java = "
				+ set.contains("Java")
		);

		System.out.println(
			"Contains 100 = "
				+ set.contains(100)
		);
	}

	static void badMemberExample() {
		MemberWithoutOverride first = new MemberWithoutOverride("user1", "Sejin");

		MemberWithoutOverride second = new MemberWithoutOverride("user1", "Sejin");

		System.out.println(
			"Equals = " + first.equals(second)
		);

		System.out.println(
			"First hash = " + first.hashCode()
		);

		System.out.println(
			"Second hash = " + second.hashCode()
		);

		MyHashSetV2 set = new MyHashSetV2(8);

		set.add(first);
		set.add(second);

		System.out.println(
			"Set size = " + set.size()
		);
	}

	static void goodMemberExample() {
		Member first = new Member("user1", "Sejin");

		Member second = new Member("user1", "Sejin");

		System.out.println(
			"Equals = " + first.equals(second)
		);

		System.out.println(
			"First hash = " + first.hashCode()
		);

		System.out.println(
			"Second hash = " + second.hashCode()
		);

		MyHashSetV2 set = new MyHashSetV2(8);

		set.add(first);

		System.out.println(
			"Add same member = " +
				set.add(second)
		);

		System.out.println(
			"Set size = " + set.size()
		);
	}

	static void genericExample() {
		MySet<String> stringSet = new MyHashSetV4<>(8);

		stringSet.add("Java");
		stringSet.add("Spring");

		System.out.println(
			"Contains Java = "
				+ stringSet.contains("Java")
		);

		MySet<Member> memberSet = new MyHashSetV4<>(8);

		memberSet.add(
			new Member("user1", "Sejin")
		);

		System.out.println(
			"Contains member = "
				+ memberSet.contains(
					new Member("user1", "Sejin")
				)
		);
	}

	static class MyHashSetV1 {
		private final List<List<String>> buckets;
		private int size;

		MyHashSetV1(int capacity) {
			buckets = new ArrayList<>(capacity);

			for (int i = 0; i < capacity; i++) {
				buckets.add(new LinkedList<>());
			}
		}

		boolean add(String value) {
			int index = getIndex(value);

			List<String> bucket = buckets.get(index);

			if (bucket.contains(value)) {
				return false;
			}

			bucket.add(value);
			size++;

			return true;
		}

		boolean contains(String value) {
			int index = getIndex(value);

			return buckets.get(index).contains(value);
		}

		int size() {
			return size;
		}

		private int getIndex(String value) {
			int hash = value.hashCode();

			return Math.floorMod(
				hash,
				buckets.size()
			);
		}
	}

	static class MyHashSetV2 {
		private final List<List<Object>> buckets;
		private int size;

		MyHashSetV2(int capacity) {
			buckets = new ArrayList<>(capacity);

			for (int i = 0; i < capacity; i++) {
				buckets.add(new LinkedList<>());
			}
		}

		boolean add(Object value) {
			int index = getIndex(value);

			List<Object> bucket = buckets.get(index);

			if (bucket.contains(value)) {
				return false;
			}

			bucket.add(value);
			size++;

			return true;
		}

		boolean contains(Object value) {
			int index = getIndex(value);

			return buckets.get(index).contains(value);
		}

		int size() {
			return size;
		}

		private int getIndex(Object value) {
			int hash = value == null ? 0 : value.hashCode();

			return Math.floorMod(hash, buckets.size());
		}
	}

	interface MySet<T> {
		boolean add(T value);
		boolean contains(T value);
		boolean remove(T value);
		int size();
	}

	static class MyHashSetV4<T> implements MySet<T> {
		private final List<List<T>> buckets;
		private int size;

		MyHashSetV4(int capacity) {
			buckets = new ArrayList<>(capacity);

			for (int i = 0; i < capacity; i++) {
				buckets.add(new LinkedList<>());
			}
		}

		@Override
		public boolean add(T value) {
			int index = getIndex(value);

			List<T> bucket = buckets.get(index);

			if (bucket.contains(value)) {
				return false;
			}

			bucket.add(value);
			size++;

			return true;
		}

		@Override
		public boolean contains(T value) {
			int index = getIndex(value);

			return buckets.get(index).contains(value);
		}

		@Override
		public boolean remove(T value) {
			int index = getIndex(value);

			boolean removed = buckets.get(index).remove(value);

			if (removed) {
				size--;
			}

			return removed;
		}

		@Override
		public int size() {
			return size;
		}

		private int getIndex(T value) {
			int hash = value == null ? 0 : value.hashCode();

			return Math.floorMod(hash, buckets.size());
		}
	}

	static class MemberWithoutOverride {
		private final String id;
		private final String name;

		MemberWithoutOverride(
			String id,
			String name
		) {
			this.id = id;
			this.name = name;
		}
	}

	static class Member {
		private final String id;
		private final String name;

		Member(
			String id,
			String name
		) {
			this.id = id;
			this.name = name;
		}

		@Override
		public boolean equals(Object object) {
			if (this == object) {
				return true;
			}

			if (!(object instanceof Member member)) {
				return false;
			}

			return Objects.equals(
				id,
				member.id
			);
		}

		@Override
		public int hashCode() {
			return Objects.hash(id);
		}

		@Override
		public String toString() {
			return id + ":" + name;
		}
	}
}
