package assignment09;
/**
 * Chenxi Sun and Jordan Newton
 * u0455173 and u1018840
 * 
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class PathFinder {
	private static int row = 0;

	private static Vertex start;
	private static Vertex goal;
	private static int height;
	private static int width;

	public PathFinder() {

	}

	private static Vertex[][] mazefinal = new Vertex[101][101];

	public static void main(String[] args) {
		PathFinder abc = new PathFinder();

		// abc.fileToString("mediumMaze.txt");
		// abc.fileToString("randomMaze.txt");
		// abc.fileToString("turn.txt");
		abc.printoutsolution("turn.txt", "mysoulution.txt");

	}

	public static void solveMaze(String inputFile, String outputFile) {
		shortestpath.clear();
		printoutsolution(inputFile,outputFile);
	}

	
	/**
	 * printoutsolution will take the string of filename and create a txt file with outputfilename as file name.
	 * The output file will have the shortest path with a dot in place of space.
	 * 
	 * @param filename
	 * @param outputfilename
	 */
	
	public static void printoutsolution(String filename, String outputfilename) {

		String results = "";
		try (BufferedReader input = new BufferedReader(new FileReader(filename))) {
			while (input.ready()) {
				String firstline = "";
				if (row == 0) {
					String[] dimensions = input.readLine().split(" ");

					height = Integer.parseInt(dimensions[0]);

					width = Integer.parseInt(dimensions[1]);

					System.out.println(height + " " + width);
					
				}

				//results += "\n";// add new line since bufferreader only has
								// readline and will erase \n
				results += input.readLine();
				row++;
				for (int i = 0; i < results.length(); i++) {

					if (results.charAt(i) == 'X') {
						mazefinal[row][i] = null;
						System.out.print("X");
					} else if (results.charAt(i) == ' ') {
						Vertex node = new Vertex();
						mazefinal[row][i] = node;
						System.out.print(" ");

					} else if (results.charAt(i) == 'S') {
						start = new Vertex();
						mazefinal[row][i] = start;
						System.out.print("S");
					} else if (results.charAt(i) == 'G') {
						goal = new Vertex();
						mazefinal[row][i] = goal;
						System.out.print("G");
					}

				}
				results = "";
				System.out.println("");

			}

			input.close();

			for (int i = 1; i < mazefinal.length - 1; i++) {

				for (int j = 1; j < mazefinal[i].length - 1; j++) {
					if (mazefinal[i][j] != null) {

						if (mazefinal[i + 1][j] != null) {
							Vertex current = mazefinal[i][j];
							Vertex neighbor = mazefinal[i + 1][j];
							current.adjacentneighbor.add(neighbor);

						}
						if (mazefinal[i - 1][j] != null) {
							Vertex current = mazefinal[i][j];
							Vertex neighbor = mazefinal[i - 1][j];
							current.adjacentneighbor.add(neighbor);

						}
						if (mazefinal[i][j - 1] != null) {
							Vertex current = mazefinal[i][j];
							Vertex neighbor = mazefinal[i][j - 1];
							current.adjacentneighbor.add(neighbor);

						}
						if (mazefinal[i][j + 1] != null) {
							Vertex current = mazefinal[i][j];
							Vertex neighbor = mazefinal[i][j + 1];
							current.adjacentneighbor.add(neighbor);

						}

					}

				}
			}

			System.out.println(breadthfirstSearch(start));
			System.out.println(printpath(goal).size());
			File outputFile = new File(outputfilename);
			try (PrintWriter output = new PrintWriter(new FileWriter(outputFile))) {
				output.println(height + " " + width);
				for (int i = 1; i < height + 1; i++) {
					
					for (int j = 0; j < width + 1; j++) {
					
						if (mazefinal[i][j] == null) {
							output.print("X");
						} else if (mazefinal[i][j] != null && shortestpath.contains(mazefinal[i][j])
								&& mazefinal[i][j] != start && mazefinal[i][j] != goal) {
							output.print(".");
						}

						else if (mazefinal[i][j] != null && mazefinal[i][j] != start && mazefinal[i][j] != goal&&!shortestpath.contains(mazefinal[i][j])) {
							output.print(" ");
						}

						else if (mazefinal[i][j] == start) {
							output.print("S");
						} else if (mazefinal[i][j] == goal) {
							output.print("G");
						}
						if(j==width){
							output.print("\n");
						}
						
					}
					
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	

	}

	public static ArrayList<Vertex> shortestpath = new ArrayList<Vertex>();//arraylist contain the vertexes for shortest path

	/**
	 * breadthfirstSearch that will go through the vertexes in broadest neighbors and mark the shortest path with distance 
	 * change the shortest path distance from infinity to path length return the shortest path length
	 * 
	 * @param x
	 * @return int
	 */
	
	private static int breadthfirstSearch(Vertex x) {

		MyQueue<Vertex> list = new MyQueue<Vertex>();

		x.distance = 0;
		list.enqueue(x);

		int distance = 0;
		while (!list.isEmpty()) {
			Vertex current = list.dequeue();

			for (int i = 0; i < current.adjacentneighbor.size(); i++) {

				Vertex neighbor = current.adjacentneighbor.get(i);
				if (neighbor.distance == Integer.MAX_VALUE) {

					neighbor.distance = 1 + current.distance;

					distance = neighbor.distance;

					neighbor.comefrom = current;

					list.enqueue(neighbor);

					if (neighbor == goal) {
						goal.distance = distance - 1;
						System.out.println("solved");

						return distance - 1;

					}

				}

			}

		}
		shortestpath.clear();
		System.out.println("unsolvable");
		return distance;

	}
/**
 * retrace the path after the breadthfirst search where the distance is marked
 * The retraced vertex is then added to an arraylist called shortest path and returned
 * 
 * 
 * 
 * @param Vertex x
 * @return ArrayList<Vertex>
 */
	public static ArrayList<Vertex> printpath(Vertex x) {
		 if (x.comefrom == start) {
				return shortestpath;

			}
		 else if (x.comefrom != null) {
			shortestpath.add(x.comefrom);
			return printpath(x.comefrom);
		}
		return shortestpath;
	}

}
