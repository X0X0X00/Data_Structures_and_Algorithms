// Import any package as required

/***
 * Zhenhao Zhang
 * zzh133@u.rochester.edu
 * 32277234
 */

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class HuffmanSubmit implements Huffman {

	// Feel free to add more methods and variables as required.

	public void encode(String inputFile, String outputFile, String freqFile){
		// TODO: Your code here

		BinaryIn bin = new BinaryIn(inputFile); // 获取Bin 数据流
		Map<Character, Integer> frequencyMap = new HashMap<>(); // 储存frequency

		while (!bin.isEmpty()){ // 如果不是空的

			Character c = bin.readChar(); // 从Bin读取一个字符
			frequencyMap.put(c,frequencyMap.getOrDefault(c,0)+1); // 增加该字符的频率计数
		}

		HuffmanNode root = HuffmanCoding.buildHuffmanTree(frequencyMap); // 构建一个哈夫曼树，并返回这棵树的根
		HuffmanCoding.dumpFrequencyMap(frequencyMap,freqFile); // 频率映射保存到freqFile

		BinaryIn bin2 = new BinaryIn(inputFile); // 些行初始化了另一个BinaryIn对象
		BinaryOut bout = new BinaryOut(outputFile); // 和一个BinaryOut对象

		while(!bin2.isEmpty()){
			Character c = bin2.readChar(); // 从bin2读取一个字符
			String code = HuffmanCoding.getCode(root,c); // 从哈夫曼树获取字符c的哈夫曼编码
			bout.write(code); // 将哈夫曼编码写入输出文件
		}
		System.out.println();
		bout.close(); // 关闭文档

	}



	public void decode(String inputFile, String outputFile, String freqFile){
		// TODO: Your code here

		BinaryIn bin = new BinaryIn((inputFile));
		Map<Character,Integer> freMap = HuffmanCoding.readFrequencyMap(freqFile);
		BinaryOut bout = new BinaryOut(outputFile); // bout 表示传入outputFile的内容
		HuffmanNode root = HuffmanCoding.buildHuffmanTree(freMap); // root 把频率映射到Huffman 然后返回输的节点
		while (!bin.isEmpty()){ // 如果是空的
			HuffmanNode cur = root; // cur 为初始化的root
			while(!cur.isLeaf() && !bin.isEmpty()){ // 如果不是Leaf 或者 是空的就停止loop
				char bit = bin.readChar(); // 读取二进制的bit
				if (bit == '1'){ // 如果是1 移动到右节点
					cur = cur.right;
				}
				else {
					cur = cur.left; // 不是 就移动到左边
				}
			}
			bout.write(cur.data); // 输出文件
		}
		bout.flush();
		bout.close(); // 关闭
	}



	public static void main(String[] args) {
		Huffman huffman = new HuffmanSubmit();
		huffman.encode("ur.jpg", "ur.enc", "freq.txt");
		huffman.decode("ur.enc", "ur_dec.jpg", "freq.txt");


		huffman.encode("alice30.txt", "alice30.enc", "freq_alice30.txt");
		huffman.decode("alice30.enc", "alice30_dec.txt", "freq_alice30.txt");
		// After decoding, both ur.jpg and ur_dec.jpg should be the same.
		// On linux and mac, you can use `diff' command to check if they are the same.
	}

}
