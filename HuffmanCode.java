import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.PriorityQueue;

// Defining a class that creates nodes of the tree
class Node {
  // Storing character in ch variable of type character
  Character ch;
  // Storing frequency in freq variable of type int
  Integer freq;
  // Initially both child (left and right) are null
  Node left = null;
  Node right = null;

  // Creating a constructor of the Node class
  Node(Character ch, Integer freq) {
    this.ch = ch;
    this.freq = freq;
  }

  // Creating a constructor of the Node class
  public Node(Character ch, Integer freq, Node left, Node right) {
    this.ch = ch;
    this.freq = freq;
    this.left = left;
    this.right = right;
  }
}

// Main class
public class HuffmanCode {
  // Function to build Huffman tree
  public static void createHuffmanTree(String text) {
    // Base case: if user does not provide string
    if (text == null || text.length() == 0) {
      return;
    }

    // Count the frequency of appearance of each character and store it in a map
    // Creating an instance of the Map
    Map<Character, Integer> freq = new HashMap<>();

    // Loop iterates over the string and converts the text into character array
    for (char c : text.toCharArray()) {
      // Storing character and their frequency into Map by invoking the put() method
      freq.put(c, freq.getOrDefault(c, 0) + 1);
    }

    // Create a priority queue that stores current nodes of the Huffman tree.
    // Here a point to note that the highest priority means the lowest frequency
    PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(l -> l.freq));

    // Loop iterate over the Map and returns a Set view of the mappings contained in
    // this Map
    for (var entry : freq.entrySet()) {
      // Create a leaf node and add it to the queue
      pq.add(new Node(entry.getKey(), entry.getValue()));
    }

    // While loop runs until there is more than one node in the queue
    while (pq.size() != 1) {
      // Removing the nodes having the highest priority (the lowest frequency) from
      // the queue
      Node left = pq.poll();
      Node right = pq.poll();

      // Sum up the frequency of the nodes (left and right) that we have deleted
      int sum = left.freq + right.freq;

      // Adding a new internal node (deleted nodes i.e. right and left) to the queue
      // with a frequency
      // that is equal to the sum of both nodes
      pq.add(new Node(null, sum, left, right));
    }

    // Root stores pointer to the root of Huffman Tree
    Node root = pq.peek();

    // Trace over the Huffman tree and store the Huffman codes in a map
    Map<Character, String> huffmanCode = new HashMap<>();
    encodeData(root, "", huffmanCode);

    // Prints the Huffman codes for the characters
    System.out.println("Huffman Codes of the characters are: " + huffmanCode);

    // Prints the initial data
    System.out.println("The initial string is: " + text);

    // Creating an instance of the StringBuilder class
    StringBuilder sb = new StringBuilder();

    // Loop iterate over the character array
    for (char c : text.toCharArray()) {
      // Prints encoded string by getting characters
      sb.append(huffmanCode.get(c));
    }

    System.out.println("The encoded string is: " + sb);
    System.out.print("The decoded string is: ");

    // Special case: For input like 'a', 'aa', 'aaa', etc.
    if (isLeaf(root)) {
      while (root.freq-- > 0) {
        System.out.print(root.ch);
      }
    } else {
      // Traverse over the Huffman tree again and this time, decode the encoded string
      int index = -1;
      while (index < sb.length() - 1) {
        index = decodeData(root, index, sb);
      }
    }
  }

  // Traverse the Huffman Tree and store Huffman Codes in a Map
  // Function that encodes the data
  public static void encodeData(Node root, String str, Map<Character, String> huffmanCode) {
    if (root == null) {
      return;
    }

    // Checks if the node is a leaf node or not
    if (isLeaf(root)) {
      huffmanCode.put(root.ch, str.length() > 0 ? str : "1");
    }
    encodeData(root.left, str + '0', huffmanCode);
    encodeData(root.right, str + '1', huffmanCode);
  }

  // Traverse the Huffman Tree and decode the encoded string
  // Function that decodes the encoded data
  public static int decodeData(Node root, int index, StringBuilder sb) {
    // Checks if the root node is null or not
    if (root == null) {
      return index;
    }

    // Checks if the node is a leaf node or not
    if (isLeaf(root)) {
      System.out.print(root.ch);
      return index;
    }
    index++;
    root = (sb.charAt(index) == '0') ? root.left : root.right;
    return decodeData(root, index, sb);
  }

  // Function to check if the Huffman Tree contains a single node
  public static boolean isLeaf(Node root) {
    // Returns true if both conditions return true
    return root.left == null && root.right == null;
  }

  // Driver code
  public static void main(String args[]) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter a string: ");
    String text = sc.nextLine();    // Function calling
    createHuffmanTree(text);
  }
}