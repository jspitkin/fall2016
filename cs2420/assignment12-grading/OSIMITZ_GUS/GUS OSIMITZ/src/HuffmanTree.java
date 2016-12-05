package assignment12;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Implements file compression and decompression using Huffman's algorithm,
 * which encodes and decodes each character of a file using a binary trie.
 *
 * @author Thomas Osimitz - U0970671
 */
public class HuffmanTree {

    /**
     * Represents a single node of a Huffman Tree.
     */
    private class Node implements Comparable<Node> {

        public int symbol; // character to be encoded/decoded

        public int weight; // number of occurrences of the character

        public Node leftChild, rightChild, parent;

        /**
         * Constructs a leaf node.
         */
        public Node(int _symbol, int _weight) {
            symbol = _symbol;
            weight = _weight;
            leftChild = rightChild = parent = null;
        }

        /**
         * Constructs an internal node. Note that an internal node has a weight (sum
         * of the weights of its children) but no character (INCOMPLETE_CODE).
         *
         * @param left
         *          - left child of the new node
         * @param right
         *          - right child of the new node
         */
        public Node(Node left, Node right) {
            symbol = INCOMPLETE_CODE;
            weight = left.weight + right.weight;
            leftChild = left;
            rightChild = right;
            parent = null;
        }

        /**W
         * Returns a string containing all of the edges in the tree rooted at "this"
         * node, in DOT format.
         */
        public String generateDot() {
            String atNode = Character.toString((char) symbol);
            if (symbol == INCOMPLETE_CODE) {
                atNode = " ";
            }
            else if (symbol == EOF) {
                atNode = "EOF";
            }
            else if (symbol == '\n') {
                atNode = "newline";
            }
            else if (symbol == '\t') {
                atNode = "tab";
            }
            else if (symbol == ' ') {
                atNode = "space";
            }
            atNode += " " + weight;
            String ret = "\tnode" + hashCode() + " [label = \"<f0> |<f1> " + atNode
                    + "|<f2> \"]\n";
            if (leftChild != null) {
                ret += "\tnode" + hashCode() + ":f0 -- node" + leftChild.hashCode()
                        + ":f1\n" + leftChild.generateDot();
            }
            if (rightChild != null) {
                ret += "\tnode" + hashCode() + ":f2 -- node" + rightChild.hashCode()
                        + ":f1\n" + rightChild.generateDot();
            }

            return ret;
        }

        /**
         * Compares two Huffman nodes, using weight.
         *
         * @param rhs
         *          - right-hand side node
         * @return a value > 0 if this node is larger than rhs, a value < 0 if this
         *         node is smaller than rhs, 0 if the nodes are equal
         */
        public int compareTo(Node rhs) {
            int result = this.weight - rhs.weight;
            if(result == 0) {
                Node left = this.getLeftMostChild();
                Node right = rhs.getLeftMostChild();
                return left.symbol - right.symbol;
            }
            return result;
        }
        private Node getLeftMostChild() {
            Node temp = this;
            while(temp.leftChild != null) {
                temp = temp.leftChild;
            }
            return temp;
        }
    }

    private Node root; // root of the Huffman tree

    private Map<Integer, Node> symbols; // set of characters in the file mapped to their corresponding Huffman nodes

    private List<Integer> data; // list of characters in the file

    private final static int ERROR = -3; // used to detect errors in Huffman encoding

    private final static int INCOMPLETE_CODE = -2; // used to detect internal Huffman nodes (i.e., not a leaf node and has no character)

    private final static int EOF = 0; // used to detect the end of a file compressed using Huffman's algorithm

