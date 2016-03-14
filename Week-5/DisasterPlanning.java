import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * @author claytonwalker
 * 
 * Clayton Walker
 * COP 3503C Lab
 * Recitation #5
 * Due: 6/27/13
 * DisasterPlanning.java
 */

/**
 * A bridge (or cut edge) is an edge whose removal disconnects the graph. Bridges are important because they represent 
 * a single point of failure in a network. 
 * Brute force: delete edge and check connectivity. Takes O(E(V + E)) time. 
 * Can improve to O(E + V) using clever extension to DFS with Adjacency List
 * Runtime is O(V^2) using DFS with Adjacency Matrix
 * 
 * It will work on the test input given in the homework and any other test input.
 * Example: Test input of
 *9 10
 *0 3
 *0 1
 *1 2
 *2 4
 *1 4
 *4 6
 *4 5
 *5 7
 *5 8
 *8 7
 * 
 * will print out:
 * 0 3
 * 0 1 
 * 4 6
 * 4 5
 */

public class DisasterPlanning {
	
	public static int numVertices;
	public static int numEdges;
	public static int vertexStart;
	public static int vertexEnd;
	public static boolean [][] matrix;

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		//Open the file
		Scanner file = new Scanner(new File("connectivity.txt"));
												
		//Scan file till no more lines
		while(file.hasNextLine()) {
			
			numVertices = file.nextInt();
			numEdges = file.nextInt();
			
			matrix = new boolean[numVertices][numVertices];
			
			//Filling matrix with false
			for (int i=0;i<numVertices;i++) {
				for(int j=0;j<numVertices;j++){
					matrix [i][j] = false;
				}
			}
			
			//Add all the edges in the file together to make a graph
			for(int i=1;i<=numEdges;i++){
				
				vertexStart = file.nextInt();
				vertexEnd  = file.nextInt();
				
				matrix = addEdge(vertexStart,vertexEnd, numVertices);
			}
		}
		
		//Reopens the file
		Scanner file2 = new Scanner(new File("connectivity.txt"));
			
		//Scan file till no more lines
		while(file2.hasNextLine()) {
				
			numVertices = file2.nextInt();
			numEdges = file2.nextInt();
				
			for(int i=1;i<=numEdges;i++){
					
				vertexStart = file2.nextInt();
				vertexEnd  = file2.nextInt();
					
				//For each pair of vertices, delete the edge between them
				matrix = delEdge(vertexStart,vertexEnd, numVertices);
					
				//check if the graph is still connected
				//if it is, then that pair does not have a critical road between them
				//if it's not, then they do have a critical road between them and print it out
				
				//DFS returns the number of Connected components. If it's greater than 1, the graph has a critical road
				//when the edge between these two vertices is deleted.
				if (DFS() > 1) {
					System.out.println(vertexStart + " " + vertexEnd);
				}
				
				//Add the edge back between the two vertices so we can check the next pair	
				matrix = addEdge(vertexStart,vertexEnd, numVertices);
			}
		}
			
	file.close();
	file2.close();
	}//end main


	//Adds an edge between two vertices in the adjacency matrix
	public static boolean[][] addEdge(int vertexStart, int vertexEnd, int numVertices) {
		// TODO Auto-generated method stub
		
		//Filling matrix with true to show edge added between vertexStart and vertexEnd
		for (int i=0;i<numVertices;i++) {
			for(int j=0;j<numVertices;j++){
				if (((i == vertexStart) && (j == vertexEnd)) || ((j == vertexStart) && (i == vertexEnd))) {
					matrix [i][j] = true;
				}
			}
		}
		return matrix;
	}//end addEdge
	
	//Deletes an edge between two vertices in the adjacency matrix
	public static boolean[][] delEdge(int vertexStart, int vertexEnd, int numVertices) {
		// TODO Auto-generated method stub
		
		//Filling matrix with false to show edge deleted between vertexStart and vertexEnd
		for (int i=0;i<numVertices;i++) {
			for(int j=0;j<numVertices;j++){
				if (((i == vertexStart) && (j == vertexEnd)) || ((j == vertexStart) && (i == vertexEnd))) {
					matrix [i][j] = false;
				}
			}
		}
		return matrix;
	}//end delEdge

	// perform a DFS on the graph G
	public static int DFS() {
			boolean[] V=new boolean[numVertices]; // a visited array to mark which vertices have been visited while doing the DFS
			
			int numComponets=0; // the number of components in the graph
			
			// do the DFS from each node not already visited
			for (int i=0; i<numVertices; ++i) {
				if (!V[i]) {
					++numComponets;
					//System.out.printf("Starting a DFS for component %d starting at node %d%n",numComponets,i);
					
					DFSrecursive(i,V);
				}
			}
			
			//System.out.println();
			//System.out.printf("Finished with DFS - found %d components.%n", numComponets);
			//System.out.println();
			return numComponets;
	}//end DFS
		
	// perform a DFS starting at node at (works recursively)
	static void DFSrecursive(int at, boolean[] V) {
		//System.out.printf("At node %d in the DFS%n",at);
			
			// mark that we are visiting this node
			V[at]=true;
			
			// recursively visit every node connected to this that we have not already visited
			for (int i=0; i<numVertices; ++i) {
				if (matrix[at][i] && !V[i]) {
					//System.out.printf("Going to node %d...",i);
					DFSrecursive(i,V);
				}
			}
			
			//System.out.printf("Done processing node %d%n", at);
	}//end DFSrecursive
		
}//end DisasterPlanning Class
