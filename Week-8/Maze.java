import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * @author claytonwalker 
 * 
 * Clayton Walker
 * COP 3503C Lab
 * Recitation #8
 * Due: 7/18/13
 * Maze.java
 */

/**
 * It will work on the test input given in the homework and any other test input.
 * Example: Test input of
 * 3
 * 5
 * xxxxx
 * __x__
 * x___x
 * x_x_x
 * xxxxx
 * 15 
 * xxxxxxxxxxxxxxx 
 * ______x________ 
 * x_xxx_x_xxxxxxx 
 * x_x_x_x_______x 
 * x_x_x_xxx_x_xxx 
 * x_x_____x_x_x_x 
 * x_x_xxx_xxx_x_x 
 * x_x_x___x___x_x 
 * x_x_x_xxx_xxx_x 
 * x_x_x_x___x___x 
 * x_x_x_x_xxx_x_x 
 * x_x_x_______x_x 
 * x_x_xxxxxxxxx_x 
 * x_x_xxxxxxxxx_x 
 * xxxxxxxxxxxxxxx 
 * 15 
 * xxxxxxxxxxxxxxx 
 * ______x________ 
 * x_xxx_x_xxxxxxx 
 * x_x_x_x_______x 
 * x_x_x_xxx_x_xxx 
 * x_x_____x_x_x_x 
 * x_x_xxx_xxx_x_x 
 * x_x_x___x___x_x 
 * x_x_x_xxx_xxx_x 
 * x_x_x_x___x___x 
 * x_x_x_x_xxx_x_x 
 * x_x_xxxxxxxxx_x 
 * x_x_xxxxxxxxx_x 
 * x_x_xxxxxxxxx_x 
 * xxxxxxxxxxxxxxx
 * 
 * will print out:
 * Data Set 1: The robot CAN get to the other side.
 * Data Set 2: The robot CAN get to the other side.
 * Data Set 3: The robot CAN NOT get to the other side.
 * 
 * Pseudo Code Explanation:
 * From our start point, we will iterate through each possible starting move.
 * From there, we recursively move forward.
 * If we ever get stuck, the recursion takes us back to where we were, and we try the next possible move.
 * 
 * My algorithm just turns the maze in the file into a 2D array of 1s and 0s. 
 * From here, I recursively move each direction, and check for the 3 cases being:
 * out of bounds, hitting a wall, and if I have visited this spot before.
 * 
 * When the robot reaches the end column, and the spot is a valid open space, findpath returns true.
 */

public class Maze {

	public static int numTestCases;
	public static int n;
	public static int[][] maze2D;
	public static int[] maze1D;
	public static int index;
	public static String lineOfMaze;
	public static int startrow;		
	public static int startcol;		
    public static int endcol;
    public static char[] array;
    public static boolean [][] visited;
    
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
			
		//Open the file
		Scanner file = new Scanner(new File("maze.in.txt"));
													
		//read in the number of Test Cases
		numTestCases = file.nextInt();
				
		//for each Test Case
		for (int i=1;i<=numTestCases;i++) {
			
			//read in the Game Order
			n = file.nextInt();
			
			//initialize the maze and array to take care of Strings to chars
			//maze2D is the 2D array maze that holds 1s and 0s
			//maze1D is a 1D array that holds the entire maze from the file converted in 1s and 0s as an array
			//array is a char array that holds the x and _ characters parsed from the String lineOfMaze from the file
			//visited is to check if we have visited that spot already in the maze
			maze2D = new int[n][n];
			maze1D = new int[n*n];
			array = new char[n*n];
			visited = new boolean[n][n];
			index = 0;
			
			//Filling visited with false
			for (int j=0;j<n;j++) {
				for(int k=0;k<n;k++){
					visited [j][k] = false;
					//System.out.print(visited[j][k] + " ");
				}
				//System.out.println();
			}
			
			
			//Start and end details
			startrow = 1;		
			startcol = 0;		
		    endcol = n-1;
		    
		    //for every line in the maze
			for(int j=0;j<n;j++) {
				
				//read in the line as a string
				lineOfMaze = file.next();
				//convert that string into an array of characters
				array = lineOfMaze.toCharArray();
			    for (int k = 0; k < array.length; k++) {
			    	//System.out.print(array[k]);
			    	
			    	//if the character was an 'x' then add a 0 to maze1D
			    	//this represents a wall
			    	if (array[k] == 'x') {
						maze1D[index] = 1;
					}
			    	//if the character was a '_' then add a 1 to maze1D
			    	//this represents an open space
			    	else if (array[k] == '_') {
						maze1D[index] = 0;
					}
			    	index++;
			    }
			    //System.out.println();
			}	
				
			index=0;
			//take values in maze1D and put them in maze2D
			//makes it easier to print out and perform DFS
			for(int j=0;j<n;j++) {
				for(int k=0;k<n;k++) {
					
					maze2D[j][k] = maze1D[index];
					index++;
					//System.out.print(maze2D[j][k]);
				}
				//System.out.println();
			}
			
			//print statements
			System.out.printf("Data Set " + i + ": ");
			if (findpath(startrow, startcol, n, maze2D, visited, endcol)==true) {
				System.out.println("The robot CAN get to the other side.");
			}
			else {
				System.out.println("The robot CAN NOT get to the other side.");
			}
		}	
	file.close();
	}//end main
	
	public static boolean findpath(int row, int col, int n, int[][] maze2D, boolean[][] visited, int endcol){
		/*This is the recursive function that finds the path.
		

		/*These 3 statements are the termination conditions:
			out of bounds,  wall, and previously visited, respectively*/
		if (row<0 || col<0 || row>=n || col>=n) return false; //if this is out of the maze bounds
		if (maze2D[row][col]==1) return false;		//if this is a wall
		if (visited[row][col]==true) return false; //if we've been here before
		
		//mark as visited
		visited[row][col]=true;
		
		//winning state, we got to the end column
		if (col==endcol && (maze2D[row][col]==0)){			
			/*Reached the end, thus finding a valid path*/			
			return true;
		}

		/*Below, recursively call to go to other squares*/
		return findpath(row-1, col, n, maze2D, visited, endcol) || findpath(row, col-1, n, maze2D, visited, endcol) ||
				findpath(row, col+1, n, maze2D, visited, endcol) || findpath(row+1, col, n, maze2D, visited, endcol);
	}//end findpath method
	
}//end Maze class
