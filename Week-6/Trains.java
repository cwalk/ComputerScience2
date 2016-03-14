import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * @author claytonwalker
 * 
 * Clayton Walker
 * COP 3503C Lab
 * Recitation #6
 * Due: 7/4/13
 * Trains.java
 */

/**
 * It will work on the test input given in the homework and any other test input.
 * Example: Test input of
 *1
 *1
 *10 20
 *2
 *10 30
 *20 40
 *1
 *45 50
 *1
 *55 60
 *1
 *60 65
 *1
 *65 70
 *2
 *70 75
 *72 74
 *1
 *74 80
 *1
 *81 90
 *1
 *92 100
 * 
 * will print out:
 * Train 1: Arrive at station #10 in 100 minutes with 549 penalty points.
 */

public class Trains {
	
	public static int numMinutes;
	public static int numPenaltyPoints;
	public static int numSchedules;
	public static int numTrains;
	public static int startTime;
	public static int endTime;
	public static int previousStartTime;
	public static int previousEndTime;
	public static int tempStartTime;
	public static int tempEndTime;
	public static int errorFlag;
	public static int change;

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		//Open the file
		Scanner file = new Scanner(new File("trains.in.txt"));
												
		//read in the number of schedules
		numSchedules = file.nextInt();
		//System.out.println("numSchedules is: " + numSchedules);
			
		//for each train schedule
		for (int i=1;i<=numSchedules;i++) {
				
			//resets variables to 0
			numMinutes = 0;
			numPenaltyPoints = 0;
			numTrains = 0;
			errorFlag = 0;
			startTime = 0;
			endTime = 0;
			tempStartTime = 0;
			tempEndTime = 0;
			change = 0;
			previousStartTime = 0;
			previousEndTime = 0;
			
			//for each station
			for (int j=0;j<10;j++) {
					
				//System.out.println("For station #" + j + " to station #" + (j+1));
				
				//reads in the number of trains for each station
				numTrains = file.nextInt();
				//System.out.println("numTrains is: " + numTrains);
				
				//for each train at that station
				for (int k=1;k<=numTrains;k++) {
					
					//read in the start and end time as a temp
					tempStartTime = file.nextInt();
					tempEndTime = file.nextInt();
					
					//keeps record of decided final start and end time of previous station
					if (k==1) {
						previousStartTime = startTime;
						previousEndTime = endTime;
						//System.out.println("Train number " + k + " now has: previousStartTime " + previousStartTime + " and previousEndTime " + previousEndTime);
					}
					
					//System.out.println("Train number " + k + " has: tempStartTime " + tempStartTime + " and tempEndTime " + tempEndTime);

					
					//If the start of the next train was earlier than we arrived, skip it
					if ((tempStartTime < endTime)) {
						//Sets error flag if we cannot get to station 10, due to us arriving at a station with no available trains left to take!
						if ((tempEndTime > endTime)) {
							if (k == numTrains && change == 0) {
								errorFlag = 1;
								//System.out.println("errorFlag is being set to 1 here! This happens at station #" + j + " to station #" + (j+1));
							}
						}
						else if (tempStartTime < previousEndTime) {
							//System.out.println("Doing nothing");
						}
						else {
							//System.out.println("LOL tempStartTime is greater than or equal to previous endTime so");
							startTime = tempStartTime;
							endTime = tempEndTime;
							change = 1;
						}
					}
					//other wise, we update this as our new taken train, and start and end times are updated accordingly 
					else {
						
						//System.out.println("tempStartTime is greater than or equal to previous endTime so");
						startTime = tempStartTime;
						endTime = tempEndTime;
						change = 1;
					}
					//System.out.println("Train number " + k + " has: start time " + startTime + " and end time " + endTime);	
				}
				//Reset to 0 after every station. This holds whether we have updated our start or end time for a particular station. 
				//In other words, if we COULD take a train, we choose that, and then look at the others. If there is a GREEDIER choice,
				//then we update our start and end times and take that train.
				change = 0;
				
				numMinutes = endTime;
				//System.out.println("numMinutes is: " + numMinutes);
				
				numPenaltyPoints += endTime;
				//System.out.println("numPenaltyPoints is: " + numPenaltyPoints);
			}
			//subtracts the last endTime from station #9-#10, as the only penalty points that count are from stations #1-#9
			numPenaltyPoints -= endTime;
			
			System.out.printf("Train " + i + ": ");
			
			if (errorFlag==1) {
				System.out.println("Station #10 is NOT reachable.");
			}
			else {
				System.out.println("Arrive at station #10 in " + numMinutes + " minutes with " + numPenaltyPoints + " penalty points.");
			}
		}	
	file.close();
	}//end Main
}//end Trains class