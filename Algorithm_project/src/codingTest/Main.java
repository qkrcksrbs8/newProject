package codingTest;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		
		String[] genres = {"classic", "pop", "classic", "classic", "pop"};
		String[] plays = {"500", "600", "150", "800", "2500"};
		String[][] sum = {genres,plays};
//Arrays.sort(arr, 0, 4);
		int[] answer = {};
		int[] array = {1, 5, 2, 6, 3, 7, 4};//5263 -> 2356 -? 5 | 1234567 -> 123 ->
		int[][] commands = {{2, 5, 3},{4, 4, 1}, {1, 7, 3}};//, 
		//1. commands �迭�� ���� ��ŭ �ݺ��� ����
        //2. commands �迭 i��° ���� idex 0, idex 1���� substring
        //3. substring�� ���� sort ����
        //3. sort ���ĵ� ���� idex 2 �� ����
        for(int i = 0; i < commands.length; ++i){
        	int[] array2 = array;
        	int[] array3 = new int[commands[i][1]];
        	int cnt = 0;
        	for(int j = commands[i][0]-1; j < commands[i][1]; ++j) {
        		System.out.print(" "+array2[j]);
        		array3[cnt]=array2[j];
        		cnt++;
        	}
        	System.out.println("");
        	Arrays.sort(array3);
        	System.out.println("i : "+array3[commands[i][2]]);
//        	answer[i] = array2[commands[i][2]];
        } 

    	System.out.println("��� : "+answer);
	}

} 
