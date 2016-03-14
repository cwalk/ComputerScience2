import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * @author claytonwalker
 * 
 * Clayton Walker
 * COP 3503C Lab
 * Recitation #3
 * Due: 6/13/13
 * GridSearch.java
 * 
 * 
 * 
 * switch i and j, or x and y when you read in, he flipped them in the I/O input
 */

/**
 * Runtime of GridSearch algorithm is in O(n) time complexity because worst case we are going through the entire 2D array, through visiting all directions. 
 * It will work on the test input given in the homework and any other test input.
 * Example: Test input of
 * 2 1 0 1 4 2 1 2 3 3 2 1 1 2 2 1 2 1 3 2 3
 * 2 3 0 3 4 2 1 2 3 3 2 1 1 2 2 1 2 1 3 2 3
 * 3 1 3 3 4 2 1 2 3 3 2 1 1 2 2 1 2 1 3 2 3
 * 2 1 3 0 4 2 1 2 3 3 2 1 1 2 2 1 2 1 3 2 3
 * 
 * will print out:
 * true
 * false
 * true
 * true
 */
public class GridSearch {
	
	public static int startx;
	public static int endx;
	public static int starty;
	public static int endy;
	public static int [][] board;
	public static boolean [][] visited;
	public static boolean value;
	public static int n;

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub

		//Scan new file
		Scanner file = new Scanner(new File("gridsearch.in.txt"));
								
		/***In case that first one doesn't work, comment that line out, and uncomment this one please***/
		//Scanner file = new Scanner(new File("gridsearch.in"));
	
		while(file.hasNextLine()) {
			
			//Read in values from file
			startx = file.nextInt();
			starty = file.nextInt();
			endx = file.nextInt();
			endy = file.nextInt();
			n = file.nextInt();
			
			//Declaring 2D arrays
			int[][] board = new int[n][n];
			boolean[][] visited = new boolean[n][n];
			
			/**For tracing/visual aid**/
		/*	System.out.println("startx is " + startx);
			System.out.println("starty is " + starty);
			System.out.println("endx is " + endx);
			System.out.println("endy is " + endy);
			System.out.println("n is " + n);
			*/
			
			//Filling board with appropriate values
			for (int i=0;i<n;i++) {
				for(int j=0;j<n;j++){
					board [i][j] = file.nextInt();
					//System.out.println("board["+i+"]["+j+"] is " + board[i][j]);
				}
			}
			//Filling visited with false
			for (int i=0;i<n;i++) {
				for(int j=0;j<n;j++){
					visited [i][j] = false;
					//System.out.println("visited["+i+"]["+j+"] is " + visited[i][j]);
				}
			}
			
			/**For tracing/visual aid**/
			/*		
			//prints out the starting indexes (it's backwards on purpose)
			System.out.println("[startx][starty] is: " + board[starty][startx]);
			System.out.println("[endx][endy] is: " + board[endy][endx]);
			//prints out what the board looks like
			System.out.println("The board["+n+"]["+n+"] should look like:");
			for (int i=0;i<n;i++) {
				for(int j=0;j<n;j++){
					System.out.print(board[i][j] + " ");
				}
				System.out.println();
			}
		
			//prints out what visited board looks like
			System.out.println("The visited board["+n+"]["+n+"] should look like:");
			for (int i=0;i<n;i++) {
				for(int j=0;j<n;j++){
					System.out.print(visited[i][j] + " ");
				}
				System.out.println();
			}*/	
			
			
			//Calls method and stores result in value
			value = canMove(board, starty, startx, endy, endx, visited);
			//based on value, prints out correct output to console
			if (value == true) {
				System.out.println("true");
			}
			else {
				System.out.println("false");
			}
		}
		
		file.close();
	}//end Main
	
	public static boolean canMove(int[][] board, int startx, int starty, int endx, int endy, boolean[][] visited) {
		
		//if you have arrived at the correct location, return true
		if (startx == endx && starty == endy) {
			return true;
		}
		//if you go out of bounds, or hit a already visited node, return false
		else if (startx<0 || starty<0 || startx>=n || starty>=n || visited[startx][starty]==true) {
			return false;
		}
		//if the starting value is already larger than the ending value, return false immediately
		else if (board[startx][starty] > board[endx][endy]) {
			return false;
		}
		//else, check each direction
		else{
			visited[startx][starty] = true;
			return canMove(board, startx, starty-1, endx, endy, visited) 
					|| canMove(board, startx, starty+1, endx, endy, visited) 
					|| canMove(board, startx-1, starty, endx, endy, visited) 
					|| canMove(board, startx+1, starty, endx, endy, visited);
		}
	
	} //end canMove method

}//end GridSearch class
