package generic.advanced;

public class GenericAdvancedStudy {
	public static void main(String[] args) {
		System.out.println("1. Polymorphism");
		polymorphismExample();

		System.out.println("\n2. Generic polymorphism problem");
		genericPolymorphismProblem();

		System.out.println("\n3. Type parameter bound");
		typeParameterBound();

		System.out.println("\n4. Generic method");
		genericMethodExample();

		System.out.println("\n5. Wildcard");
		wildcardExample();

		System.out.println("\n6. Extends wildcard");
		extendsWildcardExample();

		System.out.println("\n7. Super wildcard");
		superWildcardExample();

		System.out.println("\n8. Type erasure");
		typeErasureExample();
	}

	static void polymorphismExample() {
		Animal animal = new Dog("Coco", 10);

		animal.sound();

		printAnimalNormal(new Dog("Coco", 10));
		printAnimalNormal(new Cat("Nabi", 5));
	}

	static void printAnimalNormal(Animal animal) {
		System.out.println(
			animal.getName() + ", size = " + animal.getSize()
		);
	}

	static void genericPolymorphismProblem() {
		Box<Dog> dogBox = new Box<>();
		dogBox.set(new Dog("Coco", 10));

		Dog dog = dogBox.get();
		dog.sound();
	}

	static void typeParameterBound() {
		AnimalBox<Dog> dogBox = new AnimalBox<>();
		dogBox.set(new Dog("Coco", 10));
		dogBox.printInfo();

		AnimalBox<Cat> catBox = new AnimalBox<>();
		catBox.set(new Cat("Nabi", 5));
		catBox.printInfo();
	}

	static void genericMethodExample() {
		Dog dog = new Dog("Coco", 10);
		Cat cat = new Cat("Nabi", 5);

		printAnimalGeneric(dog);
		printAnimalGeneric(cat);

		Dog result = bigger(dog, new Dog("Max", 20));

		System.out.println(
			"Bigger animal = " + result.getName()
		);

		GenericAdvancedStudy.<Dog>printAnimalGeneric(dog);
	}

	static <T extends Animal> void printAnimalGeneric(T animal) {
		System.out.println(
			animal.getName() + " says "
		);

		animal.sound();
	}

	static <T extends Animal> T bigger(T first, T second) {
		if(first.getSize() > second.getSize()) {
			return first;
		}

		return second;
	}

	static void wildcardExample() {
		Box<String> stringBox = new Box<>();
		stringBox.set("Java");

		Box<Integer> integerBox = new Box<>();
		integerBox.set(100);

		printAnyBox(stringBox);
		printAnyBox(integerBox);
	}

	static void printAnyBox(Box<?> box) {
		Object value = box.get();

		System.out.println("Value = " + value);
	}

	static void extendsWildcardExample() {
		Box<Dog> dogBox = new Box<>();
		dogBox.set(new Dog("Coco", 10));

		Box<Cat> catBox = new Box<>();
		catBox.set(new Cat("Nabi", 5));

		printAnimalBox(dogBox);
		printAnimalBox(catBox);
	}

	static void printAnimalBox(Box<? extends Animal> box) {
		Animal animal = box.get();

		System.out.println(
			"Animal = " + animal.getName()
		);

		animal.sound();
	}

	static void superWildcardExample() {
		Box<Animal> animalBox = new Box<>();

		addDog(animalBox);

		Animal animal = animalBox.get();

		System.out.println(
			"Added dog = " + animal.getName()
		);

		Box<Dog> dogBox = new Box<>();

		addDog(dogBox);

		Dog dog = dogBox.get();
		dog.sound();
	}

	static void addDog(Box<? super Dog> box) {
		box.set(new Dog("Buddy", 15));

		Object value = box.get();

		System.out.println(
			"Runtime type = "
			+
			value.getClass().getSimpleName()
		);
	}

	static void typeErasureExample() {
		Box<String> stringBox = new Box<>();
		Box<Integer> integerBox = new Box<>();

		stringBox.set("Java");
		integerBox.set(100);

		System.out.println(
			"String box class = "
			+
			stringBox.getClass().getName()
		);

		System.out.println(
			"Integer box class = "
			+
			integerBox.getClass().getName()
		);

		System.out.println(
			"Same runtime class = "
			+
			stringBox.getClass().equals(integerBox.getClass())
		);

		if(stringBox instanceof Box<?>) {
			System.out.println("Box<?> check = true");
		}
	}

	static class Box<T> {
		private T value;

		void set(T value) {
			this.value = value;
		}

		T get() {
			return value;
		}
	}

	static class AnimalBox<T extends Animal> {
		private T value;

		void set(T value) {
			this.value = value;
		}

		T get() {
			return value;
		}

		void printInfo() {
			System.out.println(
				"Name = " + value.getName()
					+ ", size = " + value.getSize()
			);

			value.sound();
		}
	}

	static class Animal {
		private final String name;
		private final int size;

		Animal(String name, int size) {
			this.name = name;
			this.size = size;
		}

		String getName() {
			return name;
		}

		int getSize() {
			return size;
		}

		void sound() {
			System.out.println("Animal sound");
		}
	}

	static class Dog extends Animal {
		Dog(String name, int size) {
			super(name, size);
		}

		@Override
		void sound() {
			System.out.println("Woof");
		}
	}

	static class Cat extends Animal {
		Cat(String name, int size) {
			super(name, size);
		}

		@Override
		void sound() {
			System.out.println("Meow");
		}
	}
}
