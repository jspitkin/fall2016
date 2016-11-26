package assignment09;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;

public class PathGrader {
	static final String mazeFilePath = "assignment9_files/";
	
	static final String[] testFiles = new String[]{"bigMaze", "demoMaze2", "openRand", "straight2",
		"turn", "bigMaze2", "long", "openRand2", "tinyMaze", "turn2", "bigOpen", "long2", "randomMaze",
		"tinyMaze2", "unsolvable", "bigOpen2", "mediumMaze", "randomMaze2", "tinyOpen", "unsolvable2",
		"classic", "mediumMaze2", "spiral", "tinyOpen2", "classic2", "open", "spiral2", "trapped",
		"demoMaze", "open2", "straight", "trapped2"};
	
	private static class SimpleMaze {
		char[][] maze;
		int width, height;
		int sx, sy, gx, gy;
		int pathLen;
		
		SimpleMaze(String filename) throws Exception {
			BufferedReader input = new BufferedReader(new FileReader(PathGrader.findFile(filename)));
			
			String[] dimensions = input.readLine().split(" ");
			height = Integer.parseInt(dimensions[0]);
			width = Integer.parseInt(dimensions[1]);
			
			maze = new char[height][width];
			
			for(int i=0; i < height; i++)
			{
				String row = input.readLine();
				
				for(int j=0; j < row.length(); j++) {
					char cur;
					switch(cur = row.charAt(j))
					{
					case '.':
						pathLen++;
						break;
					case 'S':
						sx = j;
						sy = i;
						break;
					case 'G':
						gx = j;
						gy = i;
						break;
					case 'X':
					case ' ':
						break;
					default:
						throw new UnknownCharException(cur);
					}
					maze[i][j] = cur;
				}
			}
			input.close();
		}
		
		public boolean looseCompare(SimpleMaze rhs) {
			if(width != rhs.width || height != rhs.height) {
				return false;
			}
			for(int y = 0; y < height; y++) {
				for(int x = 0; x < width; x++) {
					if(maze[y][x] != rhs.maze[y][x]) {
						if( !((maze[y][x] == ' ' && rhs.maze[y][x] == '.') ||
								(maze[y][x] == '.' && rhs.maze[y][x] == ' '))) {
							return false;
						}
					}
				}
			}
			return true;
		}
		
		public boolean checkCell(int x, int y) {
			if(maze[y][x] == '.' || maze[y][x] == 'G') {
				maze[y][x] = ' '; //so we don't loop back
				return true;
			}
			return false;
		}
	}
	
	private static class UnknownCharException extends Exception {
		private static final long serialVersionUID = 1L;
		public char errChar;
		public UnknownCharException(char errChar) {
			this.errChar = errChar;
		}
	}

