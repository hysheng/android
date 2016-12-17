package com.hys.al2;

import java.util.ArrayList;
import java.util.List;

public class CoinChange {

	/**
	 * @param args
	 */
	public static int cointNum(int[] coinValue,int totalValue){
		List<Integer> coins=new ArrayList<Integer>();
		coins.add(0);
		int coinNum=0;
		for (int i = 1; i<=totalValue; i++) {
			int coin=nearestCoin(i, coinValue);
			coinNum=coins.get(i-coin)+1;
			coins.add(coinNum);
		}
		System.out.println("----------"+coinNum);
		return coins.get(totalValue);
		
	}
	public static int nearestCoin(int value,int[] coinValues){
		int res=0;
		int nearest=Integer.MAX_VALUE;
		for(int coinValue:coinValues){
			if(coinValue<=value){
				int distance=value-coinValue;
				if(distance<nearest){
					nearest=distance;
					res=coinValue;
				}
			}
		}
		return res;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] coinValue=new int[]{1,2,5,10};
		int totalValue=28;
		int result=cointNum(coinValue, totalValue);
		System.out.println(result);
	}

}
