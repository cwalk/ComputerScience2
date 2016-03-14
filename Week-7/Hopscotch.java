import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import static java.lang.Math.*;
/**
 * @author claytonwalker 
 * 
 * Clayton Walker
 * COP 3503C Lab
 * Recitation #7
 * Due: 7/11/13
 * Hopscotch.java
 */

/**
 * It will work on the test input given in the homework and any other test input.
 * Example: Test input of
 *5
 *1
 *16
 *7
 *39
 *98
 * 
 * will print out:
 * Game #1: 1
 * Game #2: 12
 * Game #3: 5
 * Game #4: 21
 * Game #5: 48
 * 
 * 
 * Pseudo Code Explanation:
 * 
 * I take the Dynamic Programming approach. First I read in the number of cases, and in a loop read in the gameOrder.
 * 
 * for each test case: 
 *  F(gameOrder); 
 *  
 *  My F(k) function is a recursive function that defines the minimum score if you start from square k.
 *  I look at the (up to four) possible choices I have for the move. 
 *  
 *  I then express the value of F(k) in terms of the F-score for the squares you would land on if you made each possible valid move, 
 *  keeping in mind that I need the smallest possible score.
 *  
 *  F(k) is a recursive implementation. I hen memoize it using an array of ints, called Farray, after I define my base cases.
 *  
 *  F(k) {
 *  
 *  if (k==0) //base case for 0
 *  	return 0;
 *  
 *  Check if value is already inside Farray, if it is return that value, if not keep going
 *  
 *  4 checks, one for each possible move. 
 *  
 *  Each check is an if statement, checking if it is a -1 move, multiple of 7, multiple of 11, or prime greater than 10. 
 *  
 *  For each move, it recursively calls itself, to tell if it is the best option, while adding up the score.
 *  It returns the minimum score for each of the 4 possible moves. I then take the minimum value of that "move" and do two things:
 *  -update that value into the Farray
 *  -and return it to main to be printed out.
 *  }
 */



public class Hopscotch {
		
	public static int numTestCases;
	public static int gameOrder;
	public static int minimumScore;
	public static int[] Farray;

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
			
		//Open the file
		Scanner file = new Scanner(new File("hopscotch.in.txt"));
													
		//read in the number of Test Cases
		numTestCases = file.nextInt();
				
		//for each Test Case
		for (int i=1;i<=numTestCases;i++) {
			
			//read in the Game Order
			gameOrder = file.nextInt();
			
			//initialize the score for each game
			minimumScore = 0;
			
			//initialize the Farray to 0s
			Farray = new int[gameOrder+1];
			
			for(int j=0;j<gameOrder+1;j++) {
				Farray[j] = 0;
			}
			
			//Call F(k) function to computer minimumScore
			minimumScore = F(gameOrder);
			
			//print statements
			System.out.printf("Game #" + i + ": " + minimumScore);
			System.out.println();
		}	
	file.close();
	}//end Main

	//Method to tell if a number is prime or not
	public static boolean isPrime(int n) {
		// TODO Auto-generated method stub
		
		//check if n is a multiple of 2
	    if (n%2==0) return false;
	    //if not, then just check the odds
	    for(int i=3;i<=Math.sqrt(n);i+=2) {
	        if(n%i==0)
	        	//returns false if not prime
	        	return false;
	    }
	    //returns true if prime
	    return true;
	}
	
	//Method that takes care of the additions of the digits for %11 case
	public static int mod11(int k) {
		// TODO Auto-generated method stub
		int sum = 0;
		
		while(k!=0) {
			sum = k%10;
			return sum + mod11(k/10);
		}
		return sum;
	}
	
	//Mehtod that returns true if a value was found in the array, false if not
	public static boolean contains(int[] array, int key) {
	        for (int i : array) {
	            if (i == key) {
	                return true;
	            }
	        }
	        return false;
	}
	
	//Recursive method that memiozes values, includes 4 possible moves
	public static int F(int k) {
		// TODO Auto-generated method stub
		
		//base case
		if (k==0) {
			return 0;
		}
		
		//if k is in Farray, we return that value, otherwise we continue with checking each 4 possible moves
		if (contains(Farray, k)==true) {
			return Farray[k]; 
		}
		
		//initializes all values to MAX, so it's not confused as minimum value 
		int val1 = Integer.MAX_VALUE;
		int val2 = Integer.MAX_VALUE;
		int val3 = Integer.MAX_VALUE;
		int val4 = Integer.MAX_VALUE;
		
		
		//first case for always k-1
		if (k!=0) {
			val1 = 1 + F(k-1);
		}
		
		//second case for prime and >10
		if ((k>10) && (isPrime(k)==true)) {
			val3 = 3 + F(k-(k%10));
		}
		//third case for %11
		if (k%11==0) {
			val4 = 4 + F(k-(mod11(k)));
		}
		//fourth case for %7
		if (k%7==0) {
			val2 = 2 + F(k-4);
		}
		
		//take the minimum of all 4 values
		int min1 = min(val1, val2);
		int min2 = min(val3, val4);
		
		//input that into the Farray to memioze it, and return that value as well, to be printed out
		Farray[k] = min(min1, min2);
		return min(min1, min2);
	}//end F(k)
}//end Hopscotch class
