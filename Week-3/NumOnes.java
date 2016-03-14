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
 * NumOnes.java
 */

/**
 * Runtime of NumOnes algorithm is in O(log n) time complexity because we are continually dividing by 2. 
 * It will work on the test input given in the homework and any other test input.
 * Example: Test input of
 * 50
 * 1
 * 3
 * 17
 * 
 * will print out:
 * 3
 * 1
 * 2
 * 2
 */
public class NumOnes {

	public static int n;
	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		//Scan new file
		Scanner file = new Scanner(new File("numones.in.txt"));
				
		/***In case that first one doesn't work, comment that line out, and uncomment this one please***/
		//Scanner file = new Scanner(new File("numones.in"));
		
		//read file until it ends
		while (file.hasNextInt()) {
			
			//each integer is n, which is read in, and then sent to the recursive method numOnes
			int n = file.nextInt();
			//Prints out correct number of 1's in binary representation of n.
			System.out.println(numOnes(n));
		}
		file.close();
	}//end Main
	
	//Precondition: n > 0
	public static int numOnes(int n) {
		
		//Base case
		if (n==0) {
			return 0;
		}
		//If n is even, divide by 2 and keep going
		else if (n%2==0) {
			return numOnes(n/2);
		}
		//If n is odd, add 1 and call recursively again
		else {
			return numOnes(n - 1) + 1;
		}
	}//end numOnes method

}//end NumOne class
