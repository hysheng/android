package com.hys.greedy;

public class Action {
	private int start;
	private int end;
	
	public Action(){
		
	}
	public Action(int start,int end){
		this.start=start;
		this.end=end;
	
	}
	public void setStart(int start){
		this.start=start;
	}
	public int getStart(){
		return this.start;
	}
	public void setEnd(int end){
		this.end=end;
	}
	public int getEnd(){
		return this.end;
	}

}
