package algorithm;

public class Bubble_sort {

	public static void main(String[] args) {
		/* 알고리즘문제 버블정렬 */
		int i, j, temp;
		int array[] = {1, 10, 5, 8, 7, 6, 4, 3, 2, 9};
		for(i = 0; i < 10; i++) {
			for(j = 0; j < 9 - i; j++) {
				if(array[j] > array[j+1]) {
					temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
				}
			}
		}
		for(i = 0; i < 10; i++) {
			System.out.print(array[i]+" ");
		}

	}
}
