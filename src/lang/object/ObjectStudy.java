package lang.object;

import java.util.Objects;

public class ObjectStudy{
	public static void main(String[] args){
		Dog dog = new Dog("warwar");
		Car car = new Car("avante");

		Object dogObject = dog;
		Object carObject = car;

		printObject(dogObject);
		printObject(carObject);

		System.out.println("----------------------------");

		Object[] objects = {
			dog,
			car,
			"string is object",
			Integer.valueOf(100)
		};

		for(Object object : objects){
			printObject(object);
		}

		System.out.println("----------------------------");

		Member member1 = new Member("user1", "sejin");
		Member member2 = new Member("user1", "sejin");
		Member member3 = member1;

		System.out.println("member1 == member2: " + (member1 == member2));
		System.out.println("member1.equals(member2): " + member1.equals(member2));
		System.out.println("member1 == member3: " + (member1 == member3));
	}

	static void printObject(Object object){
		System.out.println(object);
	}

	static class Dog{
		private final String name;

		public Dog(String name){
			this.name = name;
		}

		@Override
		public String toString(){
			return "Dog{name='" + name + "'}";
		}
	}

	static class Car{
		private final String model;

		public Car(String model){
			this.model = model;
		}

		@Override
		public String toString(){
			return "Car{model='" + model + "'}";
		}
	}

	static class Member{
		private final String id;
		private final String name;

		public Member(String id, String name){
			this.id = id;
			this.name = name;
		}

		@Override
		public boolean equals(Object object){
			if(this == object){
				return true;
			}

			if(!(object instanceof Member member)){
				return false;
			}

			return Objects.equals(this.id, member.id);
		}

		@Override
		public int hashCode(){
			return Objects.hash(id);
		}

		@Override
		public String toString(){
			return "Member{id='" + id + "', name='" + name + "'}";
		}
	}
}
