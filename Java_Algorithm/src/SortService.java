public class SortService {
	public void selectSort(int[] array) {
		int out, in, min;
		for (out = 0; out < array.length - 1; out++) {
			min = out;
			for (in = out + 1; in < array.length; in++)
				if (array[in] < array[min])
					min = in;
			swap(out, min, array);
		}
	}

	public static void swap(int one, int two, int[] array) {
		int temp = array[one];
		array[one] = array[two];
		array[two] = temp;
	}

	public static void bubbleSort(int[] array) {
		int out, in;
		int length = array.length;
		// out=1时只剩下两个待排序的数
		for (out = length - 1; out > 0; out--) {
			// in=out-1时，比较最后两个数
			for (in = 0; in < out; in++)
				if (array[in] > array[in + 1])
					swap(in, in + 1, array);
		}
	}

	public void insertSort(int[] array) {
		int in, out;
		int length = array.length;
		for (out = 1; out < length; out++) {
			int temp = array[out];
			in = out;
			while (in > 0 && array[in - 1] >= temp) {
				array[in] = array[in - 1];
				--in;
			}
			array[in] = temp;
		}
	}
}
