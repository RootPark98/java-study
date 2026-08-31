package thread.start;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ThreadStartStudy {
	private static final DateTimeFormatter FORMATTER =
						DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

	public static void main(String[] args) {
        log("Program start");

        System.out.println("\n1. Thread start");
        threadStartExample();

        sleep(500);

        System.out.println("\n2. start() vs run()");
        startVsRunExample();

        sleep(500);

        System.out.println("\n3. Daemon thread");
        daemonExample();

        sleep(500);

        System.out.println("\n4. Runnable");
        runnableExample();

        sleep(500);

        System.out.println("\n5. Multiple threads");
        multipleThreadsExample();

        sleep(1000);

        System.out.println("\n6. Various Runnable");
        variousRunnableExample();

        sleep(1000);

        log("Main thread end");
	}

	static void threadStartExample() {
		MyThread thread = new MyThread();

		thread.setName("worker-A");

		log("Before start");

		thread.start();

		log("After start");
	}

	static void startVsRunExample() {
		MyThread startThread = new MyThread();
		startThread.setName("start-thread");

		log("Call start()");
		startThread.start();

		sleep(200);

		MyThread runThread = new MyThread();
		runThread.setName("run-thread");

		log("Call run()");
		runThread.run();
	}
    
    static void daemonExample() {
        Thread daemonThread = new Thread(
                new DaemonTask(),
                "daemon-worker"
                );

        daemonThread.setDaemon(true);

        daemonThread.start();

        log(
                "Daemon = "
                + daemonThread.isDaemon()
        );
    }

    static void runnableExample() {
        Runnable task = new CounterTask("runnable-task");

        Thread thread = new Thread(task, "runnable-worker");

        thread.start();
    }

    static void multipleThreadsExample() {
        Thread threadA = new Thread(new CounterTask("Task-A"), "worker-A");

        Thread threadB = new Thread(new CounterTask("Task-B"), "worker-B");
        
        Thread threadC = new Thread(new CounterTask("Task-C"), "worker-C");

        threadA.start();
        threadB.start();
        threadC.start();
    }

    static void variousRunnableExample() {
        Runnable classTask = new CounterTask("Class");

        Thread classThread = new Thread(classTask, "class-worker");

        classThread.start();

        Runnable anonymousTask = new Runnable() {
            @Override
            public void run() {
                log(
                        "Anonymous Runnable"
                        );
            }
        };

        Thread anonymousThread = new Thread(anonymousTask, "anonymous-worker");

        anonymousThread.start();

        Runnable lambdaTask = () -> log("Lambda Runnable");

        Thread lambdaThread = new Thread(lambdaTask, "lambda-worker");

        lambdaThread.start();

        Thread directLambdaThread = new Thread(
                    () -> log("Direct lambda"), "direct-lambda-worker"
                );

        directLambdaThread.start();

        Thread methodReferenceThread = new Thread(
                ThreadStartStudy::methodReferenceTask, "method-reference-worker"
                );

        methodReferenceThread.start();

    }

	static void methodReferenceTask() {
		log("Method reference");
	}

	static void log(String message) {
		String time = LocalTime.now().format(FORMATTER);

		String threadName = Thread.currentThread().getName();

		System.out.println(
			time
				+ " ["
				+ threadName
				+ "] "
				+ message
		);
	}

    static void sleep(long milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();

			throw new RuntimeException(e);
		}
	}

	static class MyThread extends Thread {
		@Override
		public void run() {
			log("MyThread run()");
		}
	}

	static class CounterTask implements Runnable {
		private final String taskName;

		CounterTask(String taskName) {
			this.taskName = taskName;
		}

		@Override
		public void run() {
			for (int i = 1; i <= 3; i++) {
				log(
					taskName
					+ " count = "
					+ i
				);

				sleep(100);
			}
		}
	}

	static class DaemonTask implements Runnable {
		@Override
		public void run() {
			for (int i = 1; i <= 100; i++) {
				log(
					"Daemon count = " + i
				);

				sleep(100);
			}

			log("Daemon completed");
		}
	}
}
