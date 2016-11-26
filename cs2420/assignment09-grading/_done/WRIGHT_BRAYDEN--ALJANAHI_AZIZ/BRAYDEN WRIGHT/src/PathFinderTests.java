package assignment09;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Most of the tests simply count the output dots.
 * If the output dots are <= the solution dots, test passes.
 *
 * @author Brayden Wright (u0895942), Abdulaziz Aljanahi (u0901606)
 */
public class PathFinderTests {

    /* Some strings to make copy/paste/modify process easier
     * If files can't be found, check the value of DIR first
     * Replace "mazes" with "." to use current project folder, e.g. "CS2420"
     */
    private static final String DIR = "." + File.separator;
    private static final String MAZE = "Maze.txt";
    private static final String OUTPUT = "MazeOutput.txt";
    private static final String SOLUTION = "MazeSol.txt";

    @Test
    public void TinyMazeTest() {
        
        // A string representing the maze to test
        String mazeType = DIR + "tiny";
        
        // Attempt to solve the maze (tinyMaze.txt, tinyMazeOutput.txt)
        PathFinder.solveMaze(mazeType + MAZE, mazeType + OUTPUT);
        
        // Count the resulting dots and the expected dots
        Graph resultGraph = new Graph(mazeType + OUTPUT);
        int result = resultGraph.getPathLength();
        int expected = new Graph(mazeType + SOLUTION).getPathLength();

        // Solvable, so result should be greater than or equal to 1
        assertTrue(result >= 1);

        // Check if our path is at least as good as the given solution
        assertTrue(result <= expected);
    }

    @Test
    public void TinyOpenMazeTest() {

        /* Some of these test on maze files that break the naming convention.
         * They all work in a similar manner to the first test above unless otherwise specified.
         */
        String mazeType = DIR + "tinyOpen";
        PathFinder.solveMaze(mazeType + ".txt", mazeType + "Output.txt");
        
        Graph resultGraph = new Graph(mazeType + "Output.txt");
        int result = resultGraph.getPathLength();
        int expected = new Graph(mazeType + "Sol.txt").getPathLength();

        assertTrue(result >= 1);
        assertTrue(result <= expected);
    }

    @Test
    public void MediumMazeTest() {
        String mazeType = DIR + "medium";
        PathFinder.solveMaze(mazeType + MAZE, mazeType + OUTPUT);

        Graph resultGraph = new Graph(mazeType + OUTPUT);
        int result = resultGraph.getPathLength();
        int expected = new Graph(mazeType + SOLUTION).getPathLength();

        assertTrue(result >= 1);
        assertTrue(result <= expected);
    }

    @Test
    public void BigMazeTest() {
        String mazeType = DIR + "big";
        PathFinder.solveMaze(mazeType + MAZE, mazeType + OUTPUT);

        Graph resultGraph = new Graph(mazeType + OUTPUT);
        int result = resultGraph.getPathLength();
        int expected = new Graph(mazeType + SOLUTION).getPathLength();

        assertTrue(result >= 1);
        assertTrue(result <= expected);
    }

    @Test
    public void ClassicMazeTest() {
        String mazeType = DIR + "classic";
        PathFinder.solveMaze(mazeType + ".txt", mazeType + "Output.txt");

        Graph resultGraph = new Graph(mazeType + "Output.txt");
        int result = resultGraph.getPathLength();
        int expected = new Graph(mazeType + "Sol.txt").getPathLength();

        assertTrue(result >= 1);
        assertTrue(result <= expected);
    }

    @Test
    public void DemoMazeTest() {
        String mazeType = DIR + "demo";
        PathFinder.solveMaze(mazeType + MAZE, mazeType + OUTPUT);

        Graph resultGraph = new Graph(mazeType + OUTPUT);
        int result = resultGraph.getPathLength();
        int expected = new Graph(mazeType + SOLUTION).getPathLength();

        assertTrue(result >= 1);
        assertTrue(result <= expected);
    }

    @Test
    public void RandomMazeTest() {
        String mazeType = DIR + "random";
        PathFinder.solveMaze(mazeType + MAZE, mazeType + OUTPUT);

        Graph resultGraph = new Graph(mazeType + OUTPUT);
        int result = resultGraph.getPathLength();
        int expected = new Graph(mazeType + SOLUTION).getPathLength();

        assertTrue(result >= 1);
        assertTrue(result <= expected);
    }

    @Test
    public void StraightMazeTest() {
        String mazeType = DIR + "straight";
        PathFinder.solveMaze(mazeType + ".txt", mazeType + "Output.txt");

        Graph resultGraph = new Graph(mazeType + "Output.txt");
        int result = resultGraph.getPathLength();
        int expected = new Graph(mazeType + "Sol.txt").getPathLength();

        assertTrue(result >= 1);
        assertTrue(result <= expected);
    }

    @Test
    public void TurnMazeTest() {
        String mazeType = DIR + "turn";
        PathFinder.solveMaze(mazeType + ".txt", mazeType + "Output.txt");

        Graph resultGraph = new Graph(mazeType + "Output.txt");
        int result = resultGraph.getPathLength();
        int expected = new Graph(mazeType + "Sol.txt").getPathLength();

        assertTrue(result >= 1);
        assertTrue(result <= expected);
    }

    @Test
    public void UnsolvableMazeTest() {
        String mazeType = DIR + "unsolvable";
        PathFinder.solveMaze(mazeType + ".txt", mazeType + "Output.txt");

        Graph resultGraph = new Graph(mazeType + "Output.txt");
        int result = resultGraph.getPathLength();
        int expected = new Graph(mazeType + "Sol.txt").getPathLength();

        // Unsolvable, result dot count should be 0.
        assertEquals(0, result);
        assertTrue(result <= expected);
    }

    @Test
    public void SpiralMazeTest() {
        String mazeType = DIR + "spiral";
        PathFinder.solveMaze(mazeType + MAZE, mazeType + OUTPUT);

        Graph resultGraph = new Graph(mazeType + OUTPUT);
        int result = resultGraph.getPathLength();
        int expected = new Graph(mazeType + SOLUTION).getPathLength();

        assertTrue(result >= 1);
        assertTrue(result <= expected);
    }

    @Test
    public void NoSpaceMazeTest() {
        String mazeType = DIR + "noSpace";
        PathFinder.solveMaze(mazeType + MAZE, mazeType + OUTPUT);

        Graph resultGraph = new Graph(mazeType + OUTPUT);
        int result = resultGraph.getPathLength();
        int expected = new Graph(mazeType + SOLUTION).getPathLength();

        assertTrue(result <= expected);
    }

    @Test
    public void SmallSpiralMazeTest() {
        String mazeType = DIR + "smallSpiral";
        PathFinder.solveMaze(mazeType + MAZE, mazeType + OUTPUT);

        Graph resultGraph = new Graph(mazeType + OUTPUT);
        int result = resultGraph.getPathLength();
        int expected = new Graph(mazeType + SOLUTION).getPathLength();

        assertTrue(result <= expected);
    }
}
