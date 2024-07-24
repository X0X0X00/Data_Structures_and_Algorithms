/***
 * Zhenhao Zhang
 * zzh133@u.rochester.edu
 * 32277234
 */



import java.util.*;

class HuffmanNode implements Comparable<HuffmanNode> {
	int frequency;
	char data;
	HuffmanNode left, right;

	public HuffmanNode(int freq, char val) {
		frequency = freq;
		data = val;
		left = right = null;
	}

	public int compareTo(HuffmanNode node) {
		return frequency - node.frequency;
	}

	public boolean isLeaf() {
		return left == null && right == null;
	}
}

public class HuffmanCoding {

	public static void printCodes(HuffmanNode root, String code) {
		if (root.left == null && root.right == null) {
			System.out.println(root.data + ":" + code);
			return;
		}

		printCodes(root.left, code + "0");
		printCodes(root.right, code + "1");
	}

	public static HuffmanNode buildHuffmanTree(String text) {
		Map<Character, Integer> frequencyMap = new HashMap<>();
		for (char c : text.toCharArray()) {
			frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
		}

		HuffmanNode root = buildHuffmanTree(frequencyMap);
		System.out.println("encoding：");
		printCodes(root, "");
		return root;
	}

	public static HuffmanNode buildHuffmanTree(Map<Character, Integer> frequencyMap) {
		PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();
		for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
			priorityQueue.add(new HuffmanNode(entry.getValue(), entry.getKey()));
		}

		while (priorityQueue.size() > 1) {
			HuffmanNode x = priorityQueue.poll();
			HuffmanNode y = priorityQueue.poll();

			HuffmanNode newNode = new HuffmanNode(x.frequency + y.frequency, '-');
			newNode.left = x;
			newNode.right = y;

			priorityQueue.add(newNode);
		}

		HuffmanNode root = priorityQueue.peek();
		return root;
	}

	public static void dumpFrequencyMap(Map<Character, Integer> frequencyMap, String outputFile) {
		BinaryOut bout = new BinaryOut(outputFile);
		for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
			// each line: 8-digit ascii representation of the character : frequency
			String ascii = Integer.toBinaryString(entry.getKey());
			while (ascii.length() < 8) {
				ascii = "0" + ascii;
			}
			bout.write(ascii+":"+entry.getValue()+"\n");
		}
		bout.close();
	}

	public static Map<Character, Integer> readFrequencyMap(String freqFile) {
		BinaryIn bin = new BinaryIn(freqFile);
		Map<Character, Integer> frequencyMap = new HashMap<>();
		String content = bin.readString();
		String[] lines = content.split("\n");
		for(String line : lines) {
			String[] parts = line.split(":");
			frequencyMap.put((char) Integer.parseInt(parts[0], 2), Integer.parseInt(parts[1]));
		}

		return frequencyMap;
	}
	public static String getCode(HuffmanNode node, Character c) {
		return getCode(node, c, "");
	}


	public static String getCode(HuffmanNode node, Character c, String currentCode) {
		if (node == null) {
			return "";
		}

		if (node.data == c && node.isLeaf()) {
			return currentCode;
		}

		String left = getCode(node.left, c, currentCode + "0");
		if (!left.isEmpty()) {
			return left;
		}

		String right = getCode(node.right, c, currentCode + "1");
		if (!right.isEmpty()) {
			return right;
		}

		return "";

	}

	// pre-save the mapping of character and code
//	public static Map<Character, String> map = new HashMap<>();
//
//	public static String saveCode(HuffmanNode node, Character c, String currentCode) {
//		if (node == null) {
//			return "";
//		}
//
//		if (node.data == c && node.isLeaf()) {
//			map.put(c, currentCode);
//			return currentCode;
//		}
//
//		String left = getCode(node.left, c, currentCode + "0");
//		if (!left.isEmpty()) {
//			return left;
//		}
//
//		String right = getCode(node.right, c, currentCode + "1");
//		if (!right.isEmpty()) {
//			return right;
//		}
//		return "";
//
//	}
//
//	public static Map<Character, String> getCodeMap() {
//		return map;
//	}

}
