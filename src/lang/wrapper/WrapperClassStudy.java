package lang.wrapper;

import java.util.ArrayList;
import java.util.Random;

public class WrapperClassStudy{
	public static void main(String[] args){
		System.out.println("1. Primitive type Limit");
		primitiveLimit();

		System.out.println("\n2. wrapper class and autoboxing");
		wrapperAndBoxing();

		System.out.println("\n3. wrapper class method");
		wrapperMethods();

		System.out.println("\n4. wrraper class performace");
		wrapperPerformance();

		System.out.println("\n5. Class class");
		classExample();

		System.out.println("\n6. System Class");
		systemExample();

		System.out.println("\n7. Math Class");
		mathExample();

		System.out.println("\n8. Random Class");
		randomExample();
	}

	static void primitiveLimit(){
		int number = 10;

		ArrayList<Integer> numbers = new ArrayList<>();

		numbers.add(10);
		numbers.add(20);

		System.out.println("Primitive number = " + number);
		System.out.println("Integer List = " + numbers);
	}

	static void wrapperAndBoxing(){
		Integer boxed1 = Integer.valueOf(10);

		Integer boxed2 = 20;

		int unboxed1 = boxed1.intValue();
		int unboxed2 = boxed2;

		System.out.println("boxed1 = " + boxed1);
		System.out.println("boxed2 = " + boxed2);
		System.out.println("unboxed1 = " + unboxed1);
		System.out.println("unboxed2 = " + unboxed2);

		Object object = 30;
		System.out.println("Objectjdp Load = " + object);
		System.out.println("Real Class = " + object.getClass().getName());

		Integer nullable = null;

		System.out.println("Integer is able Null = " + nullable);
	}

	static void wrapperMethods(){
		String text = "100";

		int primitiveValue = Integer.parseInt(text);

		Integer wrapperValue = Integer.valueOf(text);

		String numberText = Integer.toString(200);

		System.out.println("parseInt = " + primitiveValue);
		System.out.println("valueOf = " + wrapperValue);
		System.out.println("toString = " + numberText);

		System.out.println("value compare = " + Integer.compare(10, 20));
		System.out.println("Max Value = " + Integer.max(10, 20));
		System.out.println("Min Value = " + Integer.min(10, 20));

		Integer a = 1000;
		Integer b = 1000;

		System.out.println("a == b: " + (a == b));
		System.out.println("a.equals(b): " + a.equals(b));
	}

	static void wrapperPerformance(){
		int count = 1_000_000;

		long start1 = System.nanoTime();

		long primitiveSum = 0;

		for(int i = 0; i < count; i++)
			primitiveSum += i;

		long primitiveTime = System.nanoTime() - start1;

		long start2 = System.nanoTime();

		Long wrapperSum = 0L;

		for(int i = 0; i < count; i++)
			wrapperSum += i;

		long wrapperTime = System.nanoTime() - start2;

		System.out.println("primitive sum = " + primitiveSum);
		System.out.println("wrapper Sum = " + wrapperSum);

		System.out.println("primitive time(ns) = " + primitiveTime);
		System.out.println("wrapper time(ns) = " + wrapperTime);
	}

	static void classExample(){
		User user = new User("sejin");

		Class<?> class1 = user.getClass();
		Class<User> class2 = User.class;

		System.out.println("class1 = " + class1);
		System.out.println("class2 = " + class2);

		System.out.println("All ClassName = " + class1.getName());
		System.out.println("SimpleName = " + class1.getSimpleName());
		System.out.println("Parent Class = " + class1.getSuperclass());
		System.out.println("Is Interface? = " + class1.isInterface());

		System.out.println("field List");

		for(var field : class1.getDeclaredFields()){
			System.out.println("- " + field.getName());
		}

		System.out.println("Method List");

		for(var method : class1.getDeclaredMethods()){
			System.out.println("- " + method.getName());
		}
	}

	static void systemExample(){
		System.out.println("normal print");
		System.err.println("error print exam");

		System.out.println("Now Time(ms) = " + System.currentTimeMillis());

		System.out.println("nano Time(ns) = " + System.nanoTime());

		System.out.println("Java Version = " + System.getProperty("java.version"));

		System.out.println("OS = " + System.getProperty("os.name"));

		System.out.println("home folder = " + System.getProperty("user.home"));

		int[] source = {1, 2, 3};
		int[] destination = new int[3];

		System.arraycopy(
			source,
			0,
			destination,
			0,
			source.length
		);

		System.out.println("copy array = "
			+ destination[0] + ", "
			+ destination[1] + ", "
			+ destination[2]
		);
	}

	static void mathExample(){
		System.out.println("abs = " + Math.abs(-10));
		System.out.println("max = " + Math.max(10, 20));
		System.out.println("min = " + Math.min(10, 20));

		System.out.println("round = " + Math.round(3.6));
		System.out.println("ceil = " + Math.ceil(3.1));
		System.out.println("floor = " + Math.floor(3.9));

		System.out.println("2 pow 3 = " + Math.pow(2, 3));
		System.out.println("16 wqrt = " + Math.sqrt(16));

		System.out.println("PI = " + Math.PI);
	}

	static void randomExample(){
		Random random = new Random();

		int number = random.nextInt(10);
		int dice = random.nextInt(6) + 1;

		System.out.println("0 ~ 10 = " + number);
		System.out.println("dice = " + dice);

		Random random1 = new Random(100);
		Random random2 = new Random(100);

		System.out.println("same seed 1 = " + random1.nextInt());
		System.out.println("same seed 2 = " + random2.nextInt());
	}

	static class User{
		private String name;

		public User(String name){
			this.name = name;
		}

		public void hello(){
			System.out.println("Hello. " + name);
		}
	}
}
