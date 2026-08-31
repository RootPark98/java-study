package exception.practice;

public class ExceptionPractice {
	public static void main(String[] args) {

	}

	static void normalFlow() {
		NetworkClient client = new NetworkClient(false, false);
		try {
			client.connect();
			client.send("Hello");
		} catch (NetworkException exception) {
			System.out.println("Failed = " + exception.getMessage());
		} finally {
			client.disconnect();
		}
	}

	static void recoveryExample() {
		NetworkClient client = new NetworkClient(false, true);

		try {
			client.connect();
			client.send("Improtant data");
		} catch (SendException exception) {
			System.out.println("Send failed = " + exception.getMessage());
			System.out.println("Use local backup");
		} catch (NetworkException exception) {
			System.out.println("Network failed = " + exception.getMessage());
		} finally {
			client.disconnect();
		}
	}

	static void separatedFlow() {
		NetworkClient client = new NetworkClient(false, false);

		try {
			client.connect();
			client.send("Data");

			System.out.println("Business logic finished");
		} catch (ConnectException exception) {
			System.out.println("Handle connection error");
		} catch (SendException exception) {
			System.out.println("Handle send error");
		} finally {
			client.disconnect();
		}
	}

	static void resourceProblem() {
		NetworkClient client = new NetworkClient(false, true);

		try {
			client.connect();
			client.send("Data");

			client.disconnect();
		} catch (NetworkException exception) {
			System.out.println("Failed = " + exception.getMessage());
		}

		System.out.println("Resource still open = " + client.isOpen());

		client.disconnect();
	}

	static void finallyExample() {
		NetworkClient client = new NetworkClient(false, true);

		try {
			client.connect();
			client.send("Data");
		} catch (NetworkException exception) {
			System.out.println("Failed = " + exception.getMessage());
		} finally {
			client.disconnect();
		}

		System.out.println("Resource still open = " + client.isOpen());
	}

	static void exceptionHierarchy() {
		runHierarchy(new NetworkClient(true, false));
		runHierarchy(new NetworkClient(false, true));
	}

	static void runHierarchy(NetworkClient client) {
		try {
			client.connect();
			client.send("Data");
		} catch (ConnectException exception) {
			System.out.println("ConnectException = " + exception.getMessage());
		} catch (SendException exception) {
			System.out.println("SendException = " + exception.getMessage());
		} catch (NetworkException exception) {
			System.out.println("NetworkException = " + exception.getMessage());
		} finally {
			client.disconnect();
		}
	}

	static void pracitcalExceptionStrrategy() {
		NetworkRepository repository = new NetworkRepository();

		OrderService service = new OrderService(repository);

		try {
			service.process();
		} catch (AppException exception) {
			System.out.println("AppException = " + exception.getMessage());
			System.out.println("Cause = " + exception.getCause().getClass().getSimpleName());
		}
	}

	static void tryWithResourcesExample() {
		NetworkClient client = new NetworkClient(false, true);

		try (client) {
			client.connect();
			client.send("Data");
		} catch (NetworkException exception) {
			System.out.println("Failed = " + exception.getMessage());
		}

		System.out.println("Resource still open = " + client.isOpen());
	}

	static class NetworkClient implements AutoCloseable {
		private final boolean failConnect;
		private final boolean failSend;

		private boolean open;

		NetworkClient(
			boolean failConnect,
			boolean failSend
		) {
			this.failConnect = failConnect;
			this.failSend = failSend;
		}

		void connect() throws ConnectException {
			if (failConnect) {
				throw new ConnectException(
					"Connection failed"
				);
			}

			open = true;

			System.out.println("Connected");
		}

		void send(String data) throws SendException {
			if (!open) {
				throw new SendException(
					"Client is not connected"
				);
			}

			if(failSend) {
				throw new SendException(
					"Send failed"
				);
			}

			System.out.println("Sent = " + data);
		}

		void disconnect() {
			if (!open) {
				return;
			}

			open = false;

			System.out.println("Disconnected");
		}

		boolean isOpen() {
			return open;
		}

		@Override
		public void close() {
			disconnect();
		}
	}

	static class NetworkException extends Exception {
		NetworkException(String message) {
			super(message);
		}
	}

	static class ConnectException extends NetworkException {
		ConnectException(String message) {
			super(message);
		}
	}

	static class SendException extends NetworkException {
		SendException(String message) {
			super(message);
		}
	}

	static class NetworkRepository {
		void save() {
			try (NetworkClient client = new NetworkClient(false, true)) {
				client.connect();
				client.send("Order");
			} catch (NetworkException exception) {
				throw new AppException(
					"Repository operation failed",
					exception
				);
			}
		}
	}

	static class OrderService {
		private final NetworkRepository repository;

		OrderService(NetworkRepository repository) {
			this.repository = repository;
		}

		void process() {
			repository.save();
		}
	}

	static class AppException extends RuntimeException {
		AppException(
			String message,
			Throwable cause
		) {
			super(message, cause);
		}
	}
}
