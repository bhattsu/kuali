package com.kuali;

import java.util.TreeSet;

public class ElevatorStatus {
	
	Integer currentFloor = 1;
	Integer numberOfRuns = 0;
	TreeSet<Integer> goToFloors = new TreeSet<Integer>();
	
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
	public void setGoToFloors(TreeSet<Integer> goToFloors) {
		this.goToFloors = goToFloors;
	}
	
	public void moveUp(){
		currentFloor = currentFloor + 1;
	}
	
	public void moveDown(){
		currentFloor = currentFloor - 1;
	}
	
}