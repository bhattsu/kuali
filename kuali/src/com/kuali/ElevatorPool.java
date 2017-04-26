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
			Elevator elevator = new Elevator("Elevator" + (i +1),  1, highestFloor);
			Thread thread = new Thread(elevator);
			thread.start();
			elevators.add(elevator);
			elevator.printStatus();
		}
	}	
	
	public void buttonPressed(Integer floor) throws InterruptedException{
		Elevator elevator = getClosestElevator(floor);
		elevator.printStatus();
		elevator.addGoToFloors(floor);
	}	
	
	public Elevator getClosestElevator(Integer floor){ 
		Elevator closestElevator = null;
		
		for(Elevator elevator: elevators){
			if(!elevator.isActive()){ continue; } //Some elevators may go to servicing so they become inactive
			
			ElevatorStatus elevatorStatus = elevator.getElevatorStatus();
			
			if(closestElevator != null && elevatorStatus.getCurrentFloor().equals(floor)){
				return closestElevator;
			}				
			if(closestElevator == null){
				closestElevator = elevator;
			} else {
				ElevatorStatus closestElevatorStatus = closestElevator.getElevatorStatus();
				
				int oldDistance = closestElevatorStatus.getCurrentFloor() - floor;	
				int newDistance = elevatorStatus.getCurrentFloor() - floor;	
				
				if(oldDistance  < 0){ oldDistance = oldDistance * -1; }
				if(newDistance  < 0){ newDistance = newDistance * -1; }

				if(newDistance < oldDistance){ //Unfortunately due to time constraint this does not take into account which direction it is moving..That logic has to be added. 
												//Right now it just goes by distance.
					closestElevator = elevator;
				}
			}
		}
		
		return closestElevator;
	}	
}