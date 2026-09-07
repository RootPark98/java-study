package io.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class IoBasicStudy {
	private static final Path DIRECTORY = Path.of("io-data");

	private static final int DATA_SIZE = 200_000;
	private static final int BUFFER_SIZE = 8_192;

	public static void main(String[] args) throws IOException {
		Files.createDirectories(DIRECTORY);

		basicStreamExample();

		byte[] data = createData();

		writeOneByOne(data);
		writeWithManualBuffer(data);
		writeWithBufferedStream(data);
		readWithBufferedStream();
		writeAllAtOnce(data);
	}

	private static void basicStreamExample() throws IOException {
		System.out.println("=== Basic Stream ===");

		Path file = DIRECTORY.resolve("basic.dat");

		try (OutputStream out = new FileOutputStream(file.toFile())) {
			out.write(65);
			out.write(66);
			out.write(67);
		}

		try (InputStream in = new FileInputStream(file.toFile())) {
			int value;

			while ((value = in.read()) != -1) {
				System.out.println("read = " + value);
			}
		}

		System.out.println();
	}

	private static byte[] createData() {
		byte[] data = new byte[DATA_SIZE];

		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) (i % 256);
		}

		return data;
	}

	private static void writeOneByOne(byte[] data) throws IOException {
		System.out.println("=== Write One By One ===");

		Path file = DIRECTORY.resolve("one-by-one.dat");

		long start = System.nanoTime();

		try (OutputStream out = new FileOutputStream(file.toFile())) {
			for (byte value : data) {
				out.write(value);
			}
		}

		printResult(start, data.length);
	}

	private static void writeWithManualBuffer(byte[] data) throws IOException {
		System.out.println("=== Manual Buffer ===");

		Path file = DIRECTORY.resolve("manual-buffer.dat");

		long start = System.nanoTime();

		try (OutputStream out = new FileOutputStream(file.toFile())) {
			byte[] buffer = new byte[BUFFER_SIZE];
			int position = 0;

			for (byte value : data) {
				buffer[position] = value;
				position++;

				if (position == buffer.length) {
					out.write(buffer, 0, position);
					position = 0;
				}
			}

			if (position > 0) {
				out.write(buffer, 0, position);
			}
		}

		printResult(start, data.length);
	}

	private static void writeWithBufferedStream(byte[] data) throws IOException {
		System.out.println("=== Buffered Output Stream ===");

		Path file = DIRECTORY.resolve("buffered.dat");

		long start = System.nanoTime();

		try (OutputStream out = new BufferedOutputStream(new FileOutputStream(file.toFile()),
				BUFFER_SIZE)) {
			for (byte value : data) {
				out.write(value);
			}
		}

		printResult(start, data.length);
	}

	private static void readWithBufferedStream() throws IOException {
		System.out.println("=== Buffered Input Stream ===");

		Path file = DIRECTORY.resolve("buffered.dat");

		long start = System.nanoTime();

		int count = 0;
		long checksum = 0;

		try (InputStream in = new BufferedInputStream(new FileInputStream(file.toFile()),
					BUFFER_SIZE)) {
			int value;

			while ((value = in.read()) != -1) {
				count++;
				checksum += value;
			}
		}

		double elapsed = (System.nanoTime() - start) / 1_000_000.0;

		System.out.println("read bytes = " + count);
		System.out.println("checksum = " + checksum);
		System.out.printf("elapsed = %.3f ms%n", elapsed);
		System.out.println();
	}

	private static void writeAllAtOnce(byte[] data) throws IOException {
		System.out.println("=== Write All At Once ===");

		Path file = DIRECTORY.resolve("all-at-once.dat");

		long start = System.nanoTime();

		try (OutputStream out = new FileOutputStream(file.toFile())) {
			out.write(data);
		}

		printResult(start, data.length);
	}

	private static void printResult(long start, int size) {
		double elapsed = (System.nanoTime() - start) / 1_000_000.0;

		System.out.println("written bytes = " + size);
		System.out.printf("elapsed = %.3f ms%n", elapsed);
		System.out.println();
	}
}
