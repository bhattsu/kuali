package com.kuali;

import java.util.ArrayList;
import java.util.List;


public class ElevatorPool{
	
	List<Elevator> elevators = new ArrayList<Elevator>();
	
	Integer highestFloor = null;
	Integer totalNumberOfElevators = null;

	
	public ElevatorPool(Integer highestFloor, Integer totalNumberOfElevators) {
		this.highestFloor = highestFloor;
		this.totalNumberOfElevators = totalNumberOfElevators;
	}

	public void start() throws InterruptedException {
		for(int i = 0; i < totalNumberOfElevators; i++){
			Elevator elevator = new Elevator("Elevator" + (i +1),  1, 5);
			Thread thread = new Thread(elevator);
			thread.start();
			elevators.add(elevator);
		}
	}		
}