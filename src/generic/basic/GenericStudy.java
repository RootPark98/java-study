package generic.basic;

public class GenericStudy {
	public static void main(String[] args) {
		System.out.println("1. Duplicate classes");
		duplicateExample();

		System.out.println("\n2. Object and polymorphism");
		objectExample();

		System.out.println("\n3. Generic");
		genericExample();

		System.out.println("\n4. Generic type safety");
		typeSafetyExample();

		System.out.println("\n5. Generic usage");
		genericUsageExample();
	}

	static void duplicateExample() {
		StringBox stringBox = new StringBox();
		stringBox.set("Java");

		IntegerBox integerBox = new IntegerBox();
		integerBox.set(100);

		System.out.println("String = " + stringBox.get());
		System.out.println("Integer = " + integerBox.get());
	}

	static void objectExample() {
		ObjectBox box = new ObjectBox();

		box.set("Java");

		String value = (String) box.get();

		System.out.println("Value = " + value);

		box.set(100);

		try {
			String wrong = (String) box.get();
			System.out.println(wrong);
		} catch (ClassCastException exception) {
			System.out.println("ClassCastException");
		}
	}

	static void genericExample() {
		GenericBox<String> stringBox = new GenericBox<>();
		stringBox.set("Spring");
		String text = stringBox.get();

		GenericBox<Integer> integerBox = new GenericBox<>();
		integerBox.set(200);
		int number = integerBox.get();

		System.out.println("Text = " + text);
		System.out.println("Number = " + number);
	}

	static void typeSafetyExample() {
		GenericBox<Dog> dogBox = new GenericBox<>();

		dogBox.set(new Dog("Coco"));

		Dog dog = dogBox.get();

		dog.sound();
	}

	static void genericUsageExample() {
		Pair<String, Integer> user = new Pair<>("sejin", 28);
		Pair<String, String> language = new Pair<>("backend", "java");

		System.out.println(
			"User = "
				+ user.getFirst()
				+ ", "
				+ user.getSecond()
		);

		System.out.println(
			"Language = "
				+ language.getFirst()
				+ ", "
				+ language.getSecond()
		);

		GenericBox<String> result = createBox("Generic method");

		System.out.println("Result = " + result.get());
	}

	static class StringBox {
		private String value;

		void set(String value) {
			this.value = value;
		}

		String get() {
			return value;
		}
	}

	static class IntegerBox {
		private Integer value;

		void set(Integer value) {
			this.value = value;
		}

		Integer get() {
			return value;
		}
	}

	static class ObjectBox {
		private Object value;

		void set(Object value) {
			this.value = value;
		}

		Object get() {
			return value;
		}
	}

	static class GenericBox<T> {
		private T value;

		void set(T value) {
			this.value = value;
		}

		T get() {
			return value;
		}
	}

	static class Pair<K, V> {
		private final K first;
		private final V second;

		Pair(K first, V second) {
			this.first = first;
			this.second = second;
		}

		K getFirst() {
			return first;
		}

		V getSecond() {
			return second;
		}
	}

	static <T> GenericBox<T> createBox(T value) {
		GenericBox<T> box = new GenericBox<>();
		box.set(value);

		return box;
	}

	static class Dog {
		private final String name;

		Dog(String name) {
			this.name = name;
		}

		void sound() {
			System.out.println(name + " says woof");
		}
	}

	static class Cat {
		private final String name;

		Cat(String name) {
			this.name = name;
		}
	}
}
