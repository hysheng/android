package com.hys.base;

public class Test {

	/**
	 * @param args
	 */
	String str = new String("good");
	Integer integer=2;
	char[] ch = { 'a', 'b', 'c' };

	public static void main(String args[]) {
		Test ex = new Test();
		System.out.print(ex.str + " 1 "+ex.integer+" ");
		System.out.println(ex.ch);
		ex.change(ex, ex.ch,ex.integer);
		System.out.print(ex.str + " and "+ex.integer+" ");
		System.out.print(ex.ch);
	}

	public void change(Test str, char ch[],Integer integer) {
		integer=5;
		str.str = "test ok";
		ch[0] = 'g';
	}
}
