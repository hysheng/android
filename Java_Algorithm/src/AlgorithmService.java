
import java.util.Scanner;

public class AlgorithmService {
	static int count=0;
	static int num=0;
	
	// 获得输入的数据
	public static int[] getData() {
		int data[] = new int[7];
		Scanner scanner = new Scanner(System.in);
		for (int i = 0; i < 7; i++) {
			data[i] = scanner.nextInt();
		}
		return data;
	}

	// 求n个数的和
	public static int sum(int[] data) {
		int result = 0;
		for (int i = 0; i < data.length; i++) {
			result += data[i];
		}
		return result;
	}
	//从n个数求最大值
	public static int getMax(int[] data) {
		int max = 0;
		for (int i = 0; i < data.length; i++) {
			if (max < data[i]) {
				max = data[i];
			}
		}
		return max;
	}
	//n的阶乘
	public static int getFactorial(int n) {
		int result = 1;
		for (int i = 1; i <= n; i++) {
			result *= i;
		}
		return result;
	}

	// 升序排序
	public static int[] ascendingSort(int[] bdata) {
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

	// 降序排序
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

	// 查找第location小的数据
	public static int findIntBySmall(int[] bdata, int location) {
		int[] data = ascendingSort(bdata);
		return data[location - 1];
	}

	// 查找第location大的数据
	public static int findIntByBig(int[] bdata, int location) {
		int[] data = descendingSort(bdata);
		return data[location - 1];
	}
	//汉诺塔
	public static void hanoi(int n,String a,String b,String c){
		
		if(n==1){
//			System.out.println("第"+(++count)+"次移动:"+a+"棒>>>>>>>"+c+"棒");
			++count;
			num=count;
		}else{
			hanoi(n-1, a, c, b);
//			System.out.println("第"+(++count)+"次移动:"+a+"棒>>>>>>>"+c+"棒");
			++count;
			hanoi(n-1, b, a, c);
		}
	}
	public static int hanoiStepNum(){
		return num;
	}
	
}
