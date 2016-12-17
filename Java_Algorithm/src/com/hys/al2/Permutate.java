package com.hys.al2;

public class Permutate {

	/**
	 * @param args
	 * È«ÅÅÁĞ
	 */
	private static int count=0;
	public static void swap(String[] str,int i,int j){
		String temp=str[i];
		str[i]=str[j];
		str[j]=temp;
	}
	public static void per(String[] str,int st,int len){
		if(st==len-1){
			for(int i=0;i<len;i++){
				System.out.print(str[i]+" ");
			}
			System.out.println();
			count++;
		}else{
			for(int i=st;i<len;i++){
				swap(str, st, i);
				per(str, st+1, len);
				swap(str, st, i);
			}
		}
			
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num=4;
		String[] str=new String[4];
		for(int i=0;i<num;i++){
			str[i]=""+(i+1);
		}
		for(int i=0;i<str.length;i++){
			System.out.print(str[i]+" ");
		}
		System.out.println();
		per(str, 0, str.length);
		System.out.println(count);
	}

}
