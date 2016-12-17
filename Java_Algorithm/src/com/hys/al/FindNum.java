package com.hys.al;

import java.util.Scanner;

public class FindNum {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int length;
		Scanner scanner = new Scanner(System.in);
		length=scanner.nextInt();
		int flag = scanner.nextInt();
		int data[]=new int[length];
		for (int i = 0; i < length; i++) {
			data[i] = scanner.nextInt();
		}
		
		System.out.println(findIntByBig(data, flag));

	}

	public static int findIntByBig(int[] bdata, int location) {
		int[] data = descendingSort(bdata);
		return data[location - 1];
	}
	public static int[] descendingSort(int[] bdata) {
		int[] data = bdata;
		int temp;
		for (int i = 0; i < data.length - 1; i++) {
			for (int j = i + 1; j < data.length; j++) {
				if (data[i] > data[j]) {
					temp = data[j];
					data[j] = data[i];
					data[i] = temp;
				}
			}
		}
		return data;
	}
}
