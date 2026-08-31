package collection.mapqueue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

public class MapQueueStudy {
	public static void main(String[] args) {
		System.out.println("1. Map");
		mapExample();

		System.out.println("\n2. Map methods");
		mapMethodExample();

		System.out.println("\n3. Map implementations");
		mapImplementationExample();

		System.out.println("\n4. Stack");
		stackExample();

		System.out.println("\n5. Queue");
		queueExample();

		System.out.println("\n6. Deque");
		dequeExample();

		System.out.println("\n7. Deque as Stack");
		dequeStackExample();

		System.out.println("\n8. Deque as Queue");
		dequeQueueExample();
	}

	static void mapExample() {
		Map<String, Integer> map = new HashMap<>();

		map.put("Java", 100);
		map.put("Spring", 200);
		map.put("Database", 300);

		System.out.println(
			"Java = " + map.get("Java")
		);

		System.out.println(
			"Spring = " + map.get("Spring")
		);

		map.put("Java", 999);

		System.out.println(
			"Changed Java = " + map.get("Java")
		);

		System.out.println(
			"Size = " + map.size()
		);
	}

	static void mapMethodExample() {
		Map<String, Integer> scores = new HashMap<>();

		scores.put("Tom", 90);
		scores.put("Bob", 80);
		scores.put("Alice", 100);

		System.out.println(
			"Contains Tom = "
				+ scores.containsKey("Tom")
		);

		System.out.println(
			"Contains score 80 = "
				+scores.containsValue(80)
		);

		System.out.println(
			"Unknown score = "
				+ scores.get("Jack")
		);

		System.out.println(
			"Default score = "
				+ scores.getOrDefault(
					"Jack",
					0
				)
		);

		System.out.println("Keys");

		for (String key : scores.keySet()) {
			System.out.println(key);
		}

		System.out.println("Values");

		for (Integer value : scores.values()) {
			System.out.println(value);
		}

		System.out.println("Entries");

		for (Map.Entry<String, Integer> entry : scores.entrySet()) {
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}

		scores.remove("Bob");

		System.out.println("After remove = " + scores);
	}

	static void mapImplementationExample() {
		Map<Integer, String> hashMap = new HashMap<>();

		Map<Integer, String> linkedHashMap = new LinkedHashMap<>();

		Map<Integer, String> treeMap = new TreeMap<>();

		int[] keys = {30, 10, 20};

		for (int key : keys) {
			String value = "Value" + key;

			hashMap.put(key, value);
			linkedHashMap.put(key, value);
			treeMap.put(key, value);
		}

		System.out.println(
			"HashMap = " + hashMap
		);
		System.out.println(
			"LinkedHashMap = " + linkedHashMap
		);
		System.out.println(
			"TreeMap = " + treeMap
		);
	}

	static void stackExample() {
		Stack<String> stack = new Stack<>();

		stack.push("A");
		stack.push("B");
		stack.push("C");

		System.out.println(
			"Peek = " + stack.peek()
		);

		System.out.println(
			"Pop = " + stack.pop()
		);

		System.out.println(
			"Pop = " + stack.pop()
		);

		System.out.println(
			"Stack = " + stack
		);
	}

	static void queueExample() {
		Queue<String> queue = new ArrayDeque<>();

		queue.offer("A");
		queue.offer("B");
		queue.offer("C");

		System.out.println(
			"Peek = " + queue.peek()
		);

		System.out.println(
			"Poll = " + queue.poll()
		);

		System.out.println(
			"Poll = " + queue.poll()
		);

		System.out.println(
			"Queue = " + queue
		);
	}

	static void dequeExample() {
		Deque<String> deque = new ArrayDeque<>();

		deque.addFirst("B");
		deque.addFirst("A");

		deque.addLast("C");
		deque.addLast("D");

		System.out.println(
			"Deque = " + deque
		);

		System.out.println(
			"First = " + deque.peekFirst()
		);

		System.out.println(
			"Last = " + deque.peekLast()
		);

		System.out.println(
			"Remove first = " + deque.removeFirst()
		);

		System.out.println(
			"Remove last = " + deque.removeLast()
		);

		System.out.println(
			"Deque = " + deque
		);
	}

	static void dequeStackExample() {
		Deque<String> stack = new ArrayDeque<>();

		stack.push("A");
		stack.push("B");
		stack.push("C");

		System.out.println(
			"Peek = " + stack.peek()
		);

		System.out.println(
			"Pop = " + stack.pop()
		);

		System.out.println(
			"Pop = " + stack.pop()
		);
	}

	static void dequeQueueExample() {
		Deque<String> queue = new ArrayDeque<>();

		queue.offerLast("A");
		queue.offerLast("B");
		queue.offerLast("C");

		System.out.println(
			"Peek = " + queue.peekFirst()
		);

		System.out.println(
			"Poll = " + queue.pollFirst()
		);

		System.out.println(
			"Poll = " + queue.pollFirst()
		);
	}
}
