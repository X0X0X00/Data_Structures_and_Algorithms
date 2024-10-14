// Import any package as required


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;

public class HuffmanSubmit implements Huffman {

    public static class Node{
        private Character c;
        private int freq;
        private Node left;
        private Node right;

        //叶节点的构造函数
        public Node(Character c, int freq, Node left, Node right){
            this.c = c;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }
        public Boolean isLeaf(){
            return this.left == null && this.right == null;
        }
    }

    //方法创建代码表
    private static void create_the_code_Table(String[] codeTable, Node node, String code) {
        if (node.isLeaf()) {
            codeTable[node.c] = code;
            return;
        }
        create_the_code_Table(codeTable, node.left, code + "0");
        create_the_code_Table(codeTable, node.right, code + "1");
    }




    //为树创建优先级队列
    private static Node createTree(int[] freq) {
        //创建优先队列来存储节点
        PriorityQueue<Node> priority_queue = new PriorityQueue<>(
                Comparator.comparingInt(node -> node.freq));
        // 将叶子节点添加到优先队列
        for (char i = 0; i < freq.length; i++) {
            if (freq[i] > 0) {
                priority_queue.add(new Node(i, freq[i], null, null));
            }
        }
        // 为频率最小的两个节点创建父节点
        while (priority_queue.size() > 1) {
            // 移除频率最小的两个节点
            Node left = priority_queue.remove();
            Node right = priority_queue.remove();
            // 使用组合频率创建节点
            Node node_parent = new Node(null, left.freq + right.freq, left, right);
            // 将父节点添加回优先级队列
            priority_queue.add(node_parent);
        }
        return priority_queue.remove();
    }


    //计算文件中每个字符的频率
    public int[] computeFreq(String inputFile) {
        int[] freq = new int[256];
        BinaryIn in = new BinaryIn(inputFile);
        while (!in.isEmpty()) {
            char character = in.readChar();
            freq[character]++;
        }
        return freq;
    }


    public void encode(String inputFile, String outputFile, String freqFile) {
        // 计算字符频率
        int[] freq = computeFreq(inputFile);
        // 构建霍夫曼树
        Node root = createTree(freq);
        // 生成代码表
        String[] the_hf_codeTable = new String[256];
        create_the_code_Table(the_hf_codeTable, root, "");
        // 写出文件
        try(FileWriter fw = new FileWriter(freqFile)) {
            for(int i = 0; i < freq.length; i++) {
                fw.write(String.format("%08d", Integer.parseInt(Integer.toBinaryString(i))) + ":" + freq[i]);
                fw.write("\n");
            }
        } catch(IOException e){}
        BinaryIn input = new BinaryIn(inputFile);
        BinaryOut output = new BinaryOut(outputFile);
        while (!input.isEmpty()) {
            char c = input.readChar();
            String code = the_hf_codeTable[c];
            for (int i = 0; i < code.length(); i++) {
                if (code.charAt(i) == '0') {
                    output.write(false);
                } else {
                    output.write(true);
                }
            }
        }
        output.close();
    }

    public void decode(String inputFile, String outputFile, String freqFile){
        // 读取频率表
        int[] freq = new int[256];
        try(BufferedReader buffered_reader = new BufferedReader(new FileReader(freqFile))) {
            String theline;
            while ((theline = buffered_reader.readLine()) != null) {
                String[] nude_parts = theline.split(":");
                freq[Integer.parseInt(nude_parts[0], 2)] = Integer.parseInt(nude_parts[1]);
            }
        } catch(IOException e){}
        // 构建霍夫曼树
        Node root = createTree(freq);
        // 解码
        BinaryIn input = new BinaryIn(inputFile);
        BinaryOut output = new BinaryOut(outputFile);
        Node node = root;
        while (!input.isEmpty()) {
            boolean bits = input.readBoolean();
            if (bits) {
                node = node.right;
            } else {
                node = node.left;
            }
            if (node.isLeaf()) {
                output.write(node.c);
                node = root;
            }
        }
        output.close();
    }



   public static void main(String[] args) {
      Huffman  huffman = new HuffmanSubmit();
		huffman.encode("ur.jpg", "ur.enc", "freq.txt");
		huffman.decode("ur.enc", "ur_dec.jpg", "freq.txt");
       huffman.encode("alice30.txt", "alice30.enc", "freq_alice30.txt");
       huffman.decode("alice30.enc", "alice30_dec.txt", "freq_alice30.txt");
    }

}
