package com.kuali;

import java.util.Scanner;

public class ElevatorSimulator{
	
	//public final static long sleepTimeBeforeButtonClickAgain = 5000;
	
	public static void main(String args[]) throws InterruptedException {
		
		Scanner scanner = new Scanner (System.in);
		System.out.print("Highest floor: ");  
		Integer highestFloor = Integer.valueOf(scanner.next());
		
		System.out.print("Total number of elevators: ");  
		Integer totalNumberOfElevators = Integer.valueOf(scanner.next());
		
		ElevatorPool elevatorPool = new ElevatorPool(highestFloor, totalNumberOfElevators);
		elevatorPool.start();
		
	
		System.out.println("** Elevators initialized. Time to move up and down **");
		while(true){
			System.out.print("Which floor do you want to go to ? ");
			Integer gotoFloor = Integer.valueOf(scanner.next());
			elevatorPool.buttonPressed(gotoFloor);
			//Thread.sleep(sleepTimeBeforeButtonClickAgain);
		}
	}
}