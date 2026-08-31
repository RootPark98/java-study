package exception.basic;

public class ExceptionStudy{
	public static void main(String[] args){
		System.out.println("1. Return value");
		returnValueExample();

		System.out.println("\n2. Checked exception");
		checkedExceptionExample();

		System.out.println("\n3. Unchecked exception");
		uncheckedExceptionExample();

		System.out.println("\n4. Exception propagation");
		propagationExample();
	}

	static void returnValueExample(){
		String result1 = connectWithReturn("server");
		String result2 = connectWithReturn("error");

		System.out.println("Result 1 = " + result1);
		System.out.println("Result 2 = " + result2);

		if("ERROR".equals(result2)){
			System.out.println("Connection failed");
		}
	}

	static String connectWithReturn(String address){
		if("error".equals(address)){
			return "ERROR";
		}

		return "SUCCESS";
	}

	static void checkedExceptionExample(){
		try{
			connectChecked("error");
			System.out.println("Connection success");
		} catch(NetworkCheckedException exception){
			System.out.println("Checked exception = " + exception.getMessage());
		}
	}

	static void connectChecked(String address) throws NetworkCheckedException{
		if("error".equals(address)){
			throw new NetworkCheckedException(
				"Cannot connect to " + address
			);
		}

		System.out.println("Connected to " + address);
	}

	static void uncheckedExceptionExample(){
		try{
			connectUnchecked("error");
			System.out.println("Connection success");
		} catch(NetworkRuntimeException exception){
			System.out.println("Unchecked exception = " + exception.getMessage());
		}
	}

	static void connectUnchecked(String address){
		if("error".equals(address)){
			throw new NetworkRuntimeException(
				"Cannot connet to " + address
			);
		}

		System.out.println("Connected to " + address);
	}

	static void propagationExample(){
		try{
			level1();
		} catch(NetworkCheckedException exception){
			System.out.println("Caught in propagationExample = " + exception.getMessage());
		}
	}

	static void level1() throws NetworkCheckedException{
		level2();
	}

	static void level2() throws NetworkCheckedException{
		level3();
	}

	static void level3() throws NetworkCheckedException{
		throw new NetworkCheckedException(
			"Error from level3"
		);
	}

	static class NetworkCheckedException extends Exception{
		NetworkCheckedException(String message){
			super(message);
		}
	}

	static class NetworkRuntimeException extends RuntimeException{
		NetworkRuntimeException(String message){
			super(message);
		}
	}
}
