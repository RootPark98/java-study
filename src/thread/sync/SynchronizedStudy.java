package thread.sync;

public class SynchronizedStudy {
	public static void main(String[] args) {
		unsafeExample();
		synchronizedMethodExample();
		synchronizedBlockExample();
	}

	private static void unsafeExample() {
		System.out.println("=== Unsafe Account ===");

		UnsafeAccount account = new UnsafeAccount(1000);

		Thread threadA = new Thread(
				() -> withdraw(account, 800),
				"thread-A"
				);

		Thread threadB = new Thread(
				() -> withdraw(account, 800),
				"thread-B"
				);

		threadA.start();
		threadB.start();

		join(threadA);
		join(threadB);

		System.out.println("final balance = " + account.getBalance());
		System.out.println();
	}

	private static void synchronizedMethodExample() {
		System.out.println("=== Synchronized Method ===");

		SynchronizedMethodAccount account = new SynchronizedMethodAccount(1000);

		Thread threadA = new Thread(
				() -> withdraw(account, 800),
				"thread-A"
				);

		Thread threadB = new Thread(
				() -> withdraw(account, 800),
				"thread-B"
				);

		threadA.start();
		threadB.start();

		join(threadA);
		join(threadB);

		System.out.println("final balance = " + account.getBalance());
		System.out.println();
	}

	private static void synchronizedBlockExample() {
                 System.out.println("=== Synchronized Block ===");

                 SynchronizedBlockAccount account = new SynchronizedBlockAccount(1000);

                 Thread threadA = new Thread(
                                 () -> withdraw(account, 800),
                                 "thread-A"
                                 );

                 Thread threadB = new Thread(
                                 () -> withdraw(account, 800),
                                 "thread-B"
                                 );

                 threadA.start();
                 threadB.start();

                 join(threadA);
                 join(threadB);

                 System.out.println("final balance = " + account.getBalance());
                 System.out.println();
         }

	private static void withdraw(Account account, int amount) {
		String name = Thread.currentThread().getName();

		System.out.println(name + " request = " + amount);

		boolean result = account.withdraw(amount);

		System.out.println(name + " success = " + result);
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

	interface Account {
		boolean withdraw(int amount);

		int getBalance();
	}

	static class UnsafeAccount implements Account {
		private int balance;

		UnsafeAccount(int balance) {
			this.balance = balance;
		}

		@Override
		public boolean withdraw(int amount) {
			String name = Thread.currentThread().getName();

			System.out.println(name + " checks balance = " + balance);

			if (balance < amount) {
				System.out.println(name + " insufficient balance");

				return false;
			}

			sleep(500);

			balance -= amount;

			System.out.println(name + " withdraw complete, balance = " + balance);

			return true;
		}

		@Override
		public int getBalance() {
			return balance;
		}
	}

	static class SynchronizedMethodAccount implements Account {
		private int balance;

		SynchronizedMethodAccount(int balance) {
			this.balance = balance;
		}

		@Override
		public synchronized boolean withdraw(int amount) {
			String name = Thread.currentThread().getName();

			System.out.println(name + " checks balance = " + balance);

			if (balance < amount) {
				System.out.println(name + " insufficient balance");

				return false;
			}

			sleep(500);

			balance -= amount;

			System.out.println(name + " withdraw complete, balance = " + balance);

			return true;
		}

		@Override
		public synchronized int getBalance() {
			return balance;
		}
	}

	static class SynchronizedBlockAccount implements Account {
		private final Object lock = new Object();
		private int balance;

		SynchronizedBlockAccount(int balance) {
			this.balance = balance;
		}

		@Override
		public boolean withdraw(int amount) {
			String name = Thread.currentThread().getName();

			System.out.println(name + " waits for lock");

			synchronized (lock) {
				System.out.println(name + " gets lock");

				if (balance < amount) {
					System.out.println(name + " insufficient balance");

					return false;
				}

				sleep(500);

				balance -= amount;

				System.out.println(name + " withdraw complete, balance = " + balance);

				return true;
			}
		}

		@Override
		public int getBalance() {
			synchronized (lock) {
				return balance;
			}
		}
	}
}
