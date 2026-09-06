package io.encoding;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class EncodingStudy {
	public static void main(String[] args) {
		basicEncodingExample();
		charsetLookupExample();
		utf8Example();
		wrongDecodingExample();
	}

	private static void basicEncodingExample() {
		System.out.println("=== Basic Encoding ===");

		String text = "Hello";

		byte[] bytes = text.getBytes(StandardCharsets.UTF_8);

		System.out.println("text = " + text);
		System.out.println("character length = " + text.length());
		System.out.println("byte length = " + bytes.length);

		printBytes(bytes);

		String decoded = new String(bytes, StandardCharsets.UTF_8);

		System.out.println("decoded = " + decoded);
		System.out.println();
	}

	private static void charsetLookupExample() {
		System.out.println("=== Charset Lookup ===");

		System.out.println("default charset = " + Charset.defaultCharset());

		Map<String, Charset> charsets = Charset.availableCharsets();

		int count = 0;

		for (String name : charsets.keySet()) {
			System.out.println(name);

			count++;

			if (count >= 10) {
				break;
			}
		}

		System.out.println();
	}

	private static void utf8Example() {
		System.out.println("=== UTF-8 Example ===");

		String english = "ABC";
		String korean = "가나다";

		byte[] englishBytes = english.getBytes(StandardCharsets.UTF_8);

		byte[] koreanBytes = korean.getBytes(StandardCharsets.UTF_8);

		System.out.println("english characters = " + english.length());
		System.out.println("english bytes = " + englishBytes.length);

		System.out.println("korean characters = " + korean.length());
		System.out.println("korean bytes = " + koreanBytes.length);

		System.out.print("english byte values = ");
		printBytes(englishBytes);

		System.out.print("korean byte values = ");
		printBytes(koreanBytes);

		System.out.println();
	}

	private static void wrongDecodingExample() {
		System.out.println("=== Wrong Decoding ===");

		String text = "Hello 한글";

		byte[] utf8Bytes = text.getBytes(StandardCharsets.UTF_8);

		String correct = new String(utf8Bytes, StandardCharsets.UTF_8);

		String wrong = new String(utf8Bytes, StandardCharsets.ISO_8859_1);
		
		System.out.println("original = " + text);
		System.out.println("correct = " + correct);
		System.out.println("wrong = " + wrong);
		System.out.println();
	}

	private static void printBytes(byte[] bytes) {
		for (byte value : bytes) {
			System.out.println((value & 0xff) + " ");
		}

		System.out.println();
	}
}

