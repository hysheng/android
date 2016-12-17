package com.hys.al;

import java.util.Scanner;

public class Hanoi {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(System.in);
		int data=scanner.nextInt();
		hanoi(data, "a","c","b");

	}
	public static void hanoi(int n, String a, String b, String c) {

		if (n == 1) {
			 System.out.println(a+"-->"+c);
			
		} else {
			hanoi(n - 1, a, c, b);
			System.out.println(a+"-->"+c);
			hanoi(n - 1, b, a, c);
		}
	}

}
