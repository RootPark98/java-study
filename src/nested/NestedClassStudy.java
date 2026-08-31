package nested;

public class NestedClassStudy{
	public static void main(String[] args){
		System.out.println("1. Static nested class");
		staticNestedExample();

		System.out.println("\n2. Static nested class usage");
		staticNestedUsage();

		System.out.println("\n3. Inner class");
		innerClassExample();

		System.out.println("\n4. Inner class usage");
		innerClassUsage();

		System.out.println("\n5. Same variable name");
		sameNameExample();
	}

	static void staticNestedExample(){
		Network.NetworkMessage message = new Network.NetworkMessage("Hello");
		message.print();
	}

	static void staticNestedUsage(){
		Network network = new Network();
		network.send("Java");
	}

	static void innerClassExample(){
		Car car = new Car("Tesla");

		Car.Engine engine = car.new Engine();
		engine.start();
	}

	static void innerClassUsage(){
		Account account = new Account("sejin", 1000);
		account.printInfo();
	}

	static void sameNameExample(){
		Outer outer = new Outer();
		Outer.Inner inner = outer.new Inner();

		inner.printValue();
	}

	static class Network{
		private static final String TYPE = "TCP";

		static class NetworkMessage{
			private final String message;

			NetworkMessage(String message){
				this.message = message;
			}

			void print(){
				System.out.println("Type = " + TYPE);
				System.out.println("Message = " + message);
			}
		}

		void send(String message){
			NetworkMessage networkMessage = new NetworkMessage(message);

			networkMessage.print();
		}
	}

	static class Car{
		private final String model;

		Car(String model){
			this.model = model;
		}

		class Engine{
			void start(){
				System.out.println("Model = " + model);
				System.out.println("Engine started");
			}
		}
	}

	static class Account{
		private final String owner;
		private final int balance;

		Account(String owner, int balance){
			this.owner = owner;
			this.balance = balance;
		}

		void printInfo(){
			Formatter formatter = new Formatter();

			System.out.println(formatter.format());
		}

		private class Formatter{
			String format(){
				return "Owner = " + owner + ", Balance = " + balance;
			}
		}
	}

	static class Outer{
		private int value = 10;

		class Inner{
			private int value = 20;

			void printValue(){
				int value = 30;

				System.out.println("Local = " + value);
				System.out.println("Inner = " + this.value);
				System.out.println("Outer = " + Outer.this.value);
			}
		}
	}
}
