package collection.linkedlist;

public class LinkedListStudy {
	public static void main(String[] args) {
		System.out.println("1. Node connection");
		nodeConnection();

		System.out.println("\n2. Traverse nodes");
		traverseNodes();

		System.out.println("\n3. Linked list");
		linkedListExample();

		System.out.println("\n4. Add and remove");
		addRemoveExample();

		System.out.println("\n5. Generic");
		genericExample();
	}

	static void nodeConnection() {
		Node<String> first = new Node<>("A");
		Node<String> second = new Node<>("B");
		Node<String> third = new Node<>("C");

		first.next = second;
		second.next = third;

		System.out.println(first.value);
		System.out.println(first.next.value);
		System.out.println(first.next.next.value);
	}

	static void traverseNodes() {
		Node<String> first = new Node<>("A");
		first.next = new Node<>("B");
		first.next.next = new Node<>("C");

		Node<String> current = first;

		while (current != null) {
			System.out.println("Value = " + current.value);
			current = current.next;
		}
	}

	static void linkedListExample() {
		MyLinkedList<String> list = new MyLinkedList<>();

		list.add("A");
		list.add("B");
		list.add("C");

		System.out.println("List = " + list);
		System.out.println("Index 1 = " + list.get(1));
		System.out.println("Size = " + list.size());
	}

	static void addRemoveExample() {
		MyLinkedList<String> list = new MyLinkedList<>();

		list.add("A");
		list.add("B");
		list.add("C");

		list.add(1, "X");

		System.out.println("After Insert = " + list);

		String removed = list.remove(2);

		System.out.println("Removed = " + removed);
		System.out.println("After remove = " + list);

		list.set(1, "Y");

		System.out.println("After set = " + list);
	}

	static void genericExample() {
		MyLinkedList<Integer> numberList = new MyLinkedList<>();

		numberList.add(10);
		numberList.add(20);
		numberList.add(30);

		int number = numberList.get(1);

		System.out.println("Number = " + number);

		MyLinkedList<String> textList = new MyLinkedList<>();

		textList.add("Java");
		textList.add("Spring");

		String text = textList.get(0);

		System.out.println("Text = " + text);
	}

	static class Node<T> {
		T value;
		Node<T> next;

		Node(T value) {
			this.value = value;
		}
	}

	static class MyLinkedList<T> {
		private Node<T> first;
		private int size;

		void add(T value) {
			add(size, value);
		}

		void add(int index, T value) {
			checkPositionIndex(index);

			Node<T> newNode = new Node<>(value);

			if (index == 0) {
				newNode.next = first;
				first = newNode;
			} else {
				Node<T> previous = getNode(index - 1);

				newNode.next = previous.next;
				previous.next = newNode;
			}

			size++;
		}

		T get(int index) {
			checkElementIndex(index);

			return getNode(index).value;
		}

		T set(int index, T value) {
			checkElementIndex(index);

			Node<T> node = getNode(index);

			T oldValue = node.value;

			node.value = value;

			return oldValue;
		}

		T remove(int index) {
			checkElementIndex(index);

			Node<T> removedNode;

			if (index == 0) {
				removedNode = first;
				first = first.next;
			} else {
				Node<T> previous = getNode(index - 1);

				removedNode = previous.next;
				previous.next = removedNode.next;
			}

			removedNode.next = null;

			size--;

			return removedNode.value;
		}

		int size() {
			return size;
		}

		private Node<T> getNode(int index) {
			Node<T> current = first;

			for (int i = 0; i < index; i++) {
				current = current.next;
			}

			return current;
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
			StringBuilder builder = new StringBuilder("[");

			Node<T> current = first;

			while (current != null) {
				builder.append(current.value);

				if (current.next != null) {
					builder.append(", ");
				}

				current = current.next;
			}

			builder.append("]");

			return builder.toString();
		}
	}
}