    /**
     * Generates a compressed version of the input file.
     *
     * @param infile
     *          - input file of (uncompressed) data
     * @param outfile
     *          - output file of compressed data
     */
    public void compressFile(File infile, File outfile) {
        symbols = new HashMap<Integer, Node>();
        data = new ArrayList<Integer>();

        try(DataOutputStream out = new DataOutputStream(new FileOutputStream(outfile))) {
            // read characters and counts frequency of each
            readData(new FileInputStream(infile));

            // build Huffman tree using frequency information
            createTree();

            // write character and frequencies to beginning of compressed file
            writeEncodingInfo(out);

            // for each character in the input file, encodes it using the Huffman tree
            // and writes the bit code
            try(BitOutputStream bout = new BitOutputStream(out)) {
                for (int ch : data) {
                    bout.writeBits(getCode(ch & 0xff));
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    /**
     * Generates a decompressed version of the input file
     *
     * @param infile
     *          - input file of (compressed) data
     * @param outfile
     *          - output file of decompressed data
     */
    public void decompressFile(File infile, File outfile) {
        symbols = new HashMap<Integer, Node>();
        data = new ArrayList<Integer>();

        try(DataInputStream in = new DataInputStream(new FileInputStream(infile))) {
            // read characters and frequency information
            readEncodingInfo(in);

            // build Huffman tree using frequency information
            createTree();

            // read Huffman codes corresponding to each character in original file
            try(BitInputStream bin = new BitInputStream(in)) {
                readCompressedData(bin);
            }

            // use the codes to find each character in the Huffman tree and print the
            // characters
            try(FileOutputStream out = new FileOutputStream(outfile)) {
                for (int i : data) {
                    out.write(i);
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    /**
     * COMPRESSION
     *
     * Reads all characters, while maintaining a count of the occurrences of each
     * character (AKA the character's weight).
     *
     * @param in
     *          - stream for the input file
     * @throws IOException
     */
    private void readData(InputStream in) throws IOException {
        int ch;
        // read characters until end of file
        while ((ch = in.read()) != -1) {
            // if character had not yet been seen, put it in the tree (weight = 1)
            if (symbols.get(ch) == null) {
                symbols.put(ch, new Node(ch, 1));
            }
            // if character has already been seen, increment weight
            else {
                symbols.get(ch).weight++;

            }
            // keep track of all characters (in order)
            data.add(ch);
        }

        // add end-of-file marker to tree and list of characters
        // this lets use know when to stop during decompression
        symbols.put(EOF, new Node(EOF, 1));
        data.add(EOF);
    }

    /**
     * DECOMPRESSION
     *
     * Reads the Huffman codes corresponding to each character in original file.
     *
     * @param in
     *          - stream for the input file
     * @throws IOException
     */
    private void readCompressedData(BitInputStream in) throws IOException {
        String bits = "";
        int bit;
        int decode = 0;

        // read bits until end of file
        while ((bit = in.readBit()) != -1) {
            bits += bit == 0 ? "0" : "1";

            // follow the path in the Huffman tree indicated by the bit code and get
            // the character encoded
            decode = getChar(bits);

            if (decode == INCOMPLETE_CODE) { // if the path leads to an internal node,  not a complete code; get next bit
                continue;
            }
            else if (decode == ERROR) {
                throw new IOException("Decoding error");
            }
            else if (decode == EOF) {
                return;
            }

            // keep track of each character decoded
            data.add(decode);
            bits = "";
        }
    }

    /**
     * COMPRESSION
     *
     * Writes the character and weight information to the output file, so that the
     * Huffman tree can reconstructed at the time of decompression.
     *
     * @param out
     *          - stream for the output file
     * @throws IOException
     */
    private void writeEncodingInfo(DataOutputStream out) throws IOException {
        for (Map.Entry<Integer, Node> e : symbols.entrySet()) {
            out.writeByte(e.getKey());
            out.writeInt(e.getValue().weight);
        }

        // special code to indicate end of file
        out.writeByte(0);
        out.writeInt(0);
    }

    /**
     * DECOMPRESSION
     *
     * Reads the character and weight information at the beginning of the
     * compressed file, so that the Huffman tree can reconstructed.
     *
     * @param in
     *          - stream for the input file
     * @throws IOException
     */
    private void readEncodingInfo(DataInputStream in) throws IOException {
        int ch;
        int num;
        while (true) {
            ch = in.readByte();
            num = in.readInt();
            if (num == 0) { // EOF
                return;
            }
            symbols.put(ch, new Node(ch, num));
        }
    }

    /**
     * COMPRESSION and DECOMPRESSION
     *
     * Constructs a Huffman tree to represent bit codes for each character. (See
     * algorithm and examples in Lecture 22.)
     */
    private void createTree() {
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        pq.addAll(symbols.values());
        Node newParent;
        while(pq.size() >= 2) {
            Node left = pq.remove();
            Node right = pq.remove();
            newParent = new Node(left, right);
            left.parent = newParent;
            right.parent = newParent;
            pq.add(newParent);
        }
        root = pq.remove();
    }

    /**
     * COMPRESSION
     *
     * Returns the bit code for a character, by traversing the path from the
     * character's leaf node up to the root of the tree. Encountering a left child
     * causes a 0 to be pre-appended to the bit code, and encountering a right
     * child causes a 1 to be pre-appended. (See algorithm and examples in Lecture
     * 22.)
     *
     * @param ch
     *          - character to be encoded
     */
    private int[] getCode(int ch) {
        Node tempChild = symbols.get(ch);
        Node tempParent = tempChild.parent;
        String bitCode = "";
        while(tempParent != null) {
            if(tempParent.leftChild.equals(tempChild)) {
                bitCode = bitCode + "0";
                tempChild = tempParent;
                tempParent = tempParent.parent;
            }
            else if(tempParent.rightChild.equals(tempChild)) {
                bitCode = bitCode + "1";
                tempChild = tempParent;
                tempParent = tempParent.parent;
            }
        }
        int[] bitCodeInts = new int[bitCode.length()];
        char[] charArray = bitCode.toCharArray();
        int count = 0;
        for (int curr = charArray.length - 1; curr >= 0; curr--) {
            bitCodeInts[curr] = Character.getNumericValue(charArray[count]);
            count++;
        }
        return bitCodeInts;
    }

    /**
     * DECOMPRESSION
     *
     * Returns the character for the bit code, by traversing the path from the
     * root of the tree to the character's leaf node. A 0 in the code causes the
     * path to go through a left child, and a 1 in the code causes the path to go
     * through a right child.
     *
     * @param code
     *          - the bit code indicating the path from the root
     * @return the character encoded or ERROR if the path is not valid
     */
    private int getChar(String code) {
        Node curr = root;

        for (int i = 0; (i < code.length()); i++) {
            if (code.charAt(i) == '0') {
                curr = curr.leftChild;
            }
            else {
                curr = curr.rightChild;
            }
        }

        if (curr == null) {
            return ERROR;
        }

        return curr.symbol;
    }

    /**
     * Generates a DOT file for visualizing the Huffman tree.
     *
     * @param dotFilename
     *          - filename of DOT file to generate
     */
    public void huffmanToDot(String dotFilename) {
        try(PrintWriter out = new PrintWriter(dotFilename)) {
            out.println("graph Tree {\n\tnode [shape=record]\n");

            if (root == null)
                out.println("");
            else
                out.print(root.generateDot());

            out.println("}");
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}