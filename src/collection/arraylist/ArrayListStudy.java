package collection.arraylist;

import java.util.Arrays;

public class ArrayListStudy {
	public static void main(String[] args) {
		System.out.println("1. Array and index");
		arrayAndIndex();

		System.out.println("\n2. Array insert");
		arrayInsert();

		System.out.println("\n3. Dynamic array");
		dynamicArray();

		System.out.println("\n4. List features");
		listFeatures();

		System.out.println("\n5. Generic list");
		genericList();
	}

	static void arrayAndIndex() {
		int[] numbers = {10, 20, 30, 40};

		System.out.println("Index 0 = " + numbers[0]);
		System.out.println("Index 2 = " + numbers[2]);
		System.out.println("Length = " + numbers.length);

		numbers[1] = 200;

		System.out.println("Changed = " + Arrays.toString(numbers));
	}

	static void arrayInsert() {
		int[] numbers = {10, 20, 30, 0};

		int insertIndex = 1;

		for (int i = 2; i >= insertIndex; i--) {
			numbers[i + 1] = numbers[i];
		}

		numbers[insertIndex] = 15;

		System.out.println("Inserted = " + Arrays.toString(numbers));
	}

	static void dynamicArray() {
		MyArrayList<String> list = new MyArrayList<>(2);

		list.add("A");
		list.add("B");

		System.out.println(list);

		list.add("C");

		System.out.println(list);

		list.add("D");
		list.add("E");

		System.out.println(list);
	}

	static void listFeatures() {
		MyArrayList<String> list = new MyArrayList<>();

		list.add("A");
		list.add("B");
		list.add("C");

		list.add(1, "X");

		System.out.println("After insert = " + list);

		System.out.println("Get index 2 = " + list.get(2));

		list.set(2, "Y");

		System.out.println("After set = " + list);

		String removed = list.remove(1);

		System.out.println("Removed = " + removed);
		System.out.println("After remove = " + list);
		System.out.println("Size = " + list.size());
	}

	static void genericList() {
		MyArrayList<String> stringList = new MyArrayList<>();

		stringList.add("Java");
		stringList.add("Sprint");

		String text = stringList.get(0);

		System.out.println("Text = " + text);

		MyArrayList<Integer> numberList = new MyArrayList<>();

		numberList.add(10);
		numberList.add(20);

		int number = numberList.get(1);

		System.out.println("Number = " + number);
	}

	static class MyArrayList<T> {
		private static final int DEFAULT_CAPACITY = 5;

		private Object[] elements;
		private int size;

		MyArrayList() {
			this(DEFAULT_CAPACITY);
		}

		MyArrayList(int initialCapacity) {
			elements = new Object[initialCapacity];
		}

		void add(T value) {
			ensureCapacity();

			elements[size] = value;
			size++;
		}

		void add(int index, T value) {
			checkPositionIndex(index);
			ensureCapacity();

			for (int i = size - 1; i >= index; i--) {
				elements[i + 1] = elements[i];
			}

			elements[index] = value;
			size++;
		}

		T get(int index) {
			checkElementIndex(index);

			return (T) elements[index];
		}

		T set(int index, T value) {
			checkElementIndex(index);

			T oldValue = (T) elements[index];

			elements[index] = value;

			return oldValue;
		}

		T remove(int index) {
			checkElementIndex(index);

			T oldValue = (T) elements[index];

			for (int i = index; i < size - 1; i ++) {
				elements[i] = elements[i + 1];
			}

			size--;

			elements[size] = null;

			return oldValue;
		}

		int size() {
			return size;
		}

		private void ensureCapacity() {
			if(size < elements.length) {
				return;
			}

			int newCapacity = Math.max(elements.length * 2, 1);

			elements = Arrays.copyOf(
				elements,
				newCapacity
			);

			System.out.println(
				"Capacity increased to " + newCapacity
			);
		}

		private void checkElementIndex(int index) {
			if (index < 0 || index >= size) {
				throw new IndexOutOfBoundsException(
					"Index = " + index + ", size = " + size
				);
			}
		}

		private void checkPositionIndex(int index) {
			if (index < 0 || index > size) {
				throw new IndexOutOfBoundsException(
					"Index = " + index + ", size = " + size
				);
			}
		}

		@Override
		public String toString() {
			return Arrays.toString(
				Arrays.copyOf(elements, size)
			);
		}
	}
}
