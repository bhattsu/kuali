package com.kuali;

import java.util.TreeSet;

public class Elevator implements Runnable {
	
	Integer lowestFloorAllowed = null;
	Integer highestFloorAllowed = null;	
	String id = null;
	ElevatorStatus elevatorStatus = null;
	
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
		while(elevatorStatus.getNumberOfRuns() < 10) {			
			try {
				TreeSet<Integer> goToFloors = elevatorStatus.getGoToFloors();
				System.out.println("The Go To Floor Size is " + goToFloors.size());
				if(goToFloors.size() > 0){			
					for(Integer floor: goToFloors){
						moveTo(floor);
						goToFloors.remove(floor);
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
	
	
	public void moveTo(Integer floor) throws InterruptedException {
		synchronized(this){
			elevatorStatus.addGoToFloors(floor);
		}
	}		
	
	public void moveUp(Integer floor) throws InterruptedException {
		while(elevatorStatus.getCurrentFloor() < floor){
			Thread.sleep(2000);
			elevatorStatus.moveUp();
			printStatus();
		}
	}	
	
	public void moveDown(Integer floor) throws InterruptedException {
		while(elevatorStatus.getCurrentFloor() > floor){
			Thread.sleep(2000);
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