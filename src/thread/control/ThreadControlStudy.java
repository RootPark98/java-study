package thread.control;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ThreadControlStudy {
    public static void main(String[] args) {
        interruptFlagExample();
        interruptSleepExample();
        interruptedMethodExample();

        printerV1Example();
        printerV2Example();
        printerV3Example();

        yieldExample();
        printerV4Example();
    }

    private static void interruptFlagExample() {
        System.out.println("=== Interrupt Flag ===");

        Thread worker = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.yield();
            }

            System.out.println("worker interrupted = " + Thread.currentThread().isInterrupted());
            System.out.println("worker end");
        }, "worker");

        worker.start();

        sleepMain(100);

        worker.interrupt();

        join(worker);

        System.out.println();
    }

    private static void interruptSleepExample() {
        System.out.println("=== Interrupt Sleep===");

        Thread worker = new Thread(() -> {
            try {
                System.out.println("worker sleep");

                Thread.sleep(3000);

                System.out.println("worker wake");
            } catch (InterruptedException e) {
                System.out.println("interrupt exception");
                System.out.println("interrupted = " + Thread.currentThread().isInterrupted());
            }

            System.out.println("worker end");
        }, "worker");

        worker.start();

        sleepMain(100);

        worker.interrupt();

        join(worker);

        System.out.println();
    }

    private static void interruptedMethodExample() {
        System.out.println("=== Thread.interrupted===");

        Thread worker = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.yield();
            }

            System.out.println("before = " + Thread.currentThread().isInterrupted());

            boolean interrupted = Thread.interrupted();

            System.out.println("returned = " + interrupted);
            System.out.println("after = " + Thread.currentThread().isInterrupted());
        }, "worker");

        worker.start();

        sleepMain(100);

        worker.interrupt();

        join(worker);

        System.out.println();
    }

    private static void printerV1Example() {
        System.out.println("=== Printer V1 ===");

        PrinterV1 printer = new PrinterV1();

        printer.start();
        printer.addJob("document-1");

        sleepMain(200);

        printer.stop();

        join(printer.getWorker());

        System.out.println();
    }

    private static void printerV2Example() {
        System.out.println("=== Printer V2 ===");

        PrinterV2 printer = new PrinterV2();

        printer.start();
        printer.addJob("document-1");

        sleepMain(200);

        printer.stop();

        join(printer.getWorker());

        System.out.println();
    }

    private static void printerV3Example() {
        System.out.println("=== Printer V3 ===");

        PrinterV3 printer = new PrinterV3();

        printer.start();
        printer.addJob("document-1");
        printer.addJob("document-2");

        sleepMain(200);

        printer.stop();

        join(printer.getWorker());

        System.out.println();
    }

    private static void yieldExample() {
        System.out.println("=== Yield ===");

        Thread threadA = new Thread(() -> runYieldTask("A"), "thread-A");
        Thread threadB = new Thread(() -> runYieldTask("B"), "thread-B");

        threadA.start();
        threadB.start();

        join(threadA);
        join(threadB);

        System.out.println();
    }

    private static void runYieldTask(String name) {
        for (int i = 1; i <= 5; i++) {
            System.out.println(name = " = " + i);

            Thread.yield();
        }
    }

    private static void printerV4Example() {
        System.out.println("=== Printer V4 ===");

        PrinterV4 printer = new PrinterV4();

        printer.start();

        printer.addJob("document-1");
        printer.addJob("document-2");
        printer.addJob("document-3");

        sleepMain(200);

        printer.stop();

        join(printer.getWorker());

        System.out.println();
    }

    private static void sleepMain(long millis) {
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

    static class PrinterV1 {
        private final Queue<String> queue = new ConcurrentLinkedQueue<>();
        private final Thread worker;
        private volatile boolean running = true;

        PrinterV1() {
            worker = new Thread(this::run, "printer-v1");
        }

        public void start() {
            worker.start();
        }

        public void addJob(String job) {
            queue.offer(job);
        }

        public void stop() {
            running = false;
        }

        public Thread getWorker() {
            return worker;
        }

        private void run() {
            while (running) {
                String job = queue.poll();

                if (job != null) {
                    System.out.println("print = " + job);

                    continue;
                }

                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            System.out.println("printer-v1 end");
        }
    }

    static class PrinterV2 {
        private final Queue<String> queue = new ConcurrentLinkedQueue<>();
        private final Thread worker;

        private volatile boolean running = true;

        PrinterV2(){
            worker = new Thread(this::run, "printer-v2");
        }

        public void start(){
            worker.start();
        }

        public void addJob(String job) {
            queue.offer(job);
        }

        public void stop() {
            running = false;
            worker.interrupt();
        }

        public Thread getWorker() {
            return worker;
        }

        private void run() {
            while (running) {
                String job = queue.poll();

                if (job != null) {
                    System.out.println("print = " + job);

                    continue;
                }

                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    System.out.println("printer-v2 interrupted");
                }
            }

            System.out.println("printer-v2 end");
        }
    }

    static class PrinterV3 {
        private final Queue<String> queue = new ConcurrentLinkedQueue<>();
        private final Thread worker;

        PrinterV3() {
            worker = new Thread(this::run, "printer-v3");
        }

        public void start() {
            worker.start();
        }

        public void addJob(String job) {
            queue.offer(job);
        }

        public void stop() {
            worker.interrupt();
        }

        public Thread getWorker() {
            return worker;
        }

        private void run() {
            while (!Thread.currentThread().isInterrupted()) {
                String job = queue.poll();

                if (job != null) {
                    System.out.println("print = " + job);

                    continue;
                }

                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    break;
                }
            }

            System.out.println("printer-v3 end");
        }
    }

    static class PrinterV4 {
        private final Queue<String> queue = new ConcurrentLinkedQueue<>();
        private final Thread worker;

        PrinterV4() {
            worker = new Thread(this::run, "printer-v4");
        }

        public void start() {
            worker.start();
        }

        public void addJob(String job) {
            queue.offer(job);
        }

        public void stop() {
            worker.interrupt();
        }

        public Thread getWorker() {
            return worker;
        }

        private void run() {
            while (!Thread.currentThread().isInterrupted()) {
                String job = queue.poll();

                if (job != null) {
                    System.out.println("print = " + job);

                    continue;
                }

                Thread.yield();
            }

            System.out.println("printer-v4 end");
        }
    }
}
