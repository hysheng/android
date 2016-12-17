
public class Test {

	/**
	 * ����������
	 * @param args
	 */
	public static int[] array = new int[100];

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		for (int i = 0; i < array.length; i++) {
			array[i] = (int) (Math.random() * 100);

		}
		System.out.println("����Ҫ���������:");
		displayData(array);
		long startTime = System.currentTimeMillis();
//		HughQuickSort.sort(0, array.length - 1, array);
		quickSort(0, array.length-1,array);
		long endTime = System.currentTimeMillis();
		System.out.println("����������飺");
		displayData(array);
		System.out.println();
		System.out.println("�㷨�ķѣ�" + (endTime - startTime) + "ms");

		// ��ŵ������
		// AlgorithmService.hanoi(3, "a", "b", "c");
		// System.out.println(AlgorithmService.hanoiStepNum());
	}

	private static void displayData(int[] array) {
		for (int i = 0; i < array.length; i++)
			System.out.print("	" + array[i]);
		System.out.println();
	}

	private static void quickSort(int left, int right, int[] arr) {
		int l = left;
		int r = right;
		int pivot = arr[(left + right) / 2];
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
				l++;
			if (arr[r] == pivot)
				r--;
		}
		if (l == r) {
			l++;
			r--;
		}
		if (left < r)
			quickSort(left, r, arr);
		if (l < right)
			quickSort(l, right, arr);

	}

}
