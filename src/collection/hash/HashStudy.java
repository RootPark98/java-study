package collection.hash;

public class HashStudy {
	public static void main(String[] args) {
		System.out.println("1. List vs Set");
		listVsSet();

		System.out.println("\n2. Linear Set");
		linearSetExample();

		System.out.println("\n3. Direct index");
		directIndexExample();

		System.out.println("\n4. Modulo");
		moduloExample();

		System.out.println("\n5. Hash collision");
		collisionExample();

		System.out.println("\n6. Hash Set");
		hashSetExample();
	}

	static void listVsSet() {
		int[] list = {10, 20, 20, 30};

		System.out.print("List = ");

		for (int value : list) {
			System.out.print(value + " ");
		}

		System.out.println();

		System.out.println(
			"List allows duplicates"
		);

		System.out.println(
			"Set does not allow duplicates"
		);
	}

	static void linearSetExample() {
		LinearSet set = new LinearSet();

		System.out.println("Add 10 = " + set.add(10));
		System.out.println("Add 20 = " + set.add(20));
		System.out.println("Add 30 = " + set.add(30));

		System.out.println("Add 20 = " + set.add(20));

		System.out.println("Contains 30 = " + set.contains(30));

		System.out.println("Contains 99 = " + set.contains(99));
	}

	static void directIndexExample() {
		boolean[] values = new boolean[100];

		values[10] = true;
		values[20] = true;
		values[30] = true;

		System.out.println("Contains 20 = " + values[20]);

		System.out.println("Contains 50 = " + values[50]);
	}

	static void moduloExample() {
		int capacity = 10;

		int[] values = {
			1,
			5,
			14,
			27,
			99
		};

		for (int value : values) {
			int index = Math.floorMod(
				value,
				capacity
			);

			System.out.println(
				"Value = " + value
					+ ", index = " + index
			);
		}
	}

	static void collisionExample() {
		int capacity = 10;

		int first = 1;
		int second = 11;
		int third = 21;

		System.out.println(
			first + " -> "
				+ Math.floorMod(first, capacity)
		);

		System.out.println(
			second + " -> "
				+ Math.floorMod(second, capacity)
		);

		System.out.println(
			third + " -> "
				+ Math.floorMod(third, capacity)
		);

		System.out.println(
			"Different values use the same index"
		);
	}

	static void hashSetExample() {
		MyHashSet set = new MyHashSet(10);

		set.add(1);
		set.add(11);
		set.add(21);
		set.add(5);
		set.add(15);

		System.out.println(
			"Add duplicate 11 = " + set.add(11)
		);

		System.out.println(
			"Contains 21 = " + set.contains(21)
		);

		System.out.println(
			"Contains 99 = " + set.contains(99)
		);

		System.out.println(
			"Size = " + set.size()
		);

		set.printBuckets();
	}

	static class LinearSet {
		private final int[] elements = new int[10];

		private int size;

		boolean add(int value) {
			if (contains(value)) {
				return false;
			}

			elements[size] = value;
			size++;

			return true;
		}

		boolean contains(int value) {
			for (int i = 0; i < size; i++) {
				if (elements[i] == value) {
					return true;
				}
			}

			return false;
		}
	}

	static class MyHashSet {
		private final Node[] buckets;

		private int size;

		MyHashSet(int capacity) {
			buckets = new Node[capacity];
		}

		boolean add(int value) {
			int index = getIndex(value);

			Node current = buckets[index];

			while (current != null) {
				if (current.value == value) {
					return false;
				}

				current = current.next;
			}

			Node newNode = new Node(value);

			newNode.next = buckets[index];
			buckets[index] = newNode;

			size++;

			return true;
		}

		boolean contains(int value) {
			int index = getIndex(value);

			Node current = buckets[index];

			while (current != null) {
				if (current.value == value) {
					return true;
				}

				current = current.next;
			}

			return false;
		}

		int size() {
			return size;
		}

		private int getIndex(int value) {
			return Math.floorMod(
				value,
				buckets.length
			);
		}

		void printBuckets() {
			for (int i = 0; i < buckets.length; i++) {
				System.out.println(i + " : ");

				Node current = buckets[i];

				while (current != null) {
					System.out.print(
						current.value + " "
					);

					current = current.next;
				}

				System.out.println();
			}
		}
	}

	static class Node {
		int value;
		Node next;

		Node(int value) {
			this.value = value;
		}
	}
}
