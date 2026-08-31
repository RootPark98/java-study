package lang.immutable;

public class ImmutableStudy{
	public static void main(String[] args){
		System.out.println("1. normal copy");
		primitiveExample();

		System.out.println("\n2. side effect of mmutable object");
		mutableExample();

		System.out.println("\n3. immutable object");
		immutableExample();

		System.out.println("\n4. change value of immutable object");
		immutableChangeExample();
	}

	static void primitiveExample(){
		int a = 10;
		int b = a;

		b = 20;

		System.out.println("a = " + a);
		System.out.println("b = " + b);
	}

	static void mutableExample(){
		MutableAddress address1 = new MutableAddress("seoul");
		MutableAddress address2 = address1;

		System.out.println("before");
		System.out.println("address1 = " + address1);
		System.out.println("address2 = " + address2);

		address2.setValue("busan");

		System.out.println("after");
		System.out.println("address1 = " + address1);
		System.out.println("address2 = " + address2);
	}

	static void immutableExample(){
		ImmutableAddress address1 = new ImmutableAddress("seoul");
		ImmutableAddress address2 = address1;

		System.out.println("address1 = " + address1);
		System.out.println("address2 = " + address2);
	}

	static void immutableChangeExample(){
		ImmutableAddress address1 = new ImmutableAddress("seoul");

		ImmutableAddress address2 = address1.changeValue("busan");

		System.out.println("prev object = " + address1);
		System.out.println("new object = " + address2);

		System.out.println("Is same object? " + (address1 == address2));
	}

	static class MutableAddress{
		private String value;

		public MutableAddress(String value){
			this.value = value;
		}

		public void setValue(String value){
			this.value = value;
		}

		@Override
		public String toString(){
			return "MutableAddress{value='" + value + "'}";
		}
	}

	static final class ImmutableAddress{
		private final String value;

		public ImmutableAddress(String value){
			this.value = value;
		}

		public ImmutableAddress changeValue(String newValue){
			return new ImmutableAddress(newValue);
		}

		@Override
		public String toString(){
			return "ImmutableAddress{value='" + value + "'}";
		}
	}
}
