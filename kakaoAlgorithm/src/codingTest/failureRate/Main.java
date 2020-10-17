package codingTest.failureRate;

import java.util.Arrays;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Solution solution = new Solution();
		
		int num = 5;//스테이지 갯수
		int[] arrayNum = {2, 1, 2, 6, 2, 4, 3, 3};//사용자가 도전중인 스테이지
		
		//--------------------
		//기대값 [3, 4, 2, 1, 5]
		//--------------------
		System.out.println(Arrays.toString(solution.solution(num, arrayNum)));//실패율
		

	}

}