	public static boolean isSolution(String infile, String outfile) {
		SimpleMaze in, out;
		int refPathLen;
		try {
			in = new SimpleMaze(mazeFilePath + infile + ".txt");
			refPathLen = new SimpleMaze(mazeFilePath + infile + "RefSol.txt").pathLen;
		} catch (Exception e) {
			System.err.println("GRADER ERRROR! Could not read input maze");
			System.exit(1);
			return false; //Because the compiler is stupid...
		}
		
		try {
			out = new SimpleMaze(outfile);
		}
		catch(FileNotFoundException e) {
			System.out.println("Failed " + infile + ": solveMaze did not create the specified output file, or output file is empty");
			return false;
		}
		catch(NumberFormatException e) {
			System.out.println("Failed " + infile + ": output does not contain proper height and width info");
			return false;
		}
		catch(UnknownCharException e) {
			System.out.println("Failed " + infile + ": contains unknown character \'" + e.errChar + "\'");
			return false;
		}
		catch(Exception e) {
			System.out.println("Failed " + infile + ": grader was not able to generate a graph from output maze file : " + e.getClass().getSimpleName());
			return false;
		}
		
		if(!in.looseCompare(out)) {
			System.out.println("Failed " + infile + ": output maze does not match input maze");
			return false;
		}

		if(refPathLen == 0) { // No solution, or S and G touching
			if(out.pathLen == 0) {
				return true;
			} else {
				System.out.println("Failed " + infile + ": input maze had no solution but output has a path");
				return false;
			}
		}

		if(out.pathLen != refPathLen) {
			System.out.println("Failed " + infile + ": path has wrong length: " + out.pathLen + ", should be: " + refPathLen);
			return false;
		}

		refPathLen++; // account for goal, previous refPathLen was just dot count
		int curx = out.sx, cury = out.sy, numVisited = 0;
		while(curx != out.gx || cury != out.gy) {
			if(out.checkCell(curx - 1, cury)) {
				curx--;
			} else if(out.checkCell(curx + 1, cury)) {
				curx++;
			} else if(out.checkCell(curx, cury - 1)) {
				cury--;
			} else if(out.checkCell(curx, cury + 1)) {
				cury++;
			} else {
				System.out.println("Failed " + infile + ": path is not connected to goal node");
				return false;
			}
			numVisited++;
			if(numVisited > refPathLen) {
				System.out.println("Failed " + infile + ": path contains a cycle");
				return false;
			}
		}
		
		//If we made it here, everything is valid
		return true;
	}
	
	private static class Tester implements Runnable {
		String infile, outfile;
		
		public Tester(String infile, String outfile) {
			
			this.infile = findFile(infile);
			this.outfile = findFile(outfile);
		}
		
		public void run() {
			try {
				PathFinder.solveMaze(infile, outfile);
			}catch (Throwable t) {
				if(t instanceof RunningInfinitelyException) {
					System.out.println("Failed " + infile + ": did not finish within 10 seconds");
				} else {
					System.out.println("Failed " + infile + ": threw exception " + t);
				}
			}
		}
	}

	/***
	 * Actually run a test in another thread.
	 */
	@SuppressWarnings("deprecation")
	private static boolean runTest(Runnable r) {
		Thread t = new Thread(r);
		t.start();
		try {
			t.join(10000);
			if (t.isAlive()) {
				t.stop(new RunningInfinitelyException());
				t.join();
				return false;
			}
			return true;
		} catch (InterruptedException e) {
			System.err.println("Something went wrong with the grader, run again");
			System.exit(1);
			return false; //Because the compiler is stupid...
		}
	}
	
	/**
	 * Used as a marker for printing a relevant error message when a student's
	 * code is running infinitely.
	 */
	static class RunningInfinitelyException extends Exception {
		private static final long serialVersionUID = 1L;
		public String toString() {
			return "Running Infinitely";
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("===PathFinder Grader Output===");
		
		// get rid of any old "out.txt"
		try {
			File f = new File("out.txt");
			f.delete();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		int testsPassed = 0;
		for(String s : testFiles) {
			//System.out.println("Testing maze: " + s);
			
			if(!runTest(new Tester(mazeFilePath + s + ".txt", /*s +*/ "out.txt"))) {
				continue;
			}
			
			if(isSolution(s, /*s +*/ "out.txt")) {
				testsPassed++;
			}
		}

		System.out.println();
		System.out.println("Passed " + testsPassed + "/32 tests");
		System.out.println("Test maze score:                       " + (int)((testsPassed / 32.0) * 80) + "/80");
		System.out.println("Quality of student's code and style:   /5");
		System.out.println("Students tests:                        /5");
		System.out.println("Analysis document:                     /10");
		System.out.println("Total:                                 /100");

		System.out.println("\n---------------\nTA coments:");
	}
	
	private static String findFile(String filename) {
		URL fileURL = PathGrader.class.getClassLoader().getResource(filename);
		if(fileURL != null ) {
			return fileURL.getFile();
		}
		return filename;
	}
}
