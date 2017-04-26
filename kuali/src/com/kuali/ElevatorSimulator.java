package com.kuali;

import java.util.Scanner;

public class ElevatorSimulator{
	
	public final static long sleepTimeBeforeButtonClickAgain = 5000;
	
	public static void main(String args[]) throws InterruptedException {
		
		Scanner scanner = new Scanner (System.in);
		System.out.print("Enter number of floors: ");  
		Integer numberOfFloors = Integer.valueOf(scanner.next());
		
		System.out.print("Total number of elevators: ");  
		Integer totalNumberOfElevators = Integer.valueOf(scanner.next());
		
		scanner.close();
		while(true){
			System.out.print("Enter number of floors: ");  			
			Thread.sleep(sleepTimeBeforeButtonClickAgain);
		}
	}
}