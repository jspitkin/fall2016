package assignment09;

import java.util.LinkedList;

/**
 * Find a path through a passed map.
 * addLast acts as enqueue and removeFirst acts as dequeue
 *
 * @author Brayden Wright (u0895942), Abdulaziz Aljanahi (u0901606)
 */
@SuppressWarnings("WeakerAccess")
public class PathFinder {

    /**
     * Quick abstraction of a LinkedList<Node> into a Queue<Node>.
     * Primarily to make it clear a Queue was used and help readability.
     */
    private static class Queue<Node> extends LinkedList<Node> {
        public void enqueue(Node node) {
            addFirst(node);
        }

        public Node dequeue() {
            return removeLast();
        }
    }

    /**
     * Start the maze solving process.
     *
     * @param inputFile  - the input map file for which to generate a path
     * @param outputFile - the output map file containing the generated path
     */
    public static void solveMaze(String inputFile, String outputFile) {

        // Create a graph from the input file and a queue to store the path.
        Graph graph = new Graph(inputFile);
        Queue<Node> pathQ = new Queue<Node>();

        // Mark the starting node as visited, then enqueue it.
        graph.getStart().visited();
        pathQ.enqueue(graph.getStart());

        // BFS to find a good path.
        Node current;
        while (!pathQ.isEmpty()) {
            current = pathQ.dequeue();
            current.visited();

            if (current == graph.getGoal()) break;
            if (current == graph.getStart() && current.getNeighbors() == null) break;

            for (Node node : current.getNeighbors()) {
                if (node.isVisited()) continue;
                node.visitedBy(current);
                pathQ.enqueue(node);
            }

        }

        // Write the path by travelling backwards from Goal.
        // If Goal has was never visited, or Start has no neighbors, the maze is unsolvable.
        Node visited;
        current = graph.getGoal();
        if (graph.getGoal().getVisitedBy() != null && graph.getStart().getNeighbors().size() != 0) {
            while ((visited = current.getVisitedBy()) != graph.getStart()) {
                visited.setData('.');
                current = visited;
            }
        }

        // Write the graph with found path to the output file.
        graph.toFile(outputFile);
    }

}
