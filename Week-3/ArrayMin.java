import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;
/**
 * @author claytonwalker
 * 
 * Clayton Walker
 * COP 3503C Lab
 * Recitation #3
 * Due: 6/13/13
 * ArrayMin.java
 */

/**
 * Runtime of ArrayMin algorithm is in O(n) time complexity because we are going through the whole array in the worst case. 
 * It will work on the test input given in the homework and any other test input.
 * Example: Test input of
 * 50 100 50 6 7 4
 * 46 47 94 30 21
 * 30 34 300 30000 3
 * 
 * will print out:
 * 4
 * 21
 * 3
 */
public class ArrayMin {

	public static int count;
	public static int[] A;
	public static String input;
	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub

		//Scan new file
		Scanner file = new Scanner(new File("arraymin.in.txt"));
						
		/***In case that first one doesn't work, comment that line out, and uncomment this one please***/
		//Scanner file = new Scanner(new File("arraymin.in"));

		//while there are lines left in the file
		while (file.hasNextLine()) {
		
			//read in line separated by spaces
			String input = file.nextLine();
		    StringTokenizer strToken = new StringTokenizer(input);
		    //count how many tokens we read in, this will be the number of indexes in the array
		    int count = strToken.countTokens();
		    //Reads in the numbers to the array
		    //System.out.println("Count: " + count);
		    int[] A = new int[count];
		    
		    //for each index in the array, parse the string separated by spaces into an int array called A[]
		    for(int i = 0;i < count;i++){
		        A[i] = Integer.parseInt((String)strToken.nextElement());
		        //System.out.println("A["+i+"] is " + A[i]);
		    }
		    
		    //Send A[] to method minVal that will return the smallest value in the array to be printed out
		    System.out.println(minVal(A));
		}
		file.close();
	}//end Main

	//Find minimum (lowest) value in array using loop  
	public static int minVal(int[] numbers){  
	    
		int minValue = numbers[0];  
	    
		//go through the entire array until you find the minimum value
		for(int i=1;i<numbers.length;i++){  
			//updating the new minimum value each time by comparison
			if(numbers[i] < minValue){  
	            minValue = numbers[i];  
	        }  
	    }  
	    return minValue;  
	} //end minVal method
	
}//end ArrayMin class
