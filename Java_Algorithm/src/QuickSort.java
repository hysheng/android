


//没有修改的，正常使用
public class QuickSort {

	/**
	 * @param args
	 */
	public static int[] data = new int[100];

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (int i = 0; i < data.length; i++)
			data[i] = (int) (Math.random() * 100);
		displayData(data);
		long startTime = System.currentTimeMillis();
		sort(0, data.length - 1, data);
		System.out.println("排序成功：");
		long endTime = System.currentTimeMillis();
		System.out.println("算法耗费：" + (endTime - startTime) + "ms");
		displayData(data);
	}

	public static void sort(int left, int right, int[] arr) {
		int l = left;
		int r = right;
		int pivot = arr[(left + right) / 2];// 找中间值
		int temp = 0;
		while (l < r) {
			while (arr[l] < pivot)
				l++;
			while (arr[r] > pivot)
				r--;
			if (l >= r)
				break;
			temp = arr[l];
			arr[l] = arr[r];
			arr[r] = temp;
			if (arr[l] == pivot)
				r--;
			if (arr[r] == pivot)
				l++;
		}
		if (l == r) {
			l++;
			r--;
		}
		if (left < r)
			sort(left, r, arr);
		if (right > l)
			sort(l, right, arr);
	}

	private static void displayData(int[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print("	" + array[i]);
			if (i % 10 == 0)
				System.out.println();
		}
		System.out.println();
	}
}
