import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * @author claytonwalker
 * 
 * Clayton Walker
 * COP 3503C Lab
 * Recitation #4
 * Due: 6/20/13
 * Conversion.java
 */

/**
 * Runtime of Conversion algorithm is in O(n^2) time complexity because we are going through nested loops in the worst case. 
 * It will work on the test input given in the homework and any other test input.
 * Example: Test input of
 * 1
 * 4
 * 0 0 1 0
 * 0 0 1 1
 * 1 1 0 1
 * 0 1 1 0
 * 2
 * 6
 * 2 1 4
 * 3 2 0 5
 * 2 1 3
 * 2 2 4
 * 3 5 0 3
 * 2 4 1
 * 1
 * 6
 * 0 1 0 0 1 0
 * 1 0 1 0 0 1
 * 0 1 0 1 0 0
 * 0 0 1 0 1 0
 * 1 0 0 1 0 1
 * 0 1 0 0 1 0
 * 2
 * 4
 * 1 2
 * 2 2 3
 * 3 0 1 3
 * 2 1 2
 * 0
 * 
 * will print out:
 * 4
 * 1 2 
 * 2 2 3 
 * 3 0 1 3 
 * 2 1 2 
 * 6
 * 0 1 0 0 1 0 
 * 1 0 1 0 0 1 
 * 0 1 0 1 0 0 
 * 0 0 1 0 1 0 
 * 1 0 0 1 0 1 
 * 0 1 0 0 1 0 
 * 6
 * 2 1 4 
 * 3 0 2 5 
 * 2 1 3 
 * 2 2 4 
 * 3 0 3 5 
 * 2 1 4 
 * 4
 * 0 0 1 0 
 * 0 0 1 1 
 * 1 1 0 1 
 * 0 1 1 0
 */
public class Conversion {

	public static int conversionType;
	public static int n;
	public static int p;
	public static int [][] matrix;
	public static int [] list;
	public static int [] printList;
	public static int count;
	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub

		//Scan new file
		Scanner file = new Scanner(new File("conversions.txt"));
										
		//Scan file till no more lines
		while(file.hasNextLine()) {
			
			//Read in conversion type from file
			conversionType = file.nextInt();
			
			//for 1 conversionType, meaning adjacencyMatrix
			if (conversionType == 1) {
				
				//Represents the number of vertices in the graph
				n = file.nextInt();
				System.out.println(n);
				
				int[][] matrix = new int[n][n];
				//Filling matrix with appropriate values
				for (int i=0;i<n;i++) {
					for(int j=0;j<n;j++){
						matrix [i][j] = file.nextInt();
					}
				}
				
				/***For Tracing purposes***/
				//prints out what the matrix looks like
				//System.out.println("The matrix["+n+"]["+n+"] should look like:");
				/*for (int i=0;i<n;i++) {
					for(int j=0;j<n;j++){
						System.out.print(matrix[i][j] + " ");
					}
				System.out.println();
				}*/
				
				list = new int[n];
				count=0;
				//print out array, which is adjacency list
				for (int i=0;i<n;i++) {
					count=0;
					for(int j=0;j<n;j++){
						if (matrix[i][j] == 1) {
							list[count]=j;
							count++;
						}
					}
					System.out.print(count + " ");
					for(int j=0;j<count;j++){
						System.out.print(list[j] + " ");
					}
					System.out.println();
				}
			}
			//for 2 conversionType, meaning adjacencyList
			else if (conversionType == 2) {
				
				//Represents the number of vertices in the graph
				n = file.nextInt();
				System.out.println(n);
				
				int [] printList = new int[n];;
				
				//prints out adjacency matrix
				for (int i=0;i<n;i++) {
				
					p = file.nextInt();
					int[] list = new int[p];
					
					//initializes the entire array to 0 by default
					for (int j=0;j<n;j++) {
						printList[j] = 0;
					}
					
					for(int j=0;j<p;j++){
						list [j] = file.nextInt();
						printList[list[j]] = 1;
					}
	
				//prints out each single array n times	
					for(int j=0;j<n;j++){
						System.out.print(printList[j] + " ");
					}
					System.out.println();
				}
			}
			//for 0 conversionType, meaning end program
			else {
				break;
			}
				
		}//end while
		
		file.close();
	}//end Main
	
}//end Conversion Class
