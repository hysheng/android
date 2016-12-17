package com.hys.al2;

import java.util.Scanner;

public class MatrixP {

	/**
	 * @param args
	 * hugh的矩阵连乘
	 */
	public static int[] getData() {
		Scanner scanner = new Scanner(System.in);
		int num = scanner.nextInt();
		int data[] = new int[num * 2];
//		例如int data[]={30,35,35,15,15,5,5,10,10,20,20,25};

		for (int i = 0; i < data.length; i++) {
			if (i == 0 || i == data.length - 1) {
				data[i] = scanner.nextInt();
			} else {
				int elem = scanner.nextInt();
				data[i] = elem;
				data[++i] = elem;
			}
		}
		return data;
	}

	public static int getMactrixProduct(int[] data) {
//		m[i][j]表示第i个矩阵连乘到第j个矩阵的最少乘法数
		int length = data.length / 2;
		int[][] result = new int[length][length];
		for (int i = 0; i < length; i++) {
			result[i][i] = 0;
		}
		for (int i = 1; i < length; i++) {
			for (int j = 0; j < length - i; j++) {
				int p = j + i;
				int flag = Integer.MAX_VALUE;
				for (int k = j; k < p; k++) {
					int temp = result[j][k] + result[k + 1][p] + data[j * 2]
							* data[k * 2 + 1] * data[p * 2 + 1];
					if (flag > temp) {
						flag = temp;
						result[j][p] = temp;
					}
				}
			}
		}
		return result[0][length - 1];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] data = getData();
		int productNum = getMactrixProduct(data);
		System.out.println(productNum);
	}

}
