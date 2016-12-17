package com.hys.al2;

import java.util.Scanner;

public class Bag {
	static int capacity;
	static int number;
	static int[] weight;
	static int[] value;
	static int cw;
	static int cv;
	static int bestValue;

	public static void main(String args[]) {
		/*第一行输入两个整数：number和capacity
		 * 第二行number个整数分别是weight1....weightn
		 * 第三行是number个物品的价值
		 */
		Scanner s = new Scanner(System.in);
		while (s.hasNext()) {
			String line = s.nextLine();//读入容量capacity和物品的数量number
			String[] sArray = line.split(" ");
			capacity = Integer.parseInt(sArray[0]);
			number= Integer.parseInt(sArray[1]);
			if (number == 0 && capacity == 0)
				return;
			if (number == 0) {
				System.out.println(0);
				continue;
			}

			line = s.nextLine();// 读入w的行
			sArray = line.split(" ");
			weight = new int[sArray.length];
			for (int j = 0; j < sArray.length; j++) {
				weight[j] = Integer.parseInt(sArray[j]);
			}

			line = s.nextLine();// 读入p的行
			sArray = line.split(" ");
			value = new int[sArray.length];
			for (int j = 0; j < sArray.length; j++) {
				value[j] = Integer.parseInt(sArray[j]);
			}
			System.out.println((Knapsack(value, weight, capacity)));
		}

	}// Main

	private static class Element implements Comparable {
		int id;// 物品编号
		double d;

		private Element(int idd, double dd) {
			id = idd;
			d = dd;
		}

		public int CompareTo(Object x) {
			double xd = ((Element) x).d;
			if (d < xd)
				return -1;
			if (d == xd)
				return 0;
			return 1;
		}

		public boolean equals(Object x) {
			return d == ((Element) x).d;
		}

		public int compareTo(Object arg0) {
			// TODO Auto-generated method stub
			return 0;
		}
	}// Element

	
	public static int Knapsack(int[] pp, int[] ww, int cc) {
		capacity = cc;
		number = pp.length - 1;
		// n=pp.length;
		cw = 0;
		cv = 0;
		bestValue = 0;
		Element[] q = new Element[number + 1];

		// for(int i=1;i<=n;i++)
		// q[i-1]=new Element(i,pp[i]/ww[i]);
		for (int i = 0; i <= number; i++)
			q[i] = new Element(i, pp[i] / ww[i]);
		// 对q[]从大到小排序
		for (int m = 0; m < q.length; m++) {
			for (int j = m + 1; j < q.length; j++) {
				if (q[m].d < q[j].d) {
					// int temp=m;
					// m=j;
					// j=temp;
					int temp1 = q[m].id;
					q[m].id = q[j].id;
					q[j].id = temp1;

					double temp2 = q[m].d;
					q[m].d = q[j].d;
					q[j].d = temp2;
				}
			}
		}

		value = new int[number + 1];
		weight = new int[number + 1];

		for (int j = 0; j <= number; j++) {
			value[j] = pp[q[j].id];
			weight[j] = ww[q[j].id];
		}

		backtrack(0);
		return bestValue;
	}// knapsnack

	static void backtrack(int i) {
		if (i > number) {
			bestValue = cv;
			return;
		}
		if (cw + weight[i] <= capacity) {
			cw += weight[i];
			cv += value[i];
			backtrack(i + 1);
			cw -= weight[i];
			cv -= value[i];
		}
		if (bound(i + 1) > bestValue)
			backtrack(i + 1);
	}// backtrack

	private static double bound(int i) {
		double cleft = capacity - cw;
		double bound = cv;

		while (i <= number && weight[i] <= cleft) {
			cleft -= weight[i];
			bound += value[i];
			i++;
		}
		if (i <= number)
			bound += value[i] * cleft / weight[i];
		return bound;
	}// bound
}