package com.hys.greedy;

import java.util.List;
import java.util.Scanner;



public class Test {

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
			if(actions[i].getStart()==0){
				actions[i].setStart(24);
			}
		}
		
		actions = GreedySelector.sortByStart(actions);
		List<Action> result = GreedySelector.selectAction(actions);
		System.out.println(result.size());
//		for (int i = 0; i < result.size(); i++) {
//			if(result.get(i).getStart()==24){
//				System.out.println("0-----"
//						+ result.get(i).getEnd());
//			}else {
//			
//				System.out.println(result.get(i).getStart() + "-----"
//						+ result.get(i).getEnd());
//			}
//		}

	}

}
