import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * @author claytonwalker
 * 
 * Clayton Walker
 * COP 3503C Lab
 * Recitation #2
 * Due: 6/6/13
 * array.java
 */
public class array {
	
	//initializing awesome variables
	public static int k = 0;
	public static int i = 1;
    public static int n = 0;
    public static int T = 0;
    public static int[] array;
	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		//Scan new file
		Scanner file = new Scanner(new File("array.in.txt"));
		
		/***In case that first one doesn't work, comment that line out, and uncomment this one please***/
		//Scanner file = new Scanner(new File("array.in"));
		
		//variable k is for representing number of test cases in file
		int k = file.nextInt();
		
		//loop goes through each test case
		for (i=1; i<=k; i++) {

			//variable n is for the array length, and initialize an array of ints of size n
			int n = file.nextInt();
			int[] array = new int[n];
			
			//for loop goes through each index in that array for each test case, and assigns values to it correctly
			for (int j=0;j<array.length;j++) {
				array[j] = file.nextInt();
			}//end j loop representing a fully filled array
			
			//variable T is the Target value
			int T = file.nextInt();

			//Calls O(n) method created below. Prints SUCCESS or FAIL determined by boolean returned by method
			if (targetSumMethod(array, T, i) == true) {
				System.out.println("Test case #" + i + ": The target " + T + " is achievable.");
			}
			else {
				System.out.println("Test case #" + i + ": The target " + T + " is NOT achievable.");
			}
		}//end i loop representing each test case
	file.close();
	}//end Main

	/**
	 * O(n) runtime Argument 
	 *
	 *The method created takes advantage that the array is sorted. 
	 *If we create a low index and a high index for the array, we can
	 *essentially keep a pointer at the beginning and end of the array, called low and high.
	 *
	 *Since it is sorted, if adding the values at the low and high index is 
	 *smaller than the Target value, then you must increment the low index at the beginning, because
	 *that low index cannot be added to anything in the array and still = the Target value.
	 *It will always be smaller no matter what.
	 *
	 *Similarly, if adding the values at the low and high index is bigger than the Target value,
	 *then we can simply decrease the index of the high index at the end of the array, because this end value is the biggest,
	 *and when added to the smallest value at the beginning is bigger than the Target, it cannot be added to anything in the list and
	 *still = the Target.
	 *
	 *If the low index and high index overlap, then we simply use the FAIL case, because there are no two numbers
	 *in the array list that can be added and = the Target value. If they were found before this, then the SUCCESS
	 *case would print out the correct message to the screen.
	 */
	
	//Method to take care of algorithm in O(n) runtime.
	public static boolean targetSumMethod(int[] array, int T, int i) {
		
		//Initializing low and high indexes at each end of the array of ints
	    int low = 0, high = array.length-1;
	    
	    //This while loop goes through each array of ints and indexes while the lower end index 
	    //is still less than the higher index on the other side. If they over lap it breaks.
	    while (low < high) {
	    	//If the arrays lowest index + highest index is less than T, increment the low index
	    	if (array[low] + array[high] < T) {
	        	low++;
	        }
	        //If the arrays lowest index + highest index is greater than T, increment the high index  
	    	else if (array[low] + array[high] > T) {
	        	high--;
	        }
	        //Print out success statement
	    	else {
	    		return true;
	    	}
	    }
	    //Print out fail statement
	    return false;
	}//end targetSumMethod
}//end array class
				   