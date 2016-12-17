package com.hys.al;

import java.util.Scanner;

public class AlgorithmService {
	static int count = 0;
	static int num = 0;

	public static int getData() {

		Scanner scanner = new Scanner(System.in);
		int data = scanner.nextInt();
		return data;
	}

	public static void hanoi(int n, String a, String b, String c) {

		if (n == 1) {
			// System.out.println("第"+(++count)+"次移动:"+a+"棒>>>>>>>"+c+"棒");
			
		} else {
			hanoi(n - 1, a, c, b);
			// System.out.println("第"+(++count)+"次移动:"+a+"棒>>>>>>>"+c+"棒");
			
			hanoi(n - 1, b, a, c);
		}
	}

	public static int hanoiStepNum() {
		return num;
	}

}
