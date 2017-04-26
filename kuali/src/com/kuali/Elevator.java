package com.kuali;

public class Elevator implements Runnable {
	
	Integer lowestFloorAllowed = 1;
	Integer highestFloorAllowed = null;	
	String id = null;
	ElevatorStatus elevatorStatus = null;
	
	public Elevator(String id, Integer lowestFloorAllowed, Integer highestFloorAllowed) {
		this.id = id;
		this.lowestFloorAllowed = lowestFloorAllowed;
		this.highestFloorAllowed = highestFloorAllowed;
		elevatorStatus = new ElevatorStatus();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}	
	
	public void broadcastStatus(){
		StringBuffer sb = new StringBuffer();
		sb.append(id);
		sb.append(" is active and currently in ");
		synchronized(elevatorStatus){
			sb.append(elevatorStatus.getCurrentFloor());
		}		
		sb.append(" floor");
		System.out.println(sb.toString());
	}	
}