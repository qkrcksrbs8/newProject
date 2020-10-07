package algorithm;

public class Selection_Sort {

	public static void main(String[] args) {
		/* 알고리즘문제 선택정렬 */
		int i, j, min, index = 0, temp;
		int[] array = {1,10,5,8,7,6,4,3,2,9};
		for(i = 0; i < 10; i++) {
			min = 9999;
			for(j = i; j < 10; j++) {
				if(min > array[j]) {
					min = array[j];
					index = j;
				}
			}
			temp = array[i];
			array[i] = array[index];
			array[index] = temp;
		}
		for(i = 0; i < 10; i++) {
			System.out.print(array[i]+" ");
		}
	}

}
