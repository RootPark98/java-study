package collection.list;

public class ListStudy {
	public static void main(String[] args) {
		System.out.println("1. Interface abstraction");
		interfaceExample();

		System.out.println("\n2. Dependency injection");
		dependencyInjectionExample();

		System.out.println("\n3. Runtime dependency");
		runtimeDependencyExample();

		System.out.println("\n4. Performance comparison");
		performanceComparison();
	}

	static void interfaceExample() {
		MyList<String> list = new MyArrayList<>();

		list.add("A");
		list.add("B");
		list.add("C");

		System.out.println("Value = " + list.get(1));
		System.out.println("size = " + list.size());
	}

	static void dependencyInjectionExample() {
		MyList<String> arrayList = new MyArrayList<>();

		ListService service1 = new ListService(arrayList);

		service1.add("Java");
		service1.add("Spring");
		service1.print();

		MyList<String> linkedList = new MyLinkedList<>();

		ListService service2 = new ListService(linkedList);

		service2.add("Java");
		service2.add("Spring");
		service2.print();
	}

	static void runtimeDependencyExample() {
		boolean useLinkedList = true;

		MyList<String> list;

		if (useLinkedList) {
			list = new MyLinkedList<>();
		} else {
			list = new MyArrayList<>();
		}

		ListService service = new ListService(list);

		service.add("A");
		service.add("B");

		service.print();
	}

	static void performanceComparison() {
		MyList<Integer> arrayList = new MyArrayList<>();

		MyList<Integer> linkedList = new MyLinkedList<>();

		for (int i = 0; i < 5; i++) {
			arrayList.add(i);
			linkedList.add(i);
		}

		System.out.println("ArrayList index 4 = " + arrayList.get(4));

		System.out.println("LinkedList index 4 = " + linkedList.get(4));

		System.out.println("ArrayList get = O(1)");

		System.out.println("LinkedList get = O(n)");
	}

	interface MyList<T> {
		void add(T value);

		T get(int index);

		int size();
	}

	static class MyArrayList<T> implements MyList<T> {
		private Object[] elements = new Object[5];

		private int size;

		@Override
		public void add(T value) {
			if (size == elements.length) {
				grow();
			}

			elements[size] = value;
			size++;
		}

		@SuppressWarnings("unchecked")
		@Override
		public T get(int index) {
			checkIndex(index);

			return (T) elements[index];
		}

		@Override
		public int size() {
			return size;
		}

		private void grow() {
			Object[] newElements = new Object[elements.length * 2];

			System.arraycopy(
				elements,
				0,
				newElements,
				0,
				elements.length
			);

			elements = newElements;
		}

		private void checkIndex(int index) {
			if (index < 0 || index >= size) {
				throw new IndexOutOfBoundsException();
			}
		}
	}

	static class MyLinkedList<T> implements MyList<T> {
		private Node<T> first;
		private Node<T> last;

		private int size;

		@Override
		public void add(T value) {
			Node<T> newNode = new Node<>(value);

			if (first == null) {
				first = newNode;
				last = newNode;
			} else {
				last.next = newNode;
				last = newNode;
			}

			size++;
		}

		@Override
		public T get(int index) {
			checkIndex(index);

			Node<T> current = first;

			for (int i = 0; i < index; i++) {
				current = current.next;
			}

			return current.value;
		}

		@Override
		public int size() {
			return size;
		}

		private void checkIndex(int index) {
			if (index < 0 || index >= size) {
				throw new IndexOutOfBoundsException();
			}
		}
	}

	static class Node<T> {
		T value;
		Node<T> next;

		Node(T value) {
			this.value = value;
		}
	}

	static class ListService {
		private final MyList<String> list;

		ListService(MyList<String> list) {
			this.list = list;
		}

		void add(String value) {
			list.add(value);
		}

		void print() {
			for (int i = 0; i < list.size(); i++) {
				System.out.println("Value = " + list.get(i));
			}
		}
	}
}
