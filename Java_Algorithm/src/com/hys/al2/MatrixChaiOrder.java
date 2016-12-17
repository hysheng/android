package com.hys.al2;


import java.util.Scanner;

public class MatrixChaiOrder {

	/**
	 * @param args
	 */
	public static int[][] clac(int[] p,int[][] m,int[][] s){
		int length=p.length/2;
		for(int i=0;i<length;i++){
			m[i][i]=0;
		}
		for(int i=1;i<length;i++){
			for(int j=0;j<length-i;j++){
				int r=j+i;
				int t=Integer.MAX_VALUE;
				for(int k=j;k<r;k++){
					int temp=m[j][k]+m[k+1][r]+p[j*2]*p[k*2+1]*p[r*2+1];
					if(t>temp){
						t=temp;
						m[j][r]=temp;
					}
				}
			}
		}
		return m;
	}
	
	public static void printM(int[][] m){
		for(int i=0;i<m.length;i++){
			for (int j = 0; j < m.length; j++) {
				System.out.print(m[i][j]+"\t");
			}
			System.out.println();
		}
	}
	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		//表示有六个矩阵 第一个是30*35
		Scanner scanner=new Scanner(System.in);
		int num=scanner.nextInt();
		int data[]=new int[num*2];
		for(int i=0;i<data.length;i++){
			if(i==0||i==data.length-1){
			data[i]=scanner.nextInt();
			}else{
				int elem=scanner.nextInt();
				data[i]=elem;
				data[++i]=elem;
			}
		}
		int[][] reuslt=new int[num][num];
		int[][] tem=new int[num][num];
		reuslt=clac(data, reuslt, tem);
		System.out.println(reuslt[0][num-1]);
//		printM(reuslt);
//		int p[]={30,35,35,15,15,5,5,10,10,20,20,25};
//		m[i][j]表示第i个矩阵连乘到第j个矩阵的最少乘法数
//		int[][] m=new int[6][6];
//		int[][] s=new int[6][6];
//		m=clac(p, m, s);
//		printM(m);
		
	}

}
