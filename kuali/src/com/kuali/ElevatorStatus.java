package com.kuali;

import java.util.TreeSet;

public class ElevatorStatus {
	
	Integer currentFloor = 1;
	Integer numberOfRuns = 0;
	TreeSet<Integer> goToFloors = new TreeSet<Integer>();
	boolean isActive = true;
	

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Integer getCurrentFloor() {
		return currentFloor;
	}
	public void setCurrentFloor(Integer currentFloor) {
		this.currentFloor = currentFloor;
	}
	public Integer getNumberOfRuns() {
		return numberOfRuns;
	}
	public void setNumberOfRuns(Integer numberOfRuns) {
		this.numberOfRuns = numberOfRuns;
	}
	public TreeSet<Integer> getGoToFloors() {
		return goToFloors;
	}
	public void addGoToFloors(Integer goToFloor) {
		this.goToFloors.add(goToFloor);
	}
	
	public void moveUp(){
		currentFloor = currentFloor + 1;
	}
	
	public void moveDown(){
		currentFloor = currentFloor - 1;
	}
	
	public void addToNumberOfRuns(Integer maxNumberOfTrips){ //Make elevator status not active if total runs is 100 as per req
		numberOfRuns = numberOfRuns + 1;
		if(maxNumberOfTrips.equals(numberOfRuns)){
			this.isActive = false;
		}
	}
	
}