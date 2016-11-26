package assignment09;

import java.io.*;

/**
 * The class that acts as storage for points in a maze (a 2D array)
 *
 * @author Brayden Wright (u0895942), Abdulaziz Aljanahi (u0901606)
 */
@SuppressWarnings("WeakerAccess")
public class Graph {
    private Node[][] nodeArray;
    private Node start, goal;
    private int width, height, dotCount;

    /**
     * No parameters passed, start with empty array of nodes.
     * Not really needed, perhaps remove?
     */
    public Graph() {
        nodeArray = new Node[0][0];
    }

    /**
     * Create the graph by loading from a file, should be a maze file.
     *
     * @param filename - the file to load
     */
    public Graph(String filename) {
        fromFile(new File(filename));
    }

    /**
     * @return - this graph's starting node
     */
    public Node getStart() {
        return this.start;
    }

    /**
     * @return - this graph's goal node
     */
    public Node getGoal() {
        return this.goal;
    }

    /**
     * Primarily for testing purposes, e.g. comparing path lengths.
     *
     * @return - the number of dots in the graph
     */
    public int getPathLength() {
        return this.dotCount;
    }

    /**
     * Load map from the passed file, formatted similarly to the given example mazes.
     * Simply loads everything, but doesn't add walls to the list of neighbors.
     *
     * @param file - the maze file
     */
    public void fromFile(File file) {
        String line;

        // Create a reader on the file
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            // Read the first line
            line = reader.readLine();

            // Dimensions are in the first line, grab them
            String[] dimensions = line.split(" ");
            height = Integer.parseInt(dimensions[0]);
            width = Integer.parseInt(dimensions[1]);

            // Populate the node array
            nodeArray = new Node[width][height];
            int xPos = 0;
            int yPos = 0;
            while ((line = reader.readLine()) != null) {
                char[] lineChars = line.toCharArray();

                // Loop over every line to load this graph with nodes
                for (char chr : lineChars) {

                    // Create a new node and add it to this graph
                    nodeArray[xPos][yPos] = new Node(chr, new Point(xPos, yPos + 1));

                    // Keep track of Start and Goal nodes
                    if (chr == 'S') start = nodeArray[xPos][yPos];
                    if (chr == 'G') goal = nodeArray[xPos][yPos];

                    // Increment dot count if they exist
                    if (chr == '.') dotCount++;

                    // Move to the right
                    xPos++;
                }

                // Correct xPos and yPos before continuing loop
                yPos++;
                xPos = 0;
            }

            /* Check neighbors by looping through every point
             * Add to the node's neighbor list if neighbors exist
             * Somewhat inefficient, but efficient enough for this assignment
             *
             * |10| Loop x points while x < width
             * |20| increment y
             * |30| Goto 10 while y < height
             */
            for (yPos = 0; yPos < height; yPos++) {

                for (xPos = 0; xPos < width; xPos++) {

                    // Grab the node stored at this point
                    Node node = nodeArray[xPos][yPos];

                    // North neighbor
                    if (yPos > 0)
                        node.addNeighbor(nodeArray[xPos][yPos - 1]);

                    // South neighbor
                    if (yPos < height - 1)
                        node.addNeighbor(nodeArray[xPos][yPos + 1]);

                    // East neighbor
                    if (xPos < width - 1)
                        node.addNeighbor(nodeArray[xPos + 1][yPos]);

                    // West neighbor
                    if (xPos > 0)
                        node.addNeighbor(nodeArray[xPos - 1][yPos]);

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Write this graph to a file with the specified filename.
     *
     * @param filename - the name of the file to write
     */
    public void toFile(String filename) {

        // Create a writer
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {

            // Write the height and width, formatted to math the example maze files
            writer.write(height + " " + width);
            writer.newLine();

            // Loop through every point in the graph
            for (int yPos = 0; yPos < height; yPos++) {
                for (int xPos = 0; xPos < width; xPos++) {

                    // Write this point's data to the file
                    writer.write(nodeArray[xPos][yPos].getData());
                }

                // Gonna move to the next row, so write a new line in the file
                writer.newLine();
            }

            // Close the writer, seems to be good practice to do so.
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
