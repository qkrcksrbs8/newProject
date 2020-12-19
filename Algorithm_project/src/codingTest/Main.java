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
		//1. commands 배열의 길이 만큼 반복문 실행
        //2. commands 배열 i번째 값의 idex 0, idex 1까지 substring
        //3. substring된 값을 sort 정렬
        //3. sort 정렬된 값의 idex 2 값 추출
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

    	System.out.println("결과 : "+answer);
	}

} 
