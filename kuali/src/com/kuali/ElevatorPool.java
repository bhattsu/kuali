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
		elevator.moveTo(floor);
	}	
	
	public Elevator getClosestElevator(Integer floor){
		Elevator closestElevator = null;
		for(Elevator elevator: elevators){
			ElevatorStatus elevatorStatus = elevator.getElevatorStatus();
			synchronized(elevatorStatus){
				if(closestElevator != null && elevatorStatus.getCurrentFloor().equals(floor)){
					return closestElevator;
				}				
				if(closestElevator == null){
					closestElevator = elevator;
				} else {
					int distance = Math.abs(elevatorStatus.getCurrentFloor() - elevatorStatus.getCurrentFloor());
					if(distance < elevatorStatus.getCurrentFloor()){
						closestElevator = elevator;
					}
				}
			}
		}
		return closestElevator;
	}	
}