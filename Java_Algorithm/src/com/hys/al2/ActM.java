package com.hys.al2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ActM {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		int length = scanner.nextInt();
		Action[] actions = new Action[length];
		for (int i = 0; i < length; i++) {
			actions[i] = new Action(scanner.nextInt(), scanner.nextInt());
			if (actions[i].getStart() == 0) {
				actions[i].setStart(24);
			}
		}

		actions = sortByStart(actions);
		List<Action> result = selectAction(actions);
		System.out.println(result.size());
	}
	public static Action[] sortByStart(Action[] array){
		int out,in,min;
		for(out=0;out<array.length-1;out++){
			min=out;
			for(in=out+1;in<array.length;in++)
				if(array[in].getStart()<array[min].getStart())
					min=in;
			swap(out	, min, array);
		}
		
		return array;
	}
	private static void swap(int one,int two,Action[] array){
		Action temp=array[one];
		array[one]=array[two];
		array[two]=temp;
	}
	public static List<Action> selectAction(Action[] actions){
		int n=actions.length;
		List<Action> list=new ArrayList<Action>();
		int flag_start;
		int flag_end=actions[0].getEnd();
		list.add(actions[0]);
		for(int i=0;i<n;++i){	
			flag_start=actions[i].getStart();
			if(flag_start>=flag_end){
				flag_end=actions[i].getEnd();
				list.add(actions[i]);
				
			}
			
		}
		return list;
	}

}
 class Action {
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

