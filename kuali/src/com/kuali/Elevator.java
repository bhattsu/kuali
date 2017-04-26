package com.kuali;

import java.util.TreeSet;

public class Elevator implements Runnable {
	
	Integer lowestFloorAllowed = null;
	Integer highestFloorAllowed = null;	
	String id = null;
	ElevatorStatus elevatorStatus = null;
	Integer maxNumberOfTrips = 100;
	
	
	
	public Elevator(String id, Integer lowestFloorAllowed, Integer highestFloorAllowed) {
		this.id = id;
		this.lowestFloorAllowed = lowestFloorAllowed;
		this.highestFloorAllowed = highestFloorAllowed;
		elevatorStatus = new ElevatorStatus();
	}
	
	public ElevatorStatus getElevatorStatus() {
		return elevatorStatus;
	}

	public void setElevatorStatus(ElevatorStatus elevatorStatus) {
		this.elevatorStatus = elevatorStatus;
	}
		


	@Override
	public void run() {
		while(elevatorStatus.getNumberOfRuns() < maxNumberOfTrips) { //This will make sure you can only have 100 max trips before service will be needed		
			try {
				TreeSet<Integer> goToFloors = elevatorStatus.getGoToFloors(); //Sorted list can have multiple floors queued			
				if(goToFloors.size() > 0){			
					System.out.println("Yey! now moving " + goToFloors.size());
					for(Integer floor: goToFloors){
						moveTo(floor);
						goToFloors.remove(floor);
						elevatorStatus.addToNumberOfRuns(maxNumberOfTrips);
					}	
				} else {
					//No request so go to sleep for some time.
					Thread.sleep(2000);
				}
			}			
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}	
	
	
	public void addGoToFloors(Integer floor) throws InterruptedException {
		synchronized(this){
			elevatorStatus.addGoToFloors(floor);
		}
	}		
	
	public void moveTo(Integer floor) throws InterruptedException {
		synchronized(this){
			if(elevatorStatus.getCurrentFloor() < floor){
				moveUp(floor);				
			} else {
				moveDown(floor);
			}
		}
	}		
	
	public void moveUp(Integer floor) throws InterruptedException {
		while(elevatorStatus.getCurrentFloor() < floor){
			Thread.sleep(2000); //Assuming it takes 2 seconds to move up one floor
			elevatorStatus.moveUp();
			printStatus();
		}
	}	
	
	public void moveDown(Integer floor) throws InterruptedException {
		while(elevatorStatus.getCurrentFloor() > floor){
			Thread.sleep(2000);  //Assuming it takes 2 seconds to move down one floor
			elevatorStatus.moveDown();
			printStatus();
		}
	}	
		
	
	public void printStatus(){
		StringBuffer sb = new StringBuffer();
		sb.append(id);
		sb.append(" is active and currently in floor: ");				
		sb.append(elevatorStatus.getCurrentFloor());
		System.out.println(sb.toString());
	}
}