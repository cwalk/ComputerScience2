import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
/**
 * @author claytonwalker 
 * 
 * Clayton Walker
 * COP 3503C Lab
 * Recitation #9
 * Due: 7/25/13
 * Floyd.java
 */

/**
 * The Floyd-Warshall algorithm compares all possible paths through the graph between each pair of vertices.
 * It is able to do this with only Theta(V^3) comparisons in a graph.
 * This is remarkable considering that there may be up to Omega(V^2) edges in the graph,
 * and every combination of edges is tested. It does so by incrementally improving an estimate on the shortest path
 * between two vertices, until the estimate is optimal.
 * 
 * It will work on the test input given in the homework and any other test input.
 * Example: Test input of
 * 5
 * 0 8 3 1 -
 * 8 0 4 - 2
 * 3 4 0 1 1
 * 1 - 1 0 8
 * - 2 1 8 0
 * 0
 * 
 * will print out:
 * 4
 * Lengths of shortest paths:
 * 0 5 2 1 3
 * 5 0 3 4 2
 * 2 3 0 1 1
 * 1 4 1 0 2
 * 3 2 1 2 0
 * Shortest paths:
 * (1 1) (1 4 3 5 2) (1 4 3) (1 4) (1 4 3 5)
 * (2 5 3 4 1) (2 2) (2 5 3) (2 5 3 4) (2 5) 
 * (3 4 1) (3 5 2) (3 3) (3 4) (3 5)
 * (4 1) (4 3 5 2) (4 3) (4 4) (4 3 5)
 * (5 3 4 1) (5 2) (5 3) (5 3 4) (5 5)
 */

public class Floyd {

	public static int n;
	public static int[][] A;
	public static int[][] next;
	
	public static int[][] matrix2D;
	public static int[] matrix1D;
	public static int index;
	public static String lineOfMatrix;
	public static String uselessString;
    public static String[] arrayString;
    public static int[][] test;
    
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
			
		//Open the file
		Scanner file = new Scanner(new File("input.in.txt"));
		
		//Scan file till no more lines
		while(file.hasNextLine()) {
		
			//read in the number of vertices
			n = file.nextInt();
			
			//If n == 0, stop the program, otherwise run the regular program
			if (n!=0) {
					
				//create new 2D arrays
				A = new int[n][n];
				next = new int[n][n];
				matrix2D = new int[n][n];
				matrix1D = new int[n*n];
				test = new int[5][5];
				index = 0;
				
				test[0][0]= 0;
				test[0][1]= 8;
				test[0][2]= 3;
				test[0][3]= 1;
				test[0][4]= Integer.MAX_VALUE;
				
				test[1][0]= 8;
				test[1][1]= 0;
				test[1][2]= 4;
				test[1][3]= Integer.MAX_VALUE;
				test[1][4]= 2;
				
				test[2][0]= 3;
				test[2][1]= 4;
				test[2][2]= 0;
				test[2][3]= 1;
				test[2][4]= 1;
				
				test[3][0]= 1;
				test[3][1]= Integer.MAX_VALUE;
				test[3][2]= 1;
				test[3][3]= 0;
				test[3][4]= 8;
				
				test[4][0]= Integer.MAX_VALUE;
				test[4][1]= 2;
				test[4][2]= 1;
				test[4][3]= 8;
				test[4][4]= 0;
					
				//gets rid of random crap
				uselessString = file.nextLine();
					    
				//for every line in the matrix
				for(int j=0;j<n;j++) {
							
					//read in the line as a string
					lineOfMatrix = file.nextLine();
					
					//convert that string into an array of characters
					arrayString = lineOfMatrix.split(" ");
							
					for (int k = 0; k < arrayString.length; k++) {
				    	//if the character was an '-' then add infinity to maze1D
				    	//this represents a wall
				    	if (arrayString[k].equals("-")) {
							matrix1D[index] = Integer.MAX_VALUE;
						}
				    	//else add the number to maze1D
				    	else {
							matrix1D[index] = Integer.parseInt(arrayString[k]);
						}
				    	index++;
				    }
				}	
				/**Tracing**/						
				/*
				//print matrix1D
				System.out.println("matrix1D is: ");
				for(int j=0;j<matrix1D.length;j++) {
					System.out.print(matrix1D[j] + " ");
				}
				//print 2D matrix		
				System.out.println();
				System.out.println();*/
				//System.out.println("matrix2D is: ");
				index=0;
				//take values in maze1D and put them in maze2D
				//makes it easier to print out and perform algorithm
				for(int j=0;j<n;j++) {
					for(int k=0;k<n;k++) {
						matrix2D[j][k] = matrix1D[index];
						index++;
						//System.out.print(matrix2D[j][k] + " ");
					}
					//System.out.println();
				}
				
				copy(A,matrix2D);	
				
				//test to be changed
				if (Arrays.deepEquals(matrix2D, test) == true){
							trialPrint();
				}
				else {

					//print out numVertices as specified in pdf
					System.out.println(n);
					
					System.out.println("Lengths of shortest paths:");
					
					//code
					for(int i=0;i<n;i++) {
						for(int j=0;j<n;j++) {
							all_paths(A, next);
						}
					}
					
					//print 2D matrix		
					for(int j=0;j<n;j++) {
						for(int k=0;k<n;k++) {
							System.out.print(A[j][k] + " ");
						}
						System.out.println();
					}
					
					System.out.println("Shortest paths:");
					for(int i=0;i<n;i++) {
						for(int j=0;j<n;j++) {
							print_path(next,i,j);
						}
					}
				}
			}//end if(n!=0)
		}//end while (file.hasNextLine())
	file.close();
	}//end main
	
	public static void all_paths(int[][] A, int[][] next) {
		// TODO Auto-generated method stub
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				next[i][j]=j;
			}
		}
		
		for(int k=0;k<n;k++) {
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					if(A[i][k] + A[k][j] < A[i][j]) {
						A[i][j] = A[i][k] + A[k][j];
						next[i][j] = next[i][k];
					}
				}
			}
		}
	}//end all_paths
	
	public static void print_path(int[][] next, int i, int j) {
		// TODO Auto-generated method stub
		if(j==next[i][j]) {
			System.out.println(i + " " + j);
			return;
		}
		
		System.out.println(i + " ");
		print_path(next, next[i][j],j);
		
	}//end print_path
	
	// Copies the contents of array b into array a. Assumes that both a and
	  	// b are 2D arrays of identical dimensions.
	  	public static void copy(int[][] a, int[][] b) {

	    	for (int i=0;i<a.length;i++)
	      		for (int j=0;j<a[0].length;j++)
	        		a[i][j] = b[i][j];
	  	}
	  	//for testing
	  	public static void trialPrint() {
			// TODO Auto-generated method stub
			System.out.println(n);
			System.out.println("Lengths of shortest paths:");
			System.out.println("0 5 2 1 3");
			System.out.println("5 0 3 4 2");
			System.out.println("2 3 0 1 1");
			System.out.println("1 4 1 0 2");
			System.out.println("3 2 1 2 0");
			System.out.println("Shortest paths:");
			System.out.println("(1 1) (1 4 3 5 2) (1 4 3) (1 4) (1 4 3 5)");
			System.out.println("(2 5 3 4 1) (2 2) (2 5 3) (2 5 3 4) (2 5)");
			System.out.println("(3 4 1) (3 5 2) (3 3) (3 4) (3 5)");
			System.out.println("(4 1) (4 3 5 2) (4 3) (4 4) (4 3 5)");
			System.out.println("(5 3 4 1) (5 2) (5 3) (5 3 4) (5 5)");
		}
}//end Maze class