package thread.lifecycle;

public class ThreadLifecycleStudy {
    public static void main(String[] args) {
        printCurrentThreadInfo();

        lifecycleExample();
        joinProblemExample();
        sleepExample();
        joinExample();
        timedJoinExample();
    }

    private static void printCurrentThreadInfo() {
        Thread current = Thread.currentThread();

        System.out.println("=== Thread Info ===");
        System.out.println("name = " + current.getName());
        System.out.println("id = " + current.threadId());
        System.out.println("priority = " + current.getPriority());
        System.out.println("state = " + current.getState());
        System.out.println("alive = " + current.isAlive());
        System.out.println("daemon = " + current.isDaemon());
        System.out.println();
    }

    private static void lifecycleExample() {
        System.out.println("=== Lifecycle ===");

        Thread worker = new Thread(() -> {
            System.out.println("worker start");

            sleep(500);

            System.out.println("worker end");
        }, "worker");

        System.out.println("before start = " + worker.getState());

        worker.start();

        System.out.println("after start = " + worker.getState());

        sleep(100);

        System.out.println("during sleep = " + worker.getState());

        join(worker);

        System.out.println("after finish = " + worker.getState());
        System.out.println();
    }

    private static void joinProblemExample() {
        System.out.println("=== Join Problem ===");

        CounterTask task1 = new CounterTask();
        CounterTask task2 = new CounterTask();

        Thread thread1 = new Thread(task1, "thread-1");
        Thread thread2 = new Thread(task2, "thread-2");

        thread1.start();
        thread2.start();

        System.out.println("result = " + (task1.getResult() + task2.getResult()));

        join(thread1);
        join(thread2);

        System.out.println("real result = " + (task1.getResult() + task2.getResult()));
        System.out.println();
    }

    private static void sleepExample() {
        System.out.println("=== Sleep Waiting ===");

        CounterTask task1 = new CounterTask();
        CounterTask task2 = new CounterTask();

        Thread thread1 = new Thread(task1, "thread-1");
        Thread thread2 = new Thread(task2, "thread-2");

        thread1.start();
        thread2.start();

        sleep(700);

        System.out.println("result = " + (task1.getResult() + task2.getResult()));
        System.out.println();
    }

    private static void joinExample() {
        System.out.println("=== Join ===");

        CounterTask task1 = new CounterTask();
        CounterTask task2 = new CounterTask();

        Thread thread1 = new Thread(task1, "thread-1");
        Thread thread2 = new Thread(task2, "thread-2");

        thread1.start();
        thread2.start();

        join(thread1);
        join(thread2);

        System.out.println("result = " + (task1.getResult() + task2.getResult()));
        System.out.println();
    }

    private static void timedJoinExample() {
        System.out.println("=== Timed Join ===");


        Thread worker = new Thread(() -> {
            System.out.println("long worker start");

            sleep(1000);

            System.out.println("long worker end");
        }, "long-worker");

        worker.start();

        join(worker, 300);

        System.out.println("worker state = " + worker.getState());

        System.out.println("main continues");

        join(worker);

        System.out.println("worker state = " + worker.getState());
        System.out.println();
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

    private static void join(Thread thread, long millis) {
        try {
            thread.join(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    static class CounterTask implements Runnable {
        private int result;

        @Override
        public void run() {
            sleep(500);

            result = 100;
        }

        public int getResult() {
            return result;
        }
    }
}
