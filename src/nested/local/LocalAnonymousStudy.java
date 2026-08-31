package nested.local;

public class LocalAnonymousStudy{
	public static void main(String[] args){
		System.out.println("1. Local class");
		localClassStart();

		System.out.println("\n2. Variable capture");
		Processor processor = createCapturedProcessor(100);
		processor.run();

		System.out.println("\n3. Mutable object capture");
		mutableObjectCapture();

		System.out.println("\n4. Anonymous class");
		anonymousClassStart();

		System.out.println("\n5. Anonymous class usage");
		anonymousClassUsage();
	}

	static void localClassStart(){
		int localValue = 10;

		class LocalPrinter{
			void print(){
				System.out.println("Local value = " + localValue);
			}
		}

		LocalPrinter printer = new LocalPrinter();
		printer.print();
	}

	static Processor createCapturedProcessor(int parameter){
		int localValue = 20;

		class LocalProcessor implements Processor{
			@Override
			public void run(){
				System.out.println("Parameter = " + parameter);
				System.out.println("Local value = " + localValue);
			}
		}

		Processor processor = new LocalProcessor();

		return processor;
	}

	static void mutableObjectCapture(){
		int[] counter = {0};

		Processor processor = new Processor(){
			@Override
			public void run(){
				counter[0]++;
				System.out.println("Counter = " + counter[0]);
			}
		};

		processor.run();
		processor.run();
	}

	static void anonymousClassStart(){
		Processor processor = new Processor(){
			@Override
			public void run(){
				System.out.println("Anonymous processor");
			}
		};

		processor.run();
	}

	static void anonymousClassUsage(){
		int number = 50;

		runProcess(new Processor() {
			@Override
			public void run(){
				System.out.println("First process");
				System.out.println("Captured number = " + number);
			}
		});

		runProcess(new Processor() {
			@Override
			public void run(){
				System.out.println("Second process");
			}
		});
	}

	static void runProcess(Processor processor){
		System.out.println("Process start");
		processor.run();
		System.out.println("Process end");
	}

	interface Processor{
		void run();
	}
}
