package thread.visibility;

public class MemoryVisibilityStudy {
        public static void main(String[] args) {
            volatileExample();
            nonVolatileExample();
            volatileIsNotAtomicExample();
        }

        private static void volatileExample() {
            System.out.println("=== Volatile Visibility ===");

            VolatileTask task = new VolatileTask();
            Thread worker = new Thread(task, "volatile-worker");

            worker.start();

            sleep(100);

            System.out.println("main changes running to false");
            task.stop();

            join(worker);

            System.out.println("volatile worker finished");
            System.out.println();
        }

        private static void nonVolatileExample() {
            System.out.println("=== Non-Volatile Visibility ===");

            NonVolatileTask task = new NonVolatileTask();
            Thread worker = new Thread(task, "non-volatile-worker");

            worker.setDaemon(true);
            worker.start();

            sleep(100);

            System.out.println("main changes running to false");
            task.stop();

            sleep(300);

            System.out.println("worker alive = " + worker.isAlive());
            System.out.println("result may vary depending on JVM and optimization");
            System.out.println();
        }

        private static void volatileIsNotAtomicExample() {
            System.out.println("=== Volatile Is Not Atomic ===");

            Counter counter = new Counter();

            Thread threadA = new Thread(() -> increment(counter), "thread-A");
            Thread threadB = new Thread(() -> increment(counter), "thread-B");

            threadA.start();
            threadB.start();

            join(threadA);
            join(threadB);

            System.out.println("expected = 200000");
            System.out.println("actual = " + counter.value);
            System.out.println();
        }

        private static void increment(Counter counter) {
            for (int i = 0; i < 100000; i++) {
                counter.value++;
            }
        }

        private static void sleep(long millis) {
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        private static void join(Thread thread) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        static class VolatileTask implements Runnable {
            private volatile boolean running = true;

            public void stop() {
                running = false;
            }

            public void run() {
                System.out.println("volatile worker start");

                while (running) {
                }

                System.out.println("volatile worker end");
            }
        }

        static class NonVolatileTask implements Runnable {
            private boolean running = true;

            public void stop() {
                running = false;
            }

            public void run() {
                System.out.println("non-volatile worker start");

                while (running) {
                }

                System.out.println("non-volatile worker end");
            }
        }

        static class Counter {
            volatile int value;
        }
}
