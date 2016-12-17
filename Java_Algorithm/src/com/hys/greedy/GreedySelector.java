package com.hys.greedy;

import java.util.ArrayList;
import java.util.List;



public class GreedySelector {
	
	
	
	//以活动的开始时间为关键字进行排序
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
